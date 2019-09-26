package com.zzr.sys.permission.controller;

import com.zzr.sso.core.login.SsoWebLoginHelper;
import com.zzr.sso.core.user.ZzrSsoUser;
import com.zzr.sys.permission.common.RpcPermission;
import com.zzr.sys.permission.core.Result;
import com.zzr.sys.permission.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Joe
 */
@Api(tags = "首页管理")
@Controller
@RequestMapping("/admin/admin")
public class AdminController {
	private static List<RpcPermission> applicationMenuList = null;
	private static Set<String> applicationPermissionSet = null;

	@Autowired
	private PermissionService permissionService;

	@ApiOperation("初始页")
	@RequestMapping(method = RequestMethod.GET)
	public String execute(HttpServletRequest request, HttpServletResponse response, Model model) {
		ZzrSsoUser zzrSsoUser = SsoWebLoginHelper.loginCheck(request, response);
		// 设置登录用户名
		model.addAttribute("userName", zzrSsoUser.getUserName());
		// 设置当前登录用户没有的权限，以便控制前台按钮的显示或者隐藏
		model.addAttribute("sessionUserNoPermissions",null);
		// 默认首页
		// model.addAttribute("defaultPage", null);
		return "/admin";
	}

	@ApiOperation("菜单")
	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public @ResponseBody
	Result menu(HttpServletRequest request) {
		List<RpcPermission> dbList = permissionService.findListById(null,null);
		applicationMenuList = new ArrayList<RpcPermission>();
		applicationPermissionSet = new HashSet<String>();
		for (RpcPermission menu : dbList) {
			if (menu.getIsMenu()) {
				applicationMenuList.add(menu);
			}
			if (!StringUtils.isEmpty(menu.getUrl())) {
				applicationPermissionSet.add(menu.getUrl());
			}
		}
		// 如果配置的权限拦截器，则获取登录用户权限下的菜单，没有权限拦截限制的情况下，获取当前系统菜单呈现
		return Result.createSuccessResult().setData(applicationMenuList);
	}
}