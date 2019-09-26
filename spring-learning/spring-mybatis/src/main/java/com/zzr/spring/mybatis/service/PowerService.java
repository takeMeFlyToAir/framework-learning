package com.zzr.spring.mybatis.service;


import com.zzr.spring.mybatis.common.IService;
import com.zzr.spring.mybatis.common.PagerForDT;
import com.zzr.spring.mybatis.common.PagerResultForDT;
import com.zzr.spring.mybatis.entity.PowerEntity;

import java.util.List;

/**
 * @Author: zhaozhirong
 * @Date: 2019/01/08
 * @Description:
 */
public interface PowerService extends IService<PowerEntity> {

    /**
     * 分页查询
     * @param pager
     * @return
     */
    PagerResultForDT<PowerEntity> selectPage(PagerForDT<String> pager);

    Boolean hasCode(String code);

    List<PowerEntity> findAll();

    /**
     * 查询所有的权限，并且包含此权限是否给某角色分配的标记
     * @return
     */
    List<PowerEntity> findByRoleCode(Integer roleCode);
}
