package com.aws.tutorial.aws.controller;

import com.aws.tutorial.aws.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {
    @PostMapping("/register")
    public String register(@RequestBody User user){
        System.out.println("User "+user);
        return "success";
    }
}
