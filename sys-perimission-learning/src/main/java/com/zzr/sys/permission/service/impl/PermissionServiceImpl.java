package com.zzr.sys.permission.service.impl;

import com.zzr.sys.permission.common.RpcPermission;
import com.zzr.sys.permission.core.BaseService;
import com.zzr.sys.permission.entity.Permission;
import com.zzr.sys.permission.entity.RolePermission;
import com.zzr.sys.permission.mapper.PermissionMapper;
import com.zzr.sys.permission.service.PermissionService;
import com.zzr.sys.permission.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaozhirong on 2019/5/17.
 */
@Service
public class PermissionServiceImpl extends BaseService<Permission> implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Override
    public List<Permission> findByAppId(Integer appId, Integer roleId, Boolean isEnable) {
        List<Permission> permissionList = permissionMapper.findByAppId(appId, isEnable);
        if (roleId != null) {
            List<RolePermission> rolePermissionList = rolePermissionService.findByRoleId(roleId);
            for (Permission permission : permissionList) {
                for (RolePermission rp : rolePermissionList) {
                    if (permission.getId().equals(rp.getPermissionId())) {
                        permission.setChecked(true);
                        break;
                    }
                }
            }
        }
        return permissionList;
    }

    @Override
    public void deletePermission(Integer id, Integer appId) {
        List<Integer> idList = new ArrayList<Integer>();

        List<Permission> list = this.findByAppId(appId, null, null);
        loopSubList(id, idList, list);
        idList.add(id);
        rolePermissionService.deleteByPermissionIds(idList);
        this.deleteByIdList(idList);
    }
    // 递归方法，删除子权限
    protected void loopSubList(Integer id, List<Integer> idList, List<Permission> list) {
        for (Permission p : list) {
            if (id.equals(p.getParentId())) {
                idList.add(p.getId());
                loopSubList(p.getId(), idList, list);
            }
        }
    }
    @Override
    public void deleteByAppIds(List<Integer> idList) {
        permissionMapper.deleteByAppIds(idList);

    }

    @Override
    public List<RpcPermission> findListById(String appCode, Integer userId) {
        return permissionMapper.findListById(appCode, userId);
    }
}
