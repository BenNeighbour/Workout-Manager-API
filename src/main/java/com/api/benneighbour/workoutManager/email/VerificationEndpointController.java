package com.api.benneighbour.workoutManager.email;

import com.api.benneighbour.workoutManager.exceptions.ServiceDownException;
import com.api.benneighbour.workoutManager.user.dao.UserDao;
import com.api.benneighbour.workoutManager.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/user/verify/")
public class VerificationEndpointController {

    @Autowired
    private UserDao dao;

    @RequestMapping("/verifyEmailAddress/{email}")
    public String verifyCode(@PathVariable(name = "email") String email, Model model) throws ServiceDownException {
        if (dao.findUserByEmail(email) == null) {
            throw new ServiceDownException("There was an error.");
        }
        else {
            if (dao.findUserByEmail(email).isAccountEnabled()) {
                model.addAttribute("message", "This account has already been verified");
                return "error";

            } else {
                User u = dao.findUserByEmail(email);
                u.setAccountEnabled(true);
                dao.saveAndFlush(u);

                return "verifyEmailView";
            }
        }
    }

}
