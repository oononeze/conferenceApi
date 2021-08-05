package com.tauria.conferenceAPI.infrastructure.configuration.Security;

import com.tauria.conferenceAPI.appliation.stdSecurityServiceImpl.AuthenticationLoggingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;


import java.util.List;

@Configuration
public class AppSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationLoggingFilter authenticationLoggingFilter;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.addFilterAfter(authenticationLoggingFilter,
                OAuth2LoginAuthenticationFilter.class);

        http.authorizeRequests()

                .antMatchers("/login/*")
                .permitAll()
                .antMatchers("/guest/*")
                .permitAll()

                .anyRequest()
                .authenticated()
                .and().oauth2Login()
                .and().oauth2Client();
    }
}

