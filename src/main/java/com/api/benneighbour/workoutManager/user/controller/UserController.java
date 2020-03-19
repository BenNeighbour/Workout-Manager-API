package com.api.benneighbour.workoutManager.user.controller;

import com.api.benneighbour.workoutManager.user.entity.User;
import com.api.benneighbour.workoutManager.user.service.UserService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final String allowedOrigin = "localhost:3000/";

    @Autowired
    private UserService userServiceImpl;

    @CrossOrigin(origins = allowedOrigin)
    @PostMapping("/signup/")
    public User save(@RequestBody User u) {
        return userServiceImpl.saveUser(u);
    }

    @GetMapping("/all/")
    public List<User> getAllUsers() {
        return userServiceImpl.getAllUsers();
    }

    @CrossOrigin(origins = allowedOrigin)
    @PutMapping("/update")
    public Object update(@RequestBody User u) {
        return userServiceImpl.updateUser(u);
    }

    @CrossOrigin(origins = allowedOrigin)
    @GetMapping("/username/by/{username}")
    public Object getUserByName(@PathVariable(name="username") String username) {
        return userServiceImpl.getUserByName(username);
    }

    @CrossOrigin(origins = allowedOrigin)
    @DeleteMapping("/delete/by/{uid}")
    public void deleteExercise(@PathVariable(name = "uid") Long uid) {
        userServiceImpl.deleteUser(uid);
    }

}
