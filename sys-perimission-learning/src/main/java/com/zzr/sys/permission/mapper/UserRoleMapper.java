package com.zzr.sys.permission.mapper;

import com.zzr.sys.permission.core.MyMapper;
import com.zzr.sys.permission.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户持久化接口
 * 
 */
public interface UserRoleMapper extends MyMapper<UserRole> {

    /**
     * 删除用户角色
     * @param userId
     */
	void deleteByUserId(Integer userId);

    void deleteByRoleIds(@Param("idList") List<Integer> idList);

    void deleteByUserIds(@Param("idList") List<Integer> idList);
}
