package com.loginandregistration.loginandregistration.services;

import com.loginandregistration.loginandregistration.Dto.registrationdto;
import com.loginandregistration.loginandregistration.model.User;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface Userservice extends UserDetailsService{
    User save(registrationdto userdto);
}


