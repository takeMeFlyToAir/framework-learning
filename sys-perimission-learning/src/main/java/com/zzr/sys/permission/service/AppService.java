package com.zzr.sys.permission.service;

import com.zzr.sys.permission.core.IService;
import com.zzr.sys.permission.core.Pager;
import com.zzr.sys.permission.core.PagerResult;
import com.zzr.sys.permission.entity.App;

import java.util.List;

/**
 * 应用服务接口
 * 
 * @author zzr
 */
public interface AppService extends IService<App> {
	
	/**
	 * 启用禁用操作
	 * @param isEnable 是否启用
	 * @param idList 应用ID集合
	 * @return
	 */
	void enable(Boolean isEnable, List<Integer> idList);
	
	/**
	 * 根据是否可用查询
	 * @param isEnable 是否可用
	 * @return
	 */
	 List<App> findByAll(Boolean isEnable);


	/**
	 * 根据名称分页查询
	 * @param name 应用名称
	 * @return
	 */
	PagerResult<App> findPageByName(String name, PagerResult<App> PagerResult);

	/**
	 * 根据应用编码查询
	 * @param code 应用编码
	 * @return
	 */
	App findByCode(String code);
}