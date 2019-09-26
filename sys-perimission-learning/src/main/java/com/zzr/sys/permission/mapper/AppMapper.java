package com.zzr.sys.permission.mapper;

import com.zzr.sys.permission.core.MyMapper;
import com.zzr.sys.permission.entity.App;

import java.util.List;
import java.util.Map;

/**
 * 应用持久化接口
 * 
 */
public interface AppMapper extends MyMapper<App> {
	List<App> findPageList(Map<String,Object> params);
}
