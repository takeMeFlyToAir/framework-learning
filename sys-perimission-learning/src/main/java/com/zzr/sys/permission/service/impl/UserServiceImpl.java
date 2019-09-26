package com.zzr.sys.permission.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzr.sys.permission.core.BaseService;
import com.zzr.sys.permission.core.Pager;
import com.zzr.sys.permission.core.PagerResult;
import com.zzr.sys.permission.entity.UserRole;
import com.zzr.sys.permission.mapper.UserMapper;
import com.zzr.sys.permission.entity.User;
import com.zzr.sys.permission.service.UserRoleService;
import com.zzr.sys.permission.service.UserService;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl  extends BaseService<User> implements UserService {


	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserRoleService userRoleService;

	@Override
	public void enable(Boolean isEnable, List<Integer> idList) {
		verifyRows(userMapper.enable(isEnable,idList),idList.size(),"用户数据库更新失败");
	}

	@Override
	public void resetPassword(String password, List<Integer> idList) {
		User user = new User();
		user.setPassword(password);
		Example example = new Example(User.class);
		example.createCriteria().andIn("id",idList);
		verifyRows(userMapper.updateByExampleSelective(user,example),idList.size(),"用户数据库更新失败");
	}

	@Override
	public PagerResult<User> findPageByAccount(String account,PagerResult pagerResult) {
		Example example = new Example(User.class);
		example.createCriteria().andEqualTo("deleted",0);
		List<User> allList = this.selectByExample(example);

		Map<String,Object> paramsMap = new HashMap();
		initParamsFromPagerResult(paramsMap,pagerResult);
		paramsMap.put("account",account);
		pagerResult.initData(userMapper.findPageList(paramsMap),allList.size());
		return pagerResult;
	}

	@Override
	public User findByAccount(String account) {
		Example example = new Example(User.class);
		example.createCriteria().andEqualTo("deleted",0).andEqualTo("account",account);
		List<User> users = userMapper.selectByExample(example);
		if (users != null && users.size() > 0) {
			return users.get(0);
		}
		return null;
	}

	@Override
	public void updatePassword(Integer id, String newPassword) {
		User user = this.selectByKey(id);
		if (user != null) {
			user.setPassword(newPassword);
			this.update(user);
		}
	}

	@Override
	public void save(User user, List<Integer> roleIdList) {
		this.save(user);
		updateUserRole(user.getId(),roleIdList);
	}

	@Override
	public void update(User user, List<Integer> roleIdList) {
		this.updateNotNull(user);
		updateUserRole(user.getId(),roleIdList);
	}

	private void updateUserRole(Integer userId,  List<Integer> roleIdList){
		List<UserRole> userRoleList = new ArrayList<UserRole>();
		UserRole bean;
		for (Integer roleId : roleIdList) {
			bean = new UserRole();
			bean.setUserId(userId);
			bean.setRoleId(roleId);
			userRoleList.add(bean);
		}
		userRoleService.allocate(userId, userRoleList);
	}
}
