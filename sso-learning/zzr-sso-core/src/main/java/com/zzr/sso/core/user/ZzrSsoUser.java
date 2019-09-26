package com.zzr.sso.core.user;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by zhaozhirong on 2019/5/17.
 * 用户信息
 */
public class ZzrSsoUser implements Serializable{


    private String userId;

    private String userName;

    private Map<String, String> ext;

    private String version;

    private int expireMinute;

    private long expireFreshTime;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Map<String, String> getExt() {
        return ext;
    }

    public void setExt(Map<String, String> ext) {
        this.ext = ext;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getExpireMinute() {
        return expireMinute;
    }

    public void setExpireMinute(int expireMinute) {
        this.expireMinute = expireMinute;
    }

    public long getExpireFreshTime() {
        return expireFreshTime;
    }

    public void setExpireFreshTime(long expireFreshTime) {
        this.expireFreshTime = expireFreshTime;
    }
}
