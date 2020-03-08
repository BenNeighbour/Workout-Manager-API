package com.api.benneighbour.workoutManager.email;

import com.api.benneighbour.workoutManager.email.token.ChangePasswordToken;
import com.api.benneighbour.workoutManager.email.token.ChangePasswordTokenDao;
import com.api.benneighbour.workoutManager.user.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/api/v1/user/password/")
public class ResetPasswordEndpointController {

    @Autowired
    private UserServiceImpl service;

    @Autowired
    private ChangePasswordTokenDao tokenStore;

    @RequestMapping("/change/{email}/{token}")
    public String resetPasswordView(@PathVariable(name = "email") String email, @PathVariable(name = "token") UUID token, Model model) throws RuntimeException {
        model.addAttribute("email", email);
        model.addAttribute("token", token);

        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());

        if (service.getVerificationToken(token.toString()) != null) {
            if (service.getVerificationToken(token.toString()).getExpiryDate().before(ts)) {

                // Retrieve the token's tid
                ChangePasswordToken retrievedToken = service.getVerificationToken(token.toString());

                // Delete the token
                tokenStore.delete(retrievedToken);

                throw new RuntimeException("Token is expired");
            }

        } else {
            throw new RuntimeException("Token is invalid");
        }

        return "resetPasswordView";

    }

}
