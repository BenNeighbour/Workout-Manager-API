package com.api.benneighbour.workoutManager.email.token;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Component
public class ChangePasswordForm implements Serializable {

    private static final long serialVersionUID = 4335133031318006302L;

    @NotNull
    @Size(min = 14)
    private String formPassword;

    @NotNull
    @Size(min = 14)
    private String formConfirmPassword;

    public ChangePasswordForm() {
        this.formPassword = this.getFormPassword();
        this.formConfirmPassword = this.getFormConfirmPassword();
    }



    public String getFormPassword() {
        return formPassword;
    }
    public void setFormPassword(String formPassword) {
        this.formPassword = formPassword;
    }


    public String getFormConfirmPassword() {
        return formConfirmPassword;
    }
    public void setFormConfirmPassword(String formConfirmPassword) {
        this.formConfirmPassword = formConfirmPassword;
    }

}
