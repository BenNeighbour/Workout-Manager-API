package com.api.benneighbour.workoutManager.user.service.impl;

import com.api.benneighbour.workoutManager.email.SignupEmailSender;
import com.api.benneighbour.workoutManager.exceptions.EmailNotFoundException;
import com.api.benneighbour.workoutManager.exceptions.EmailUnreachableException;
import com.api.benneighbour.workoutManager.exceptions.ServiceDownException;
import com.api.benneighbour.workoutManager.user.dao.UserDao;
import com.api.benneighbour.workoutManager.user.entity.User;
import com.api.benneighbour.workoutManager.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.stereotype.Service;
import com.api.benneighbour.workoutManager.exceptions.EmailAlreadyTakenException;

import java.util.List;

@Service
@Configurable
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SignupEmailSender sender;

    @Override
    public User saveUser(User u) throws EmailAlreadyTakenException, EmailUnreachableException {

        if (dao.findUserByEmail(u.getEmail()) != null)   {
            throw new EmailAlreadyTakenException("Email address is already in use");
        } else {

            // Catch anything that could go wrong with setting the new mime message correctly
            try {
                try {

                    // Creating a separate thread for the email sending tasks to run on, to avoid slow response time
                    Thread emailThread = new Thread(sender.newRunnable(u));

                    // Starting the runnable task on the dedicated email thread
                    emailThread.start();

                } catch (ServiceDownException e) {
                    e.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            // Encode the user's password and save the new one into the database
            u.setPassword(passwordEncoder.encode(u.getPassword()));
            return dao.save(u);
        }
    }


    @Override
    public User resetPassword(User u) throws EmailNotFoundException {
        if (dao.findUserByEmail(u.getEmail()) == null) {
            throw new EmailNotFoundException("Sorry, the email you entered is not linked to any registered accounts.");
        } else {

            // Catch anything that could go wrong with setting the new mime message correctly
            try {
                try {

                    // Creating a separate thread for the email sending tasks to run on, to avoid slow response time
                    Thread emailThread = new Thread(sender.newRunnable(u));

                    // Starting the runnable task on the dedicated email thread
                    emailThread.start();

                } catch (ServiceDownException e) {
                    e.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            // Encode the user's password and save the new one into the database
            u.setPassword(passwordEncoder.encode(u.getPassword()));
            return dao.saveAndFlush(u);
        }

    }

    @Override
    public User updateUser(User u) {
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        return dao.saveAndFlush(u);

    }

    @Override
    public List<User> getAllUsers() {
        return dao.findAll();
    }

    @Override
    public void deleteUser(Long uid) throws UnauthorizedUserException {
        if (dao.findUserByUid(uid).getRoles().contains("ADMIN")) {
            throw new UnauthorizedUserException("Unauthorized");
        }
        dao.deleteById(uid);
    }

    @Override
    public User getUserByName(String username) {
        return dao.findByUsername(username);
    }

}
