package com.zzr.sso.core.store;

import com.zzr.sso.core.conf.Config;
import com.zzr.sso.core.user.ZzrSsoUser;
import com.zzr.sso.core.util.JedisUtil;

/**
 * Created by zhaozhirong on 2019/5/17.
 */
public class SsoLoginStore {

    private static int redisExpireMinute = 1440;

    public static void setRedisExpireMinute(int redisExpireMinute){
        if(redisExpireMinute < 30){
            redisExpireMinute = 30;
        }
        SsoLoginStore.redisExpireMinute = redisExpireMinute;
    }

    public static int getRedisExpireMinute(){
        return redisExpireMinute;
    }

    public static ZzrSsoUser get(String storeKey){
        String redisKey = redisKey(storeKey);
        Object objectValue = JedisUtil.getObjectValue(redisKey);
        if (objectValue != null) {
            return (ZzrSsoUser) objectValue;
        }
        return null;
    }

    public static void remove(String storeKey){
        String redisKey = redisKey(storeKey);
        JedisUtil.del(redisKey);
    }

    public static void put(String storeKey,ZzrSsoUser zzrSsoUser){
        String redisKey = redisKey(storeKey);
        JedisUtil.setObjectValue(redisKey,zzrSsoUser,redisExpireMinute * 60);//分钟转为秒
    }

    private static String redisKey(String storeKey){
        return Config.SSO_SESSIONID.concat("#").concat(storeKey);
    }

}
