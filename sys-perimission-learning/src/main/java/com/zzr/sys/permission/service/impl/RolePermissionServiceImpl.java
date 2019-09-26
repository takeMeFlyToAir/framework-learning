package com.zzr.sys.permission.service.impl;

import com.zzr.sys.permission.core.BaseService;
import com.zzr.sys.permission.entity.App;
import com.zzr.sys.permission.entity.RolePermission;
import com.zzr.sys.permission.mapper.RolePermissionMapper;
import com.zzr.sys.permission.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service("rolePermissionService")
public class RolePermissionServiceImpl extends BaseService<RolePermission> implements RolePermissionService {

	@Autowired
	private RolePermissionMapper rolePermissionMapper;

	@Override
	public List<RolePermission> findByRoleId(Integer roleId) {
		RolePermission rolePermission = new RolePermission();
		rolePermission.setDeleted(0);
		rolePermission.setRoleId(roleId);
		return rolePermissionMapper.select(rolePermission);
	}

	@Override
	public void allocate(Integer appId, Integer roleId, List<Integer> permissionIdList) {
		rolePermissionMapper.deleteByAppAndRoleId(appId, roleId);

		List<RolePermission> list = new ArrayList<RolePermission>();
		Integer permissionId;
		for (Iterator<Integer> i$ = permissionIdList.iterator(); i$.hasNext(); list
				.add(new RolePermission(appId, roleId, permissionId))) {
			permissionId = i$.next();
		}
		if (!CollectionUtils.isEmpty(list)) {
			super.saveList(list);
		}
	}

	@Override
	public void deleteByPermissionIds(List<Integer> idList) {
		Example example = new Example(RolePermission.class);
		example.createCriteria().andEqualTo("deleted",0).andIn("permissionId",idList);
		rolePermissionMapper.deleteByExample(example);
	}

	@Override
	public void deleteByRoleIds(List<Integer> idList) {
		Example example = new Example(RolePermission.class);
		example.createCriteria().andEqualTo("deleted",0).andIn("roleId",idList);
		rolePermissionMapper.deleteByExample(example);
	}

	@Override
	public void deleteByAppIds(List<Integer> idList) {
		Example example = new Example(RolePermission.class);
		example.createCriteria().andEqualTo("deleted",0).andIn("appId",idList);
		rolePermissionMapper.deleteByExample(example);
	}
}
