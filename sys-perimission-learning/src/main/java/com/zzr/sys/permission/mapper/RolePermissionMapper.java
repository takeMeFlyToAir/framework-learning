package com.zzr.sys.permission.mapper;

import com.zzr.sys.permission.core.MyMapper;
import com.zzr.sys.permission.entity.RolePermission;
import org.apache.ibatis.annotations.Param;

/**
 * 用户持久化接口
 * 
 */
public interface RolePermissionMapper extends MyMapper<RolePermission> {
    int deleteByAppAndRoleId(@Param("appId") Integer appId, @Param("roleId") Integer roleId);
}
