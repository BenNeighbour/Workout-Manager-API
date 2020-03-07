package com.api.benneighbour.workoutManager.email;

import com.api.benneighbour.workoutManager.exceptions.DuplicateVerificationTokenException;
import com.api.benneighbour.workoutManager.exceptions.EmailVerifySentException;
import com.api.benneighbour.workoutManager.user.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user/password/")
public class RequestResetPasswordEndpointController {

    @Autowired
    private UserServiceImpl service;

    @GetMapping("/request/change/{email}")
    public RuntimeException sendResetEmail(@PathVariable(name = "email") String email) {
        try {
            String ex = service.resetPassword(email).getLocalizedMessage();
            throw new EmailVerifySentException(ex);
        } catch (DuplicateVerificationTokenException e) {
            throw new DuplicateVerificationTokenException("duplicate");
        }
    }

}
