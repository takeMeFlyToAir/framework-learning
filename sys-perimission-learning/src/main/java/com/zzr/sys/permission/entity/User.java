package com.zzr.sys.permission.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.zzr.sys.permission.core.BaseEntity;
import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

/**
 * 用户
 */
@Table(name = "sys_user")
public class User extends BaseEntity {

	private static final long serialVersionUID = 1106412532325860697L;
	
	/** 登录名 */
	private String account;
	/** 密码 */
	private String password;
	/** 最后登录IP */
	private String lastLoginIp;
	/** 登录总次数 */
	private Integer loginCount = Integer.valueOf(0);
	/** 最后登录时间 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date lastLoginTime;
	/** 创建时间 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/** 是否启用 */
	private Boolean isEnable;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean enable) {
		isEnable = enable;
	}

	public String getIsEnableStr() {
		return (isEnable != null && isEnable) ? "是" : "否";
	}
}
