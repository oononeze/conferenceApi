package com.tauria.conferenceAPI.appliation.stdServiceImpl;

import com.tauria.conferenceAPI.infrastructure.exception.ErrorMessage;
import com.tauria.conferenceAPI.infrastructure.exception.ItemAlreadyExistsException;
import com.tauria.conferenceAPI.infrastructure.exception.ItemNotSavedException;
import com.tauria.conferenceAPI.infrastructure.repositories.AppUserRepository;
import com.tauria.conferenceAPI.infrastructure.repositories.AuthorityRepository;
import com.tauria.conferenceAPI.infrastructure.services.LoginService;
import com.tauria.conferenceAPI.infrastructure.util.UserTypes;
import com.tauria.conferenceAPI.models.applicationEntities.AppUser;
import com.tauria.conferenceAPI.models.applicationEntities.Authority;
import com.tauria.conferenceAPI.models.commands.user.CreateUserCommand;

import java.util.Optional;

public class LoginServiceImpl implements LoginService {

    private AppUserRepository appUserRepository;
    private AuthorityRepository authorityRepository;

    public LoginServiceImpl(AppUserRepository appUserRepository,
                                      AuthorityRepository authorityRepository){
        this.appUserRepository = appUserRepository;
        this.authorityRepository = authorityRepository;
    }


    @Override
    public void userLoginCallBack(CreateUserCommand command) {

        var user = new AppUser(command.getFirstName(),
                command.getLastName(), command.getUserName());

        Optional existingUser = appUserRepository.findById(command.getUserName());

        if(existingUser.isPresent())
            return;

        var savedUser = appUserRepository.save(user);

        if(savedUser == null)
            throw new ItemNotSavedException(ErrorMessage.USER_NOT_SAVED);

        Authority userAuthority = new Authority(savedUser.getUserName(), UserTypes.USER);

        var savedAuthority = authorityRepository.save(userAuthority);

        if(savedAuthority == null)
            throw new ItemNotSavedException(ErrorMessage.AUTHORITY_NOT_SAVED);

    }
}
