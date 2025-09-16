package com.learningjava.wotexpose.api.interceptor;

import com.learningjava.wotexpose.infrastructure.HttpContext;
import com.learningjava.wotexpose.shared.constant.RegionType;
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
            HttpContext.setRegion(RegionType.fromCode(region));
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        HttpContext.clear();
    }
}
