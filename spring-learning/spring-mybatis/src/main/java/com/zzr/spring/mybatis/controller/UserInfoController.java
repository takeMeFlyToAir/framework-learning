package com.zzr.spring.mybatis.controller;

import com.zzr.sso.core.conf.Config;
import com.zzr.sso.core.user.ZzrSsoUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhaozhirong on 2019/5/17.
 */
@RestController
public class UserInfoController {

    @RequestMapping(value = "/userInfo",method = {RequestMethod.GET})
    public ZzrSsoUser getUserInfo(HttpServletRequest request){
        ZzrSsoUser zzrSsoUser = (ZzrSsoUser) request.getAttribute(Config.SSO_USER);
        return zzrSsoUser;
    }

}
