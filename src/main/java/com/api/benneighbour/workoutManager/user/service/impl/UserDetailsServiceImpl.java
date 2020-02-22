package com.api.benneighbour.workoutManager.user.service.impl;

import com.api.benneighbour.workoutManager.user.CustomUserDetails;
import com.api.benneighbour.workoutManager.user.dao.UserDao;
import com.api.benneighbour.workoutManager.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao dao;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<User> optionalUser = Optional.ofNullable(dao.findByUsername(name));
        optionalUser.orElseThrow(() -> new UsernameNotFoundException("Username or Password is incorrect"));

        UserDetails userDetails = new CustomUserDetails(optionalUser.get());
        new AccountStatusUserDetailsChecker().check(userDetails);

        return userDetails;
    }
}
