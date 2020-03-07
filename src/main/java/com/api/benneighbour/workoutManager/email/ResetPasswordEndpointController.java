package com.api.benneighbour.workoutManager.email;

import com.api.benneighbour.workoutManager.user.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/api/v1/user/password/")
public class ResetPasswordEndpointController {

    @Autowired
    private UserServiceImpl service;

    @RequestMapping("/change/{email}/{token}")
    public String resetPasswordView(@PathVariable(name = "email") String email, @PathVariable(name = "token") UUID token, Model model) {
        model.addAttribute("email", email);
        model.addAttribute("token", token);

        if (service.getVerificationToken(token.toString()) != null) {
            return "resetPasswordView";
        } else {
            throw new RuntimeException("Token is expired or invalid");
        }
    }

}
