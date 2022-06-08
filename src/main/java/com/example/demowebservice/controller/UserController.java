package com.example.demowebservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("users")
public class UserController {

    @GetMapping
    public void getUserById(UUID id){

    }
}
