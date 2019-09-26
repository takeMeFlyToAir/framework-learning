package com.zzr.sso.core.conf;

import com.zzr.sso.core.entity.ReturnResult;

/**
 * Created by zhaozhirong on 2019/5/17.
 */
public class Config {

    /**
     * sso sessionid  用于浏览器和认证中心的信息交互
     */
    public static final String SSO_SESSIONID = "zzr_sso_sessionid";

    /**
     * web端重定向地址
     */
    public static final String REDIRECT_URL = "redirect_url";

    /**
     * 存储用户信息的key，用于web端
     */
    public static final String SSO_USER = "zzr_soo_user";

    /**
     * 单点登录的认证中心地址
     */
    public static final String SSO_SERVER = "sso_server";

    /**
     * 单点登录地址
     */
    public static final String SSO_LOGIN = "/login";

    /**
     * 单点登录退出地址
     */
    public static final String SSO_LOGOUT = "/logout";


    public static final String SSO_LOGOUT_PATH = "SSO_LOGOUT_PATH";

    /**
     * 不需拦截的地址
     */
    public static final String SSO_EXCLUDED_PATHS = "SSO_EXCLUDED_PATHS";

    /**
     * login fail result
     */
    public static final ReturnResult<String> SSO_LOGIN_FAIL_RESULT = new ReturnResult(501, "sso not login.");


}
