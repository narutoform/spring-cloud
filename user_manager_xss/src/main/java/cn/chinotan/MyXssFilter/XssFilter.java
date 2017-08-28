package cn.chinotan.MyXssFilter;

import cn.chinotan.xss.XssHttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
// @Component
@WebFilter(filterName = "XssFilter", urlPatterns = "/*", asyncSupported = true)
public class XssFilter implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("filter_init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.debug("filter_do");
        XssHttpServletRequestWrapper requestWrapper = new XssHttpServletRequestWrapper((HttpServletRequest) request);
        chain.doFilter(requestWrapper, response);
    }

    @Override
    public void destroy() {
        log.debug("filter_destroy");
    }
}
