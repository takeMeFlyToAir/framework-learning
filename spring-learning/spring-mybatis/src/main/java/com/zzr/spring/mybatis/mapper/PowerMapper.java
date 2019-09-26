package com.zzr.spring.mybatis.mapper;


import com.zzr.spring.mybatis.common.MyMapper;
import com.zzr.spring.mybatis.entity.PowerEntity;

import java.util.List;

public interface PowerMapper extends MyMapper<PowerEntity> {

    List<PowerEntity> findByRoleCode(Integer roleCode);
}