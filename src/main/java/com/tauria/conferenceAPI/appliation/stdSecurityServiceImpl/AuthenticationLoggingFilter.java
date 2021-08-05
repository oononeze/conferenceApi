package com.tauria.conferenceAPI.appliation.stdSecurityServiceImpl;

import com.tauria.conferenceAPI.appliation.stdServiceImpl.LoginServiceImpl;
import com.tauria.conferenceAPI.infrastructure.repositories.AppUserRepository;
import com.tauria.conferenceAPI.infrastructure.repositories.AuthorityRepository;
import com.tauria.conferenceAPI.models.commands.user.CreateUserCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@Service
public class AuthenticationLoggingFilter extends OncePerRequestFilter {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    private final Logger logger =
            Logger.getLogger(
                    AuthenticationLoggingFilter.class.getName());

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        var httpRequest = (HttpServletRequest) request;
        StringBuilder authorities = new StringBuilder();

        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        auth.getAuthorities().forEach(x -> authorities.append(x.getAuthority()).append(","));

        var userName = ((OAuth2User) auth.getPrincipal()).getAttribute("email");
        var firstName = ((OAuth2User) auth.getPrincipal()).getAttribute("given_name");
        var lastName = ((OAuth2User) auth.getPrincipal()).getAttribute("family_name");

        var userService = new LoginServiceImpl(appUserRepository,authorityRepository);

        userService.userLoginCallBack(new CreateUserCommand(userName.toString(),
                lastName.toString(),firstName.toString()));

        logger.info("Successfully authenticated user: " +
                auth.getPrincipal() + " with Roles: " +
                authorities + " to resource: " +
                httpRequest.getRequestURL());

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request){
        return false;
    }
}
