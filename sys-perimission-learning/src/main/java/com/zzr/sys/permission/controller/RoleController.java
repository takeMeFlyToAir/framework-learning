package com.zzr.sys.permission.controller;

import com.zzr.sys.permission.controller.common.BaseController;
import com.zzr.sys.permission.core.Pager;
import com.zzr.sys.permission.core.PagerResult;
import com.zzr.sys.permission.core.Result;
import com.zzr.sys.permission.entity.Role;
import com.zzr.sys.permission.service.RoleService;
import com.zzr.sys.permission.validator.Validator;
import com.zzr.sys.permission.validator.annotation.ValidateParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author Joe
 */
@Api(tags = "角色管理")
@Controller
@RequestMapping("/admin/role")
public class RoleController extends BaseController {

	@Autowired
	private RoleService roleService;

	@ApiOperation("初始页")
	@RequestMapping(method = RequestMethod.GET)
	public String execute(Model model) {
		return "/admin/role";
	}

	@ApiOperation("新增/修改页")
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(@ApiParam(value = "id") Integer id, Model model) {
		Role role;
		if (id == null) {
			role = new Role();
		}
		else {
			role = roleService.selectByKey(id);
		}
		model.addAttribute("role", role);
		return "/admin/roleEdit";
	}

	@ApiOperation("列表")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
	Result list(
			@ApiParam(value = "角色名")String name,
			@ApiParam(value = "开始页码", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer pageNo,
			@ApiParam(value = "显示条数", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer pageSize) {
		return Result.createSuccessResult().setData(roleService.findPageByName(name, new PagerResult(pageNo, pageSize)));
	}

	@ApiOperation("启用/禁用")
	@RequestMapping(value = "/enable", method = RequestMethod.POST)
	public @ResponseBody
    Result enable(
			@ApiParam(value = "ids", required = true) @ValidateParam({ Validator.NOT_BLANK }) String ids,
			@ApiParam(value = "是否启用", required = true) @ValidateParam({ Validator.NOT_BLANK }) Boolean isEnable) {
		roleService.enable(isEnable, getAjaxIds(ids));
		return Result.createSuccessResult();
	}

	@ApiOperation("新增/修改提交")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody
    Result save(
			@ApiParam(value = "id") Integer id,
			@ApiParam(value = "角色名", required = true) @ValidateParam({ Validator.NOT_BLANK }) String name,
			@ApiParam(value = "排序", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer sort,
			@ApiParam(value = "描述") String description,
			@ApiParam(value = "是否启用", required = true) @ValidateParam({ Validator.NOT_BLANK }) Boolean isEnable) {
		Role role = new Role();
		role.setName(name);
		role.setSort(sort);
		role.setDescription(description);
		role.setIsEnable(isEnable);
		if (id == null) {
			roleService.save(role);
		}
		else {
			role.setId(id);
			roleService.updateNotNull(role);
		}

		return Result.createSuccessResult();
	}
	
	@ApiOperation("删除")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
    Result delete(
			@ApiParam(value = "ids", required = true) @ValidateParam({ Validator.NOT_BLANK }) String ids) {
		roleService.deleteByIdList(getAjaxIds(ids));
		return Result.createSuccessResult();
	}
}