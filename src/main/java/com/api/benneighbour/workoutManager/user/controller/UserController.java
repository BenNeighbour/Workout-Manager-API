package com.api.benneighbour.workoutManager.user.controller;

import com.api.benneighbour.workoutManager.user.dao.UserDao;
import com.api.benneighbour.workoutManager.user.entity.User;
import com.api.benneighbour.workoutManager.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final String allowedOrigin = "localhost:3000/";

    @Autowired
    private UserService userServiceImpl;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDao dao;

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
    @PutMapping("/update/{password}")
    public Object update(@RequestBody User u, @PathVariable String password) {
        u.setPassword(password);

        User user1 = dao.findUserByUid(u.getUid());

        if (u.getPassword() != null) {
            if (!passwordEncoder.matches(u.getPassword(), user1.getPassword())) {
                throw new RuntimeException("passwords aint the same");
            }

        }

        u.setPassword(passwordEncoder.encode(password));

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
