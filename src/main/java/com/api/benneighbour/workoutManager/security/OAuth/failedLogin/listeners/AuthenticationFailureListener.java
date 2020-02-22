package com.api.benneighbour.workoutManager.security.OAuth.failedLogin.listeners;

import com.api.benneighbour.workoutManager.user.dao.UserDao;
import com.api.benneighbour.workoutManager.user.entity.User;
import com.api.benneighbour.workoutManager.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class AuthenticationFailureListener {

//    @Autowired
//    private UserService service;
//
//    @Autowired
//    private UserDao dao;
//
//    private int attempts = 0;
//
//    @Override
//    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent authenticationFailureBadCredentialsEvent) {
////        String username = authenticationFailureBadCredentialsEvent.getAuthentication().getPrincipal().toString();
////        attempts++;
//
//
//    }

}
