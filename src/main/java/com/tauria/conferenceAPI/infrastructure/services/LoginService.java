package com.tauria.conferenceAPI.infrastructure.services;

import com.tauria.conferenceAPI.models.commands.user.CreateUserCommand;

public interface LoginService {
    void userLoginCallBack(CreateUserCommand command);
}
