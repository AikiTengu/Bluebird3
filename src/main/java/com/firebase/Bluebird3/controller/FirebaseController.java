package com.firebase.Bluebird3.controller;

import com.firebase.Bluebird3.Service.FirebaseService;
import com.firebase.Bluebird3.model.BBUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class FirebaseController {

    @Autowired
    FirebaseService firebaseService;

    public String sendEmailNotification(BBUser bbuser){
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
        String result = simpleMailMessage.getText();
        return result;
    }

    @PostMapping("/saveUser")
    public String saveUser(@RequestBody BBUser bbuser) {
        if ((bbuser.getOrg_id()!=null)&&(bbuser.getEmail()!=null)) {
            sendEmailNotification(bbuser);

            String result = "";
            try {
                 result = firebaseService.saveUserData(bbuser);
            } catch (Exception e) {
                result = e.getMessage();
            }
            return result;
        }
        else return "Error. Fill in all the required fields";
    }

    @GetMapping("/users")
    public BBUser getUser(@RequestHeader() String org_id) {
        BBUser result = null;
        try {
            result = firebaseService.getUserData(org_id);
        } catch (Exception e) {
            result = null;
        }
        return  result;
    }
}
