package com.api.benneighbour.workoutManager.user.service;

import com.api.benneighbour.workoutManager.exceptions.EmailAlreadyTakenException;
import com.api.benneighbour.workoutManager.user.entity.User;

import java.util.List;

public interface UserService {

    User saveUser(User user) throws EmailAlreadyTakenException;

    User updateUser(User user);

    List<User> getAllUsers();

    User getUserByName(String username);

    void deleteUser(Long uid);

}
