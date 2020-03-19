package com.api.benneighbour.workoutManager.user.controller;

import com.api.benneighbour.workoutManager.exceptions.ThemeChangedException;
import com.api.benneighbour.workoutManager.user.dao.UserDao;
import com.api.benneighbour.workoutManager.user.entity.User;
import com.api.benneighbour.workoutManager.user.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user/settings")
public class ChangeThemeController {

    @Autowired
    private UserServiceImpl service;

    @Autowired
    private UserDao dao;

    @PostMapping("/theme/{uid}/{username}/{themeId}")
    public RuntimeException changeUserTheme(@PathVariable("uid") Long uid, @PathVariable("username") String username, @PathVariable("themeId") int themeId) throws ThemeChangedException, UnauthorizedUserException {
        User u = dao.findUserByUid(uid);
        User u1 = dao.findByUsername(username);

        if (u == u1) {
            u.setThemeIndex(themeId);
        } else {
            throw new UnauthorizedUserException("Something went wrong...");
        } if (null != service.updateField(u)) {
            throw new ThemeChangedException("" + themeId);
        }

        return new RuntimeException("Something went wrong...");
    }

}
