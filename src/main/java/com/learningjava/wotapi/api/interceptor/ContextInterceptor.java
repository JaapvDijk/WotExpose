package com.learningjava.wotapi.api.interceptor;

import com.learningjava.wotapi.infrastructure.HttpContext;
import com.learningjava.wotapi.shared.constant.Region;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ContextInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String region = request.getParameter("region");
        if (region != null) {
            HttpContext.setRegion(Region.fromCode(region));
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        HttpContext.clear();
    }
}
