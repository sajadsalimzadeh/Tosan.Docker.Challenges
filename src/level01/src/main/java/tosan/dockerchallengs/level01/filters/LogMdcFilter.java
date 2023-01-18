package tosan.dockerchallengs.level01.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Sajad Salimzadeh
 * @since 1/18/2023
 */
@Component
@Order(1)
public class LogMdcFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        if(servletResponse instanceof HttpServletResponse httpServletResponse) {
            MDC.put("request.id", "" + servletRequest.hashCode());
            MDC.put("request.status", "" + httpServletResponse.getStatus());
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
