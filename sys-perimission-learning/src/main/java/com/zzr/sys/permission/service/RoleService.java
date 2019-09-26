package com.zzr.sys.permission.service;


import com.zzr.sys.permission.core.IService;
import com.zzr.sys.permission.core.Pager;
import com.zzr.sys.permission.core.PagerResult;
import com.zzr.sys.permission.entity.Role;

import java.util.List;

/**
 * 角色服务接口
 * 
 * @author Joe
 */
public interface RoleService extends IService<Role> {
	
	/**
	 * 启用禁用操作
	 * @param isEnable 是否启用
	 * @param idList 角色ID集合
	 * @return
	 */
	 void enable(Boolean isEnable, List<Integer> idList);
	
	/**
	 * 根据角色名称和应用ID查询分页列表
	 * @param name 角色名称
	 * @return
	 */
	 PagerResult<Role> findPageByName(String name,PagerResult pagerResult);
	
	/**
	 * 查询应用可用角色
	 * @param isEnable 是否启用
	 * @return
	 */
	 List<Role> findByAll(Boolean isEnable);
}
