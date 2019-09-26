package com.zzr.sys.permission.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzr.sys.permission.core.BaseService;
import com.zzr.sys.permission.core.Pager;
import com.zzr.sys.permission.core.PagerResult;
import com.zzr.sys.permission.entity.App;
import com.zzr.sys.permission.entity.User;
import com.zzr.sys.permission.mapper.AppMapper;
import com.zzr.sys.permission.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("appService")
public class AppServiceImpl extends BaseService<App> implements AppService {

	@Autowired
	private AppMapper appMapper;
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private RolePermissionService rolePermissionService;


	@Override
	public void enable(Boolean isEnable, List<Integer> idList) {
		App app = new App();
		app.setIsEnable(isEnable);
		Example example = new Example(User.class);
		example.createCriteria().andIn("id",idList);
		verifyRows(appMapper.updateByExampleSelective(app,example),idList.size(),"用户数据库更新失败");
	}

	@Override
	public List<App> findByAll(Boolean isEnable) {
		App app = new App();
		app.setDeleted(0);
		app.setIsEnable(isEnable);
		return appMapper.select(app);
	}

	@Override
	public PagerResult<App> findPageByName(String name, PagerResult pagerResult) {
		Example example = new Example(App.class);
		example.createCriteria().andEqualTo("deleted",0);
		List<App> allList = this.selectByExample(example);

		Map<String,Object> paramsMap = new HashMap();
		initParamsFromPagerResult(paramsMap,pagerResult);
		paramsMap.put("name",name);
		pagerResult.initData(appMapper.findPageList(paramsMap),allList.size());
		return pagerResult;
	}

	@Override
	public App findByCode(String code) {
		Example example = new Example(App.class);
		example.createCriteria().andEqualTo("deleted",0).andEqualTo("code",code);
		List<App> appList = appMapper.selectByExample(example);
		if (appList != null && appList.size() > 0) {
			return appList.get(0);
		}
		return null;
	}

	@Override
	public void deleteByIdList(List<Integer> list) {
		rolePermissionService.deleteByAppIds(list);
		permissionService.deleteByAppIds(list);
		super.deleteByIdList(list);
	}
}
