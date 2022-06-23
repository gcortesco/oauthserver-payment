package com.riu.payment.config;



import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;


import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



public class CustomClientAuthenticationFilter  extends AbstractAuthenticationProcessingFilter  {

    public CustomClientAuthenticationFilter (RequestMatcher requestMatcher) {
        super(requestMatcher);
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        this.setAuthenticationSuccessHandler((request1, response1, authentication) -> {
            chain.doFilter(request1, response1);
        });

        super.doFilter(request, response, chain);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken("", "");
        return this.getAuthenticationManager().authenticate(authRequest);

    }







    }