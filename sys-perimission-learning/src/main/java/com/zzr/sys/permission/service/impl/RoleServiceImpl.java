package com.zzr.sys.permission.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzr.sys.permission.core.BaseService;
import com.zzr.sys.permission.core.Pager;
import com.zzr.sys.permission.core.PagerResult;
import com.zzr.sys.permission.entity.Role;
import com.zzr.sys.permission.entity.User;
import com.zzr.sys.permission.mapper.RoleMapper;
import com.zzr.sys.permission.service.RolePermissionService;
import com.zzr.sys.permission.service.RoleService;
import com.zzr.sys.permission.service.UserRoleService;
import com.zzr.sys.permission.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaozhirong on 2019/5/17.
 */
@Service
public class RoleServiceImpl extends BaseService<Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RolePermissionService rolePermissionService;

    @Override
    public void enable(Boolean isEnable, List<Integer> idList) {
        roleMapper.enable(isEnable,idList);
    }

    @Override
    public PagerResult<Role> findPageByName(String name,PagerResult pagerResult) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("deleted",0);
        List<Role> allList = this.selectByExample(example);

        Map<String,Object> paramsMap = new HashMap();
        initParamsFromPagerResult(paramsMap,pagerResult);
        paramsMap.put("name",name);
        pagerResult.initData(roleMapper.findPageList(paramsMap),allList.size());
        return pagerResult;
    }

    @Override
    public List<Role> findByAll(Boolean isEnable) {
        Example example = new Example(Role.class);
        example.createCriteria().andEqualTo("deleted",0).andEqualTo("isEnable",isEnable);
        return roleMapper.selectByExample(example);
    }

    @Override
    public void deleteByIdList(List<Integer> list) {
        userRoleService.deleteByRoleIds(list);
        rolePermissionService.deleteByRoleIds(list);
        super.deleteByIdList(list);
    }
}
