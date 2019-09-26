package com.zzr.sso.core.login;

import com.zzr.sso.core.conf.Config;
import com.zzr.sso.core.store.SsoLoginStore;
import com.zzr.sso.core.store.SsoSessionIdHelper;
import com.zzr.sso.core.user.ZzrSsoUser;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhaozhirong on 2019/5/17.
 */
public class SsoTokenLoginHelper {

    /**
     * 登录
     * @param sessionId
     * @param zzrSsoUser
     */
    public static void login(String sessionId, ZzrSsoUser zzrSsoUser){
        String storeKey = SsoSessionIdHelper.parseStoreKey(sessionId);
        if (storeKey == null) {
            throw new RuntimeException("parseStoreKey Fail, sessionId:"+sessionId);
        }
        SsoLoginStore.put(storeKey,zzrSsoUser);
    }

    /**
     * 退出
     * @param sessionId
     */
    public static void logout(String sessionId){
        String storeKey = SsoSessionIdHelper.parseStoreKey(sessionId);
        if (storeKey == null) {
            return;
        }
        SsoLoginStore.remove(storeKey);
    }

    /**
     * 退出，sessionId存在请求头里
     * @param request
     */
    public static void logout(HttpServletRequest request){
        String headerSessionId = request.getHeader(Config.SSO_SESSIONID);
        logout(headerSessionId);
    }


    public static ZzrSsoUser loginCheck(String sessionId){
        return SsoLoginHelper.loginCheck(sessionId);
    }

    public static ZzrSsoUser loginCheck(HttpServletRequest request){
        String headerSessionId = request.getHeader(Config.SSO_SESSIONID);
        return loginCheck(headerSessionId);
    }

}
