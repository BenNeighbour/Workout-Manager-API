package com.api.benneighbour.workoutManager.email;

import com.api.benneighbour.workoutManager.email.token.ChangePasswordForm;
import com.api.benneighbour.workoutManager.email.token.ChangePasswordToken;
import com.api.benneighbour.workoutManager.email.token.ChangePasswordTokenDao;
import com.api.benneighbour.workoutManager.user.entity.User;
import com.api.benneighbour.workoutManager.user.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;
import java.awt.*;
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

    @Autowired
    private ChangePasswordForm form;

    @GetMapping("/change/{email}/{token}")
    public String resetPasswordView(@PathVariable(name = "email") String email, @PathVariable(name = "token") UUID token, Model model) throws RuntimeException {
        model.addAttribute("form", new ChangePasswordForm());
        model.addAttribute("url", "/api/v1/user/password/change/" + email + "/" + token.toString() + "/");

        model.addAttribute("error", null);
        model.addAttribute("message", null);
        model.addAttribute("okMessage", null);

        // Getting the time
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());

        if (service.getVerificationToken(token.toString()) != null) {
            // Comparing the expiration date and the exact current date
            if (service.getVerificationToken(token.toString()).getExpiryDate().before(ts)) {
                // Retrieve the token's tid
                ChangePasswordToken retrievedToken = service.getVerificationToken(token.toString());

                // Delete the token
                tokenStore.delete(retrievedToken);

                throw new RuntimeException("Reset Password Token has expired");
            }

        } else {
            throw new RuntimeException("Reset Password Token is invalid");
        }

        return "resetPasswordView";

    }

}
