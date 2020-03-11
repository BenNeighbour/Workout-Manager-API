package com.api.benneighbour.workoutManager.email;

import com.api.benneighbour.workoutManager.email.token.ChangePasswordForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/api/v1/user/password/")
public class ValidateResetPasswordForm {

    @Autowired
    private ChangePasswordForm form;

    @PostMapping("/change/{email}/{token}")
    public String submitForm(@Valid @ModelAttribute("form") ChangePasswordForm form, BindingResult result, Model model, @PathVariable String email, @PathVariable UUID token) {

        String error;
        String message;

        if (result.hasErrors()) {
            message = "Both fields must be at least 14 characters";

            model.addAttribute("message", message);
            return "resetPasswordView";
        }

        String password = form.getFormPassword();
        String confirmPassword = form.getFormConfirmPassword();

        if (!password.equals(confirmPassword)) {
            error = "These do not match";

            model.addAttribute("error", error);
            return "resetPasswordView";
        }

        message = "Password has successfully changed!";
        model.addAttribute("okMessage", message);

        return "resetPasswordView";

    }

    // TODO: Find the user object with the email provided

    // TODO: Pre-populate the User UID, Username and Email

    // TODO: Populate the password with the User input

    // TODO: service.updateUser(u);

}
