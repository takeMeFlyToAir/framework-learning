package com.zzr.sso.core.filter;

import com.zzr.sso.core.conf.Config;
import com.zzr.sso.core.login.SsoWebLoginHelper;
import com.zzr.sso.core.path.impl.AntPathMatcher;
import com.zzr.sso.core.user.ZzrSsoUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhaozhirong on 2019/5/17.
 */
public class ZzrSsoWebFilter extends HttpServlet implements Filter {

    private static Logger logger = LoggerFactory.getLogger(ZzrSsoWebFilter.class);

    private static final AntPathMatcher antPathMatcher = new AntPathMatcher();


    private String ssoServer;
    private String logoutPath;
    private String excludedPaths;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ssoServer = filterConfig.getInitParameter(Config.SSO_SERVER);
        logoutPath = filterConfig.getInitParameter(Config.SSO_LOGOUT_PATH);
        excludedPaths = filterConfig.getInitParameter(Config.SSO_EXCLUDED_PATHS);

        logger.info("ZzrSsoWebFilter init.");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String servletPath = req.getServletPath();

        //不需要过滤的路径
        if (excludedPaths != null && excludedPaths.trim().length() > 0) {
            for (String excludedPath : excludedPaths.split(",")) {
                String uriPattern = excludedPath.trim();

                // 支持ANT表达式
                if (antPathMatcher.match(uriPattern, servletPath)) {
                    // excluded path, allow
                    chain.doFilter(request, response);
                    return;
                }
            }
        }

        //退出
        if(logoutPath != null && logoutPath.trim().length() > 0 && logoutPath.equals(servletPath)){
            //移除cookie
            SsoWebLoginHelper.removeSessionIdOnCookie(req,res);

            //重定向到退出
            String logoutPageUrl = ssoServer.concat(Config.SSO_LOGOUT);
            res.sendRedirect(logoutPageUrl);
            return;
        }

        //验证用户的有效性
        ZzrSsoUser zzrSsoUser = SsoWebLoginHelper.loginCheck(req,res);
        if (zzrSsoUser == null) {
            String header = req.getHeader("content-type");
            boolean isJson=  header!=null && header.contains("json");
            if (isJson){
                //发送json信息
                res.setContentType("application/json;charset=utf-8");
                res.getWriter().println("{\"code\":"+Config.SSO_LOGIN_FAIL_RESULT.getCode()+", \"msg\":\""+ Config.SSO_LOGIN_FAIL_RESULT.getMsg() +"\"}");
                return;
            }else {
                String link = req.getRequestURL().toString();
                String loginPageUrl = ssoServer.concat(Config.SSO_LOGIN)
                        + "?" + Config.REDIRECT_URL + "=" + link;
                res.sendRedirect(loginPageUrl);
                return;
            }
        }

        //设置用户信息
        req.setAttribute(Config.SSO_USER,zzrSsoUser);
        //允许访问
        chain.doFilter(request,response);
        return;
    }
}
