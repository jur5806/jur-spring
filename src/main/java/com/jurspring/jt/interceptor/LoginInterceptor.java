package com.jurspring.jt.interceptor;

import com.jurspring.jt.home.User;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public  boolean preHandle (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception{
//        HttpSession session = httpServletRequest.getSession();
//        String contextPath = session.getServletContext().getContextPath();
//        String[] requireAuthPages = new String[]{
//                "index",
//        };
//        String uri = httpServletRequest.getRequestURI();
//
//        uri = StringUtils.remove(uri, contextPath+"/");
//        String page = uri;
//
//        if(beginWith(page, requireAuthPages)){
//            User user = (User) session.getAttribute("user");
//            if(user == null){
//                httpServletResponse.sendRedirect("login");
//                return false;
//            }
//
//        }
//        return true;
        // 放行 options 请求，否则无法让前端带上自定义的 header 信息，导致 sessionID 改变，shiro 验证失败
        if (HttpMethod.OPTIONS.toString().equals(httpServletRequest.getMethod())) {
            httpServletResponse.setStatus(HttpStatus.NO_CONTENT.value());
            return true;
        }

        Subject subject = SecurityUtils.getSubject();
        // 使用 shiro 验证
        if (!subject.isAuthenticated()) {
            return false;
        }
        return true;
    }

    private boolean beginWith(String page, String[] requireAuthPages) {
        boolean result = false;
        for (String requiredAuthPage : requireAuthPages) {
            if(StringUtils.startsWith(page, requiredAuthPage)){
                result = true;
                break;
            };
        };
        return result;
    }
}
