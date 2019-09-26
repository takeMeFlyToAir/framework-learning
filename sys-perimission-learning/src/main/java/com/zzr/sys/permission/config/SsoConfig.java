package com.zzr.sys.permission.config;

import com.zzr.sso.core.util.JedisUtil;
import com.zzr.sys.permission.util.MyPropertiesUtil;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * Created by zhaozhirong on 2019/5/17.
 */
@Component
public class SsoConfig implements InitializingBean,DisposableBean{

    private String zzrSsoRedisAddress;

    @Override
    public void destroy() throws Exception {
       JedisUtil.close();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("===========================afterPropertiesSet======================");
        Properties properties = MyPropertiesUtil.load("redis.properties");
        zzrSsoRedisAddress = properties.getProperty("zzr.sso.redis.address");
        JedisUtil.init(zzrSsoRedisAddress);

    }
}
