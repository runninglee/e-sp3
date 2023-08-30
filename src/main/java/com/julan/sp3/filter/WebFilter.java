package com.julan.sp3.filter;


import jakarta.servlet.*;

import java.io.IOException;

public class WebFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("WEB过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("在WEB请求到达控制器之前执行的代码");
        // 执行下一个过滤器或控制器
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("在WEB响应离开控制器之后执行的代码");
    }

    @Override
    public void destroy() {
        System.out.println("在WEB响应离开控制器之后执行的代码");
    }
}
