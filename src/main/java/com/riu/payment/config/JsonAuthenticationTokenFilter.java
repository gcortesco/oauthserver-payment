package com.riu.payment.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Configuration
@Order(1)
public class JsonAuthenticationTokenFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonAuthenticationTokenFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("########## Initiating Custom filter ##########");
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        LOGGER.info("Logging Request  {} : {}", request.getMethod(), request.getRequestURI());
        //call next filter in the filter chain
        if (APPLICATION_JSON.toString().equals( request.getContentType())) {
           request = new CustomWrappedRequest(request);
        }
        filterChain.doFilter(request, response);
        LOGGER.info("Logging Response :{}", response.getContentType());
    }
}