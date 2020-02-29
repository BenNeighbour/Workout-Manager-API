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
public class ResetPasswordEmailSender {

    @Autowired
    private SpringTemplateEngine emailTemplateEngine;

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Autowired
    private User user;

    public ResetPasswordEmailSender(User u) {
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
                        context.setVariable("message", "Hi, " + u.getUsername() + " you are receiving this email to verify that you want to reset your password");

                        String html = emailTemplateEngine.process("resetPasswordEmail", context);

                        // Create a new instance for the email
                        messageHelper.setFrom("noreply@workoutmanager.com");
                        messageHelper.setTo(u.getEmail());
                        messageHelper.setSubject("Workout Manager Reset Password");
                        messageHelper.setText(html, true);

                        // Send the email
                        javaMailSender.send(mimeMessage);

                    }
                    else {
                        throw new ServiceDownException("Sorry, you are unable to sign up to this service at the moment, please try again later.");
                    }

                } catch (MessagingException e) {
                    throw new EmailUnreachableException("Sorry, the email you entered is not linked to any registered accounts.");
                }
            }

            @Override
            public void run() {
                this.sendVerificationEmail(u);
            }
        };
    }

}
