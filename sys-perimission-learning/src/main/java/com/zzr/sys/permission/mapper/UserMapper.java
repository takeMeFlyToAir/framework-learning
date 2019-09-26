package com.zzr.sys.permission.mapper;

import com.zzr.sys.permission.core.MyMapper;
import com.zzr.sys.permission.entity.App;
import com.zzr.sys.permission.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 用户持久化接口
 * 
 */
public interface UserMapper extends MyMapper<User> {
    List<App> findPageList(Map<String,Object> params);
    int enable(@Param("isEnable") Boolean isEnable, @Param("idList") List<Integer> idList);
}
