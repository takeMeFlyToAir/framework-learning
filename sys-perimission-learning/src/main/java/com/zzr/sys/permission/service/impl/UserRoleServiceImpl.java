package com.zzr.sys.permission.service.impl;

import com.zzr.sys.permission.core.BaseService;
import com.zzr.sys.permission.core.Pager;
import com.zzr.sys.permission.core.PagerResult;
import com.zzr.sys.permission.entity.Role;
import com.zzr.sys.permission.entity.User;
import com.zzr.sys.permission.entity.UserRole;
import com.zzr.sys.permission.mapper.UserRoleMapper;
import com.zzr.sys.permission.service.RoleService;
import com.zzr.sys.permission.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by zhaozhirong on 2019/5/17.
 */
@Service
public class UserRoleServiceImpl extends BaseService<UserRole> implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public UserRole findByUserRoleId(Integer userId, Integer roleId) {
        Example example = new Example(UserRole.class);
        example.createCriteria().andEqualTo("deleted",0).andEqualTo("userId",userId).andEqualTo("roleId",roleId);
        List<UserRole> list = userRoleMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public void allocate(Integer userId, List<UserRole> list) {
        //先删除角色
        userRoleMapper.deleteByUserId(userId);
        this.saveList(list);
    }

    @Override
    public void deleteByRoleIds(List<Integer> idList) {
        userRoleMapper.deleteByRoleIds(idList);
    }

    @Override
    public void deleteByUserIds(List<Integer> idList) {
        userRoleMapper.deleteByUserIds(idList);
    }
}
