package com.zzr.sso.core.filter;

import com.zzr.sso.core.conf.Config;
import com.zzr.sso.core.entity.ReturnResult;
import com.zzr.sso.core.login.SsoTokenLoginHelper;
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
 * app sso filter
 *
 * @author xuxueli 2018-04-08 21:30:54
 */
public class ZzrSsoTokenFilter extends HttpServlet implements Filter {
    private static Logger logger = LoggerFactory.getLogger(ZzrSsoTokenFilter.class);

    private static final AntPathMatcher antPathMatcher = new AntPathMatcher();

    private String ssoServer;
    private String logoutPath;
    private String excludedPaths;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        ssoServer = filterConfig.getInitParameter(Config.SSO_SERVER);
        logoutPath = filterConfig.getInitParameter(Config.SSO_LOGOUT_PATH);
        excludedPaths = filterConfig.getInitParameter(Config.SSO_EXCLUDED_PATHS);

        logger.info("ZzrSsoTokenFilter init.");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String servletPath = req.getServletPath();

        //不需要过滤的路径
        if (excludedPaths!=null && excludedPaths.trim().length()>0) {
            for (String excludedPath:excludedPaths.split(",")) {
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
        if (logoutPath!=null
                && logoutPath.trim().length()>0
                && logoutPath.equals(servletPath)) {

            // logout
            SsoTokenLoginHelper.logout(req);

            // response
            res.setStatus(HttpServletResponse.SC_OK);
            res.setContentType("application/json;charset=UTF-8");
            res.getWriter().println("{\"code\":"+ ReturnResult.SUCCESS_CODE+", \"msg\":\"\"}");

            return;
        }

        //验证用户的有效性
        ZzrSsoUser zzrSsoUser = SsoTokenLoginHelper.loginCheck(req);
        if (zzrSsoUser == null) {

            res.setStatus(HttpServletResponse.SC_OK);
            res.setContentType("application/json;charset=UTF-8");
            res.getWriter().println("{\"code\":"+ Config.SSO_LOGIN_FAIL_RESULT.getCode()+", \"msg\":\""+ Config.SSO_LOGIN_FAIL_RESULT.getMsg() +"\"}");
            return;
        }

        //设置用户信息
        request.setAttribute(Config.SSO_USER, zzrSsoUser);


        //允许访问
        chain.doFilter(request, response);
        return;
    }


}
