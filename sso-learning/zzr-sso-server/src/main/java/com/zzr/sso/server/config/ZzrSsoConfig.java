package com.zzr.sso.server.config;

import com.zzr.sso.core.store.SsoLoginStore;
import com.zzr.sso.core.util.JedisUtil;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zhaozhirong on 2019/5/17.
 */

@Configuration
public class ZzrSsoConfig implements InitializingBean,DisposableBean {

    @Value("${zzr.sso.redis.address}")
    private String redisAddress;

    @Value("${zzr.sso.redis.expire.minute}")
    private int redisExpireMinute;


    @Override
    public void afterPropertiesSet() throws Exception {
        SsoLoginStore.setRedisExpireMinute(redisExpireMinute);
        JedisUtil.init(redisAddress);
    }

    @Override
    public void destroy() throws Exception {
        JedisUtil.close();
    }

}
