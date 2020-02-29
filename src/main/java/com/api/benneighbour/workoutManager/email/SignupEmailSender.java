package com.api.benneighbour.workoutManager.email;

import com.api.benneighbour.workoutManager.exceptions.EmailUnreachableException;
import com.api.benneighbour.workoutManager.exceptions.ServiceDownException;
import com.api.benneighbour.workoutManager.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class SignupEmailSender {

    @Autowired
    private SpringTemplateEngine emailTemplateEngine;

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Autowired
    private User user;

    public SignupEmailSender(User u) {
        this.user = u;
    }

    public Runnable newRunnable(User u) {

        return new Runnable() {
            private void sendVerificationEmail(User u) throws EmailUnreachableException, ServiceDownException {

                try {

                    if (u != null) {

                        // Make message into a html message
                        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);

                        // Creating a new instance of a context to actually process the email template in the configured directory
                        Context context = new Context();
                        context.setVariable("message", "Thank you for signing up to Workout Manager, " + u.getUsername() + ". To verify that this is you, please click on this link to verify your email address.");

                        context.setVariable("url", "http://localhost:8080/api/v1/user/verify/verifyEmailAddress/" + u.getEmail());

                        String html = emailTemplateEngine.process("verificationEmail", context);

                        // Create a new instance for the email
                        messageHelper.setFrom("noreply@workoutmanager.com");
                        messageHelper.setTo(u.getEmail());
                        messageHelper.setSubject("Workout Manager Verification");
                        messageHelper.setText(html, true);

                        // Send the email
                        javaMailSender.send(mimeMessage);

                    }
                    else {
                        throw new ServiceDownException("Sorry, you are unable to sign up to this service at the moment, please try again later.");
                    }

                } catch (MessagingException e) {
                    throw new EmailUnreachableException("The email you entered cannot be reached by us. Please try again soon");
                }
            }

            @Override
            public void run() {
                this.sendVerificationEmail(u);
            }
        };
    }
}
