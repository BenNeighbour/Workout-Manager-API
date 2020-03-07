package com.api.benneighbour.workoutManager.email;

import com.api.benneighbour.workoutManager.exceptions.DuplicateVerificationTokenException;
import com.api.benneighbour.workoutManager.exceptions.EmailUnreachableException;
import com.api.benneighbour.workoutManager.exceptions.EmailVerifySentException;
import com.api.benneighbour.workoutManager.user.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/api/v1/user/password/")
public class ResetPasswordEndpointController {

    @RequestMapping("/change/{email}")
    public String resetPasswordView(@PathVariable(name = "email") String email, Model model) {
        model.addAttribute("email", email);
        return "resetPasswordView";
    }

}
