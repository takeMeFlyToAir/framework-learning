package com.zzr.sso.server.controller;

import com.zzr.sso.core.login.SsoTokenLoginHelper;
import com.zzr.sso.core.store.SsoLoginStore;
import com.zzr.sso.core.store.SsoSessionIdHelper;
import com.zzr.sso.core.user.ZzrSsoUser;
import com.zzr.sso.server.core.User;
import com.zzr.sso.server.core.UserService;
import com.zzr.sso.server.core.result.ReturnT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

/**
 * Created by zhaozhirong on 2019/5/17.
 */
@Controller
@RequestMapping("/app")
public class AppController {

    @Autowired
    private UserService userService;


    @RequestMapping("/login")
    @ResponseBody
    public ReturnT<String> login(String username, String password) {

        //登录校验
        ReturnT<User> result = userService.userLogin(username, password);
        if (result.getCode() != ReturnT.SUCCESS_CODE) {
            return new ReturnT<String>(result.getCode(), result.getMsg());
        }

        //用户信息
        ZzrSsoUser zzrSsoUser = new ZzrSsoUser();
        zzrSsoUser.setUserId(String.valueOf(result.getData().getId()));
        zzrSsoUser.setUserName(result.getData().getAccount());
        zzrSsoUser.setVersion(UUID.randomUUID().toString().replaceAll("-", ""));
        zzrSsoUser.setExpireMinute(SsoLoginStore.getRedisExpireMinute());
        zzrSsoUser.setExpireFreshTime(System.currentTimeMillis());


        //创建sessionId
        String sessionId = SsoSessionIdHelper.createSessionId(zzrSsoUser);

        //保存登录信息
        SsoTokenLoginHelper.login(sessionId, zzrSsoUser);

        //返回
        return new ReturnT<String>(sessionId);
    }



    @RequestMapping("/logout")
    @ResponseBody
    public ReturnT<String> logout(String sessionId) {
        SsoTokenLoginHelper.logout(sessionId);
        return ReturnT.SUCCESS;
    }

    @RequestMapping("/logincheck")
    @ResponseBody
    public ReturnT<ZzrSsoUser> logincheck(String sessionId) {
        ZzrSsoUser xxlUser = SsoTokenLoginHelper.loginCheck(sessionId);
        if (xxlUser == null) {
            return new ReturnT<ZzrSsoUser>(ReturnT.FAIL_CODE, "sso not login.");
        }
        return new ReturnT<ZzrSsoUser>(xxlUser);
    }

}