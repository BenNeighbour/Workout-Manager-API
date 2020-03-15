package com.api.benneighbour.workoutManager.email;

import com.api.benneighbour.workoutManager.email.token.ChangePasswordForm;
import com.api.benneighbour.workoutManager.email.token.ChangePasswordTokenDao;
import com.api.benneighbour.workoutManager.user.dao.UserDao;
import com.api.benneighbour.workoutManager.user.entity.User;
import com.api.benneighbour.workoutManager.user.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private UserServiceImpl service;

    @Autowired
    private ChangePasswordTokenDao store;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserDao dao;

    @PostMapping("/change/{email}/{token}")
    public String submitForm(@Valid @ModelAttribute("form") ChangePasswordForm form, BindingResult result, Model model, @PathVariable String email, @PathVariable UUID token) {

        // Initializing error and message fields that are later parsed in as model attributes
        String error;
        String message;

        // Validating if the binding result from the form has any errors (see the form model)
        if (result.hasErrors()) {
            // Setting the message at the bottom of the form to the following
            message = "Both fields must be at least 14 characters";

            // Adding the appropriate attributes and returning a new instance of the form
            model.addAttribute("message", message);
            return "resetPasswordView";
        }

        // Getting the field values out of the form
        String password = form.getFormPassword();
        String confirmPassword = form.getFormConfirmPassword();

        // Validating if the password is not the same as the confirm password value
        if (!password.equals(confirmPassword)) {
            // Setting the error below each of the fields in the form to the following
            error = "These do not match";

            // Adding the appropriate attributes and returning a new instance of the form
            model.addAttribute("error", error);
            return "resetPasswordView";
        }

        // If the above conditions didn't break the method, then the action was successful
        message = "Password has successfully changed!";

        // Adding the appropriate attributes to the form
        model.addAttribute("okMessage", message);

        // Running the helper function in order for the requested changes to take effect
        this.updateUser(password, token);

        // Returning a new instance of the form
        return "resetPasswordView";

    }

    private void updateUser(String password, UUID token) {

        // Casting a user with the token provided as a parameter, symbolising the current user
        User u = store.findByToken(token.toString()).getUser();

        // Creating a new empty user symbolising the object that will update the current one
        User newUser = new User();

        // Populating the new User's fields mostly based on the current user
        newUser.setEmail(u.getEmail());
        newUser.setUsername(u.getUsername());
        newUser.setDob(u.getDob());
        newUser.setUid(u.getUid());

        // Importantly, populating the new User's password with the changed, modified one
        newUser.setPassword(password);

        // Eventually updating the new user into the database
        service.updateUser(newUser);

    }

}
