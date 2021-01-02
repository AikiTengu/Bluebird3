package com.firebase.Bluebird3.controller;

import com.firebase.Bluebird3.Service.FirebaseService;
import com.firebase.Bluebird3.model.BBUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
