package com.tauria.conferenceAPI.infrastructure.configuration.business;

import com.tauria.conferenceAPI.appliation.stdServiceImpl.ApplicationUserServiceImpl;
import com.tauria.conferenceAPI.appliation.stdServiceImpl.ConferenceRoomServiceImpl;
import com.tauria.conferenceAPI.appliation.stdServiceImpl.LoginServiceImpl;
import com.tauria.conferenceAPI.appliation.stdServiceImpl.TeamServiceImpl;
import com.tauria.conferenceAPI.infrastructure.aop.MethodLoggingAdvice;
import com.tauria.conferenceAPI.infrastructure.aop.ThrowsErrorAdvice;
import com.tauria.conferenceAPI.infrastructure.repositories.*;
import com.tauria.conferenceAPI.infrastructure.services.AppUserService;
import com.tauria.conferenceAPI.infrastructure.services.ConferenceRoomService;
import com.tauria.conferenceAPI.infrastructure.services.LoginService;
import com.tauria.conferenceAPI.infrastructure.services.TeamService;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class ServiceConfiguration {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private ConferenceRoomRepository conferenceRoomRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private RoomParticipationRepository roomParticipationRepository;


    @Lazy
    @Bean
    public TeamService teamService(){

        var teamServiceImpl =  new TeamServiceImpl(teamRepository,appUserRepository);

        //sets service implementation proxy
        // to add logging advice using aop
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new MethodLoggingAdvice());
        proxyFactory.addAdvice(new ThrowsErrorAdvice());
        proxyFactory.setTarget(teamServiceImpl);

        var  proxy = (TeamServiceImpl) proxyFactory.getProxy();
        return proxy ;
    }

    @Lazy
    @Bean
    public AppUserService appUserService(){

        var appUserServiceImpl =  new ApplicationUserServiceImpl(appUserRepository);

        //sets service implementation proxy
        // to add logging advice using aop
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new MethodLoggingAdvice());
        proxyFactory.addAdvice(new ThrowsErrorAdvice());
        proxyFactory.setTarget(appUserServiceImpl);

        var  proxy = (ApplicationUserServiceImpl) proxyFactory.getProxy();
        return proxy ;
    }

    @Lazy
    @Bean
    public LoginService loginService(){

        var loginServiceImpl =  new LoginServiceImpl(appUserRepository, authorityRepository);

        //sets service implementation proxy
        // to add logging advice using aop
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new MethodLoggingAdvice());
        proxyFactory.addAdvice(new ThrowsErrorAdvice());
        proxyFactory.setTarget(loginServiceImpl);

        var  proxy = (LoginServiceImpl) proxyFactory.getProxy();
        return proxy ;
    }

    @Lazy
    @Bean
    public ConferenceRoomService conferenceRoomService(){

        var conferenceRoomServiceImpl =  new ConferenceRoomServiceImpl(teamRepository,
                conferenceRoomRepository,appUserRepository,roomParticipationRepository,
                authorityRepository);

        //sets service implementation proxy
        // to add logging advice using aop
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new MethodLoggingAdvice());
        proxyFactory.addAdvice(new ThrowsErrorAdvice());
        proxyFactory.setTarget(conferenceRoomServiceImpl);

        var  proxy = (ConferenceRoomServiceImpl) proxyFactory.getProxy();
        return proxy ;
    }
}
