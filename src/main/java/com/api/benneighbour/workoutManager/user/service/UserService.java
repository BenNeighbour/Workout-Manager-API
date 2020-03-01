package com.api.benneighbour.workoutManager.user.service;

import com.api.benneighbour.workoutManager.exceptions.EmailAlreadyTakenException;
import com.api.benneighbour.workoutManager.exceptions.EmailNotFoundException;
import com.api.benneighbour.workoutManager.exceptions.EmailUnreachableException;
import com.api.benneighbour.workoutManager.user.entity.User;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;

import java.util.List;

public interface UserService {

    User saveUser(User user) throws EmailAlreadyTakenException, EmailUnreachableException;

    User updateUser(User user);

    User resetPassword(User user) throws EmailNotFoundException;

    List<User> getAllUsers();

    User getUserByName(String username);

    void deleteUser(Long uid) throws UnauthorizedUserException;

}
