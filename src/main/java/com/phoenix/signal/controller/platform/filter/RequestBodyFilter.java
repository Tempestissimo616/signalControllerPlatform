package com.phoenix.signal.controller.platform.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;

@WebFilter("/*")
public class RequestBodyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        StringBuilder body = new StringBuilder();
        BufferedReader bufferedReader = httpRequest.getReader();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            body.append(line);
        }

        // 将请求体字符串存储为 request 属性
        httpRequest.setAttribute("requestBody", body.toString());

        // 继续请求处理链
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
