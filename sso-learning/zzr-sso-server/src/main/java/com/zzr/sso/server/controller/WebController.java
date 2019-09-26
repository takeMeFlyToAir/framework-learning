package com.zzr.sso.server.controller;

import com.zzr.sso.core.conf.Config;
import com.zzr.sso.core.login.SsoWebLoginHelper;
import com.zzr.sso.core.store.SsoLoginStore;
import com.zzr.sso.core.store.SsoSessionIdHelper;
import com.zzr.sso.core.user.ZzrSsoUser;
import com.zzr.sso.server.core.User;
import com.zzr.sso.server.core.UserService;
import com.zzr.sso.server.core.result.ReturnT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Created by zhaozhirong on 2019/5/17.
 */
@Controller
public class WebController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(Model model, HttpServletRequest request, HttpServletResponse response) {
        // 登录检查
        ZzrSsoUser zzrSsoUser = SsoWebLoginHelper.loginCheck(request, response);
        if (zzrSsoUser == null) {
            return "redirect:/login";
        } else {
            model.addAttribute("zzrSsoUser", zzrSsoUser);
            return "index";
        }
    }

    /**
     * 跳转到登录页
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(Config.SSO_LOGIN)
    public String login(Model model, HttpServletRequest request, HttpServletResponse response) {
        // 登录检查
        ZzrSsoUser zzrSsoUser = SsoWebLoginHelper.loginCheck(request, response);

        if (zzrSsoUser != null) {
            // 重定向
            String redirectUrl = request.getParameter(Config.REDIRECT_URL);
            if (redirectUrl!=null && redirectUrl.trim().length()>0) {
                String sessionId = SsoWebLoginHelper.getSessionIdOnCookie(request);
                String redirectUrlFinal = redirectUrl + "?" + Config.SSO_SESSIONID + "=" + sessionId;
                return "redirect:" + redirectUrlFinal;
            } else {
                return "redirect:/";
            }
        }
        model.addAttribute("errorMsg", request.getParameter("errorMsg"));
        model.addAttribute(Config.REDIRECT_URL, request.getParameter(Config.REDIRECT_URL));
        return "login";
    }

    @RequestMapping("/doLogin")
    public String doLogin(HttpServletRequest request,
                          HttpServletResponse response,
                          RedirectAttributes redirectAttributes,
                          String username,
                          String password,
                          String ifRemember) {
        boolean ifRem = (ifRemember!=null&&"on".equals(ifRemember))?true:false;

        ReturnT<User> result = userService.userLogin(username,password);
        if(result.getCode() != ReturnT.SUCCESS_CODE){
            redirectAttributes.addAttribute("errorMsg", result.getMsg());
            redirectAttributes.addAttribute(Config.REDIRECT_URL, request.getParameter(Config.REDIRECT_URL));
            return "redirect:/login";
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
        SsoWebLoginHelper.login(response,sessionId,zzrSsoUser,ifRem);

        //返回
        String redirectUrl = request.getParameter(Config.REDIRECT_URL);
        if (redirectUrl!=null && redirectUrl.trim().length()>0) {
            String redirectUrlFinal = redirectUrl + "?" + Config.SSO_SESSIONID + "=" + sessionId;
            return "redirect:" + redirectUrlFinal;
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(Config.SSO_LOGOUT)
    public String logout(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {

        SsoWebLoginHelper.logout(request, response);
        redirectAttributes.addAttribute(Config.REDIRECT_URL, request.getParameter(Config.REDIRECT_URL));
        return "redirect:/login";
    }

}
