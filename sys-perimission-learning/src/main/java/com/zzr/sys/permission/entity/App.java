package com.zzr.sys.permission.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.zzr.sys.permission.core.BaseEntity;
import lombok.Data;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * 应用
 * 
 * @author Joe
 */
@Table(name = "sys_app")
@Data
public class App extends BaseEntity {

	private static final long serialVersionUID = 7902814112969375973L;
	
	/** 名称 */
	private String name;
	/** 编码  */
	private String code;
	/** 排序 */
	private Integer sort = Integer.valueOf(1);
	/** 创建时间 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/** 是否启用 */
	private Boolean isEnable = Boolean.valueOf(true);
	
	public App(){
	}
	
	public App(String name, String code, Integer sort, Boolean isEnable) {
		super();
		this.name = name;
		this.code = code;
		this.sort = sort;
		this.isEnable = isEnable;
	}

	/** 以下为显示辅助参数 */
	@Transient
	private Boolean isChecked = Boolean.valueOf(false);
	
	public Boolean getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(Boolean isChecked) {
		this.isChecked = isChecked;
	}

	public String getIsEnableStr() {
		return (isEnable != null && isEnable) ? "是" : "否";
	}

}
