package com.zzr.sys.permission.mapper;

import com.zzr.sys.permission.common.RpcPermission;
import com.zzr.sys.permission.core.MyMapper;
import com.zzr.sys.permission.entity.Permission;
import com.zzr.sys.permission.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户持久化接口
 * 
 */
public interface PermissionMapper extends MyMapper<Permission> {
    List<RpcPermission> findListById(@Param("appCode") String appCode, @Param("userId") Integer userId);

    List<Permission> findByAppId(@Param("appId") Integer appId, @Param("isEnable") Boolean isEnable);

    int deleteByAppIds(@Param("idList") List<Integer> idList);
}
