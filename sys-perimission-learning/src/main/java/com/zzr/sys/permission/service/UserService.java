package com.zzr.sys.permission.service;

import com.zzr.sys.permission.core.IService;
import com.zzr.sys.permission.core.Pager;
import com.zzr.sys.permission.core.PagerResult;
import com.zzr.sys.permission.core.Result;
import com.zzr.sys.permission.entity.User;

import java.util.List;

/**
 * 用户服务接口
 * 
 */
public interface UserService extends IService<User> {
	

	/**
	 * 启用禁用操作
	 * @param isEnable 是否启用
	 * @param idList 用户ID集合
	 * @return
	 */
	void enable(Boolean isEnable, List<Integer> idList);
	
	/**
	 * 重置密码
	 * @param password 初始化密码(已加密)
	 * @param idList 
	 */
	 void resetPassword(String password, List<Integer> idList);

	/**
	 * 根据登录名和应用ID查询分页列表
	 * @param account 登录名
	 * @return
	 */
	 PagerResult<User> findPageByAccount(String account,  PagerResult pagerResult);
	
	/**
	 * 根据登录名和应用ID查询
	 * @param account 登录名
	 * @param appId 应用ID
	 * @return
	 */
	 User findByAccount(String account);
	
	/**
	 * 更新密码
	 * 
	 * @param id
	 *            用户ID
	 * @param newPassword
	 *            新密码
	 * @return
	 */
	 void updatePassword(Integer id, String newPassword);
	
	
	 void save(User user, List<Integer> roleIdList);

	 void update(User user, List<Integer> roleIdList);
}
