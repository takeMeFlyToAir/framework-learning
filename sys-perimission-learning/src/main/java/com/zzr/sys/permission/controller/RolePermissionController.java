package com.zzr.sys.permission.controller;

import com.zzr.sys.permission.controller.common.BaseController;
import com.zzr.sys.permission.core.Result;
import com.zzr.sys.permission.service.AppService;
import com.zzr.sys.permission.service.RolePermissionService;
import com.zzr.sys.permission.validator.Validator;
import com.zzr.sys.permission.validator.annotation.ValidateParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author Joe
 */
@Api(tags = "用户角色关系管理")
@Controller
@RequestMapping("/admin/rolePermission")
public class RolePermissionController extends BaseController {

	@Resource
	private AppService appService;
	@Resource
	private RolePermissionService rolePermissionService;

	@ApiOperation("初始页")
	@RequestMapping(method = RequestMethod.GET)
	public String edit(
			@ApiParam(value = "角色id", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer roleId, Model model) {
		model.addAttribute("roleId", roleId);
		model.addAttribute("appList", appService.findByAll(true));
		return "/admin/rolePermission";
	}
	
	@ApiOperation("角色授权提交")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody
	Result save(
			@ApiParam(value = "应用id", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer appId,
			@ApiParam(value = "角色id", required = true) @ValidateParam({ Validator.NOT_BLANK }) Integer roleId,
			@ApiParam(value = "权限ids") String permissionIds) {
		rolePermissionService.allocate(appId, roleId, getAjaxIds(permissionIds));
		return Result.createSuccessResult().setMessage("授权成功");
	}
}