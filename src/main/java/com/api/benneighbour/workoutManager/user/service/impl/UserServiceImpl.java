package com.api.benneighbour.workoutManager.user.service.impl;

import com.api.benneighbour.workoutManager.email.ResetPasswordEmailSender;
import com.api.benneighbour.workoutManager.email.SignupEmailSender;
import com.api.benneighbour.workoutManager.email.token.ChangePasswordToken;
import com.api.benneighbour.workoutManager.email.token.ChangePasswordTokenDao;
import com.api.benneighbour.workoutManager.exceptions.*;
import com.api.benneighbour.workoutManager.user.dao.UserDao;
import com.api.benneighbour.workoutManager.user.entity.User;
import com.api.benneighbour.workoutManager.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Configurable
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    @Autowired
    private ChangePasswordTokenDao tokenStore;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SignupEmailSender sender;

    @Autowired
    private ResetPasswordEmailSender resetSender;

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
    public RuntimeException resetPassword(String email) throws EmailNotFoundException, EmailVerifySentException, DuplicateVerificationTokenException {
        // Get user object from the string
        User user = dao.findUserByEmail(email);

        if (dao.findUserByEmail(email) == null) {
            throw new EmailNotFoundException("Sorry, the email you entered is not linked to any registered accounts.");
        } else {

            // Catch anything that could go wrong with setting the new mime message correctly
            try {

                // Creation of random string representing the token itself
                UUID token = UUID.randomUUID();
                try {

                    // Generates the token itself
                    this.createVerificationToken(user, token.toString());

                } catch (DuplicateVerificationTokenException e) {
                    throw new DuplicateVerificationTokenException("dup");
                }

                try {

                    // Creating a separate thread for the email sending tasks to run on, to avoid slow response time
                    Thread emailThread = new Thread(resetSender.newRunnable(user, email, token));

                    // Starting the runnable task on the dedicated email thread
                    emailThread.start();

                } catch (ServiceDownException e) {
                    throw new ServiceDownException("service down");
                }

            } catch (DuplicateVerificationTokenException e) {
                throw new DuplicateVerificationTokenException("duplicate");
            }
        }
        return new EmailVerifySentException("Email was sent");
//        throw new DuplicateVerificationTokenException("duplicate");
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

    @Override
    public void createVerificationToken(User user, String token) throws DuplicateVerificationTokenException {
        ChangePasswordToken customToken = new ChangePasswordToken(user, token);

        // TODO: Stop duplicates from persisting
        if (tokenStore.findTokenByUser(user) == null) {
            tokenStore.save(customToken);
        } else {
            throw new DuplicateVerificationTokenException("gh");
        }
    }

    @Override
    public ChangePasswordToken getVerificationToken(String verificationToken) {
        return tokenStore.findByToken(verificationToken);
    }

}
