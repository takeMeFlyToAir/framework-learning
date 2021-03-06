package com.zzr.sys.permission.entity;

import com.zzr.sys.permission.core.BaseEntity;

/**
 * 用户应用映射
 * 
 * @author Joe
 */
public class UserApp extends BaseEntity {

	private static final long serialVersionUID = 4942358338145288018L;

	/** 应用ID */
	private Integer appId;
	/** 用户ID */
	private Integer userId;
	
	public Integer getAppId() {
		return this.appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
