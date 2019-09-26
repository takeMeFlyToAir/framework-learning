package com.zzr.spring.mybatis.entity;


import com.zzr.spring.mybatis.common.BaseEntity;
import lombok.Data;

import javax.persistence.Table;

@Table(name = "rd_power")
@Data
public class PowerEntity extends BaseEntity {

    private String code;

    private String remark;
}