package com.api.benneighbour.workoutManager.email.token;

import com.api.benneighbour.workoutManager.user.entity.User;
import com.api.benneighbour.workoutManager.user.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateVerificationToken {

    @Autowired
    private User u;

    @Autowired
    private UserServiceImpl service;

    public CreateVerificationToken(User u) {
        this.u = u;
    }

    public Runnable newRunnable(User u, String t) {

        return new Runnable() {
            private void createToken(User u, String t) {
                try {
                    service.createVerificationToken(u, t);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }

            @Override
            public void run() {
                this.createToken(u, t);
            }
        };
    }

}
