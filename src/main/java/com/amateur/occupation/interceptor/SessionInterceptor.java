package com.amateur.occupation.interceptor;

import com.amateur.occupation.constant.Const;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

w

@Slf4j
public class SessionInterceptor implements HandlerInterceptor {
    @Value("${occupation.runtime}")
    private boolean runtime;
    @Value("${occupation.email}")
    private String email;


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) {
        HttpSession session = httpServletRequest.getSession();
        if (!runtime) {
            log.debug("debug mode test user: " + email);
            if (session.getAttribute(Const.ID_KEY) == null) {
                session.setAttribute(Const.ID_KEY, Const.TEST_EMAIL);
            }
            if (session.getAttribute(Const.USER_TYPE_KEY) == null) {
                session.setAttribute(Const.USER_TYPE_KEY, Const.TEST_USER_TYPE);
            }
        }
        return session != null && session.getAttribute(Const.ID_KEY) != null;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

    }

}
