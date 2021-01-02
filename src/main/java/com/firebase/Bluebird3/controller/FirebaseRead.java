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
public class FirebaseRead {

    @Autowired
    FirebaseService firebaseService;

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
