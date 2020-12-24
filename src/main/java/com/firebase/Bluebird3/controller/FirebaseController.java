package com.firebase.Bluebird3.controller;

import com.firebase.Bluebird3.Service.FirebaseService;
import com.firebase.Bluebird3.pojo.BBUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class FirebaseController {

    @Autowired
    FirebaseService firebaseService;

    @PostMapping("/createUser")
    public String createUser(@RequestBody BBUser bbuser) throws InterruptedException, ExecutionException {
        return firebaseService.saveUserData(bbuser);
    }
}
