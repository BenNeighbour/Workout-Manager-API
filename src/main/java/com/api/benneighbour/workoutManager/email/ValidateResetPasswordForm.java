package com.api.benneighbour.workoutManager.email;

import com.api.benneighbour.workoutManager.email.token.ChangePasswordForm;
import com.api.benneighbour.workoutManager.email.token.ChangePasswordTokenDao;
import com.api.benneighbour.workoutManager.user.dao.UserDao;
import com.api.benneighbour.workoutManager.user.entity.User;
import com.api.benneighbour.workoutManager.user.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.UUID;

@Controller
@RequestMapping("/api/v1/user/password/")
public class ValidateResetPasswordForm {

    @Autowired
    private ChangePasswordForm form;

    @Autowired
    private UserServiceImpl service;

    @Autowired
    private ChangePasswordTokenDao store;

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

        this.updateUser(password, token);

        return "resetPasswordView";

    }

    private void updateUser(String password, UUID token) {
        User u = store.findByToken(token.toString()).getUser();
        User newUser = new User();

        newUser.setEmail(u.getEmail());
        newUser.setUsername(u.getUsername());
        newUser.setDob(u.getDob());
        newUser.setUid(u.getUid());
        newUser.setPassword(password);

        service.updateUser(newUser);

    }

}
