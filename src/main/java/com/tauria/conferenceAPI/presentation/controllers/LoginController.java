package com.tauria.conferenceAPI.presentation.controllers;

import com.tauria.conferenceAPI.infrastructure.services.LoginService;
import com.tauria.conferenceAPI.models.commands.user.CreateUserCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping(path = "login/callback")
    public void oktaLogin_Callback (@AuthenticationPrincipal OidcUser user){
        try{
                var userName = user.getUserInfo().getEmail();
                var firstName = user.getGivenName();
                var lastName  = user.getFamilyName();

                var command = new CreateUserCommand(userName,lastName,firstName);
                loginService.userLoginCallBack(command);

        }
        catch(Exception ex){
            //todo log exception
        }
    }
}
