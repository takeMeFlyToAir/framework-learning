package com.zzr.sso.core.login;

import com.zzr.sso.core.conf.Config;
import com.zzr.sso.core.store.SsoLoginStore;
import com.zzr.sso.core.store.SsoSessionIdHelper;
import com.zzr.sso.core.user.ZzrSsoUser;
import com.zzr.sso.core.util.CookieUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhaozhirong on 2019/5/17.
 */
public class SsoWebLoginHelper {

    /**
     * 登录
     * @param response
     * @param sessionId
     * @param zzrSsoUser
     * @param isRemember
     */
    public static void login(HttpServletResponse response, String sessionId, ZzrSsoUser zzrSsoUser,boolean isRemember){
        String storeKey = SsoSessionIdHelper.parseStoreKey(sessionId);
        if (storeKey == null) {
            throw new RuntimeException("parseStoreKey Fail, sessionId:"+sessionId);
        }
        SsoLoginStore.put(storeKey,zzrSsoUser);
        CookieUtil.set(response, Config.SSO_SESSIONID,sessionId,isRemember);
    }


    /**
     * 退出
     * @param request
     * @param response
     */
    public static void logout(HttpServletRequest request, HttpServletResponse response){
        String sessionId = CookieUtil.getValue(request,Config.SSO_SESSIONID);
        if (sessionId == null) {
            return;
        }
        String storeKey = SsoSessionIdHelper.parseStoreKey(sessionId);
        if (storeKey != null) {
            SsoLoginStore.remove(storeKey);
        }
        CookieUtil.remove(request,response,Config.SSO_SESSIONID);
    }

    public static ZzrSsoUser loginCheck(HttpServletRequest request, HttpServletResponse response){
        String sessionId = CookieUtil.getValue(request,Config.SSO_SESSIONID);
        ZzrSsoUser zzrSsoUser  = SsoLoginHelper.loginCheck(sessionId);
        if (zzrSsoUser != null) {
            return zzrSsoUser;
        }

        //移除旧的cookie
        SsoWebLoginHelper.removeSessionIdOnCookie(request,response);
        //设置新的cookie，从参数中获取到 sessionId
        String paramSessionId =request.getParameter(Config.SSO_SESSIONID);
        zzrSsoUser = SsoLoginHelper.loginCheck(paramSessionId);
        if (zzrSsoUser != null) {
            CookieUtil.set(response, Config.SSO_SESSIONID,paramSessionId,false);
            return zzrSsoUser;
        }
        return null;

    }

    /**
     * 用户退出移除cookie
     * @param request
     * @param response
     */
    public static void removeSessionIdOnCookie(HttpServletRequest request,HttpServletResponse response){
        CookieUtil.remove(request,response,Config.SSO_SESSIONID);
    }

    /**
     * 从cookie中获取sessionId
     * @param request
     * @return
     */
    public static String getSessionIdOnCookie(HttpServletRequest request){
        return CookieUtil.getValue(request,Config.SSO_SESSIONID);
    }
}
