package com.zzr.sys.permission.controller;

import com.zzr.sys.permission.config.ConfigUtils;
import com.zzr.sys.permission.controller.common.BaseController;
import com.zzr.sys.permission.core.*;
import com.zzr.sys.permission.entity.Role;
import com.zzr.sys.permission.entity.User;
import com.zzr.sys.permission.entity.UserRole;
import com.zzr.sys.permission.exception.ValidateException;
import com.zzr.sys.permission.provider.PasswordProvider;
import com.zzr.sys.permission.service.RoleService;
import com.zzr.sys.permission.service.UserRoleService;
import com.zzr.sys.permission.service.UserService;
import com.zzr.sys.permission.util.StringUtils;
import com.zzr.sys.permission.validator.Validator;
import com.zzr.sys.permission.validator.annotation.ValidateParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * @author Joe
 */
@Api(tags = "用户管理")
@Controller
@RequestMapping("/admin/user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserRoleService userRoleService;

	@ApiOperation("初始页")
	@RequestMapping(method = RequestMethod.GET)
	public String execute(Model model) {
		return "/admin/user";
	}

	@ApiOperation("新增/修改页")
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@ApiParam(value = "id") Integer id, Model model) {
		User user;
		if (id == null) {
			user = new User();
		}
		else {
			user = userService.selectByKey(id);
		}
		model.addAttribute("user", user);
		model.addAttribute("roleList", getRoleList(id));
		return "/admin/userEdit";
	}

	@ApiOperation("列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
    Result list(
			@ApiParam(value = "登录名") String account,
			@ApiParam(value = "开始页码", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer pageNo,
			@ApiParam(value = "显示条数", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer pageSize) {
		return Result.createSuccessResult().setData(userService.findPageByAccount(account, new PagerResult(pageNo, pageSize)));
	}

	@ApiOperation("验证登录名")
	@RequestMapping(value = "/validateAccount", method = RequestMethod.POST)
	public @ResponseBody
    Result validateAccount(
			@ApiParam(value = "id") Integer id,
			@ApiParam(value = "登录名", required = true) @ValidateParam({ Validator.NOT_BLANK }) String account) {
		Result result = Result.createSuccessResult();
		User user = userService.findByAccount(account);
		if (null != user && !user.getId().equals(id)) {
			result.setCode(ResultCode.ERROR).setMessage("登录名已存在");
		}
		return result;
	}

	@ApiOperation("启用/禁用")
	@RequestMapping(value = "/enable", method = RequestMethod.POST)
	public @ResponseBody
    Result enable(
			@ApiParam(value = "ids", required = true) @ValidateParam({ Validator.NOT_BLANK }) String ids,
			@ApiParam(value = "是否启用", required = true) @ValidateParam({ Validator.NOT_BLANK }) Boolean isEnable) {
		userService.enable(isEnable, getAjaxIds(ids));
		return Result.createSuccessResult();
	}

	@ApiOperation("新增/修改提交")
	@ApiResponse(response = Result.class, code = 200, message = "success")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody
    Result save(
			@ApiParam(value = "id") Integer id,
			@ApiParam(value = "登录名", required = true) @ValidateParam({ Validator.NOT_BLANK }) String account,
			@ApiParam(value = "密码 ") String password,
			@ApiParam(value = "是否启用", required = true) @ValidateParam({ Validator.NOT_BLANK }) Boolean isEnable,
			@ApiParam(value = "角色ids") String roleIds) {
		User user = new User();
		user.setAccount(account);
		if (StringUtils.isNotBlank(password)) {
			user.setPassword(PasswordProvider.encrypt(password));
		}
		user.setIsEnable(isEnable);
		if (id == null) {
			if (StringUtils.isBlank(password)) {
				throw new ValidateException("密码不能为空");
			}
			user.setCreateTime(new Date());
			userService.save(user, getAjaxIds(roleIds));
		}
		else {
			user.setId(id);
			userService.update(user, getAjaxIds(roleIds));
		}

		return Result.createSuccessResult();
	}

	@ApiOperation("重置密码")
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public @ResponseBody
    Result resetPassword(
			@ApiParam(value = "ids", required = true) @ValidateParam({ Validator.NOT_BLANK }) String ids) {
		userService.resetPassword(PasswordProvider.encrypt(ConfigUtils.getProperty("system.reset.password")), getAjaxIds(ids));
		return Result.createSuccessResult();
	}

	@ApiOperation("删除")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
    Result delete(
			@ApiParam(value = "ids", required = true) @ValidateParam({ Validator.NOT_BLANK }) String ids) {
		userService.deleteByIdList(getAjaxIds(ids));
		return Result.createSuccessResult();
	}
	
	private List<Role> getRoleList(Integer userId) {
		List<Role> list = roleService.findByAll(TrueFalseCons.TRUE);
		if (userId != null) {
			for (Role role : list) {
				UserRole userRole = userRoleService.findByUserRoleId(userId, role.getId());
				if (null != userRole) {
					role.setIsChecked(true);
				}
				else {
					role.setIsChecked(false);
				}
			}
		}
		return list;
	}
}