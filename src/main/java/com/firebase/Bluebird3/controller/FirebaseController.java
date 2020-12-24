package com.firebase.Bluebird3.controller;

import com.firebase.Bluebird3.Service.FirebaseService;
import com.firebase.Bluebird3.pojo.BBUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class FirebaseController {

    @Autowired
    FirebaseService firebaseService;

    public String SendEmailNotification(BBUser bbuser){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost("smtp.mailtrap.io");
        mailSender.setPort(2525);
        mailSender.setUsername("f33dc540b9d655");
        mailSender.setPassword("9d69bfc30fc412");

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("Bluebird");
        simpleMailMessage.setTo(bbuser.getEmail());
        simpleMailMessage.setSubject("Notification");
        simpleMailMessage.setText("Your data has been successfully updated");

        mailSender.send(simpleMailMessage);
        return bbuser.getEmail();
    }

    @PostMapping("/createUser")
    public String createUser(@RequestBody BBUser bbuser) throws InterruptedException, ExecutionException {
        if ((bbuser.getOrg_id()!=null)&&(bbuser.getEmail()!=null)) {
            SendEmailNotification(bbuser);
            return firebaseService.saveUserData(bbuser);
        }
        else return "Error";
    }
}
