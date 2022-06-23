package com.riu.payment.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    private String realm = "oauth2/client";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .csrf().disable()
                .addFilterAfter(createCustomFilter(), AnonymousAuthenticationFilter.class)
        ;
    }

    protected AbstractAuthenticationProcessingFilter createCustomFilter() throws Exception {
        CustomClientAuthenticationFilter  filter = new CustomClientAuthenticationFilter (
                new AndRequestMatcher(
                        new AntPathRequestMatcher("/oauth/token/**")
                )
        );
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }


}
