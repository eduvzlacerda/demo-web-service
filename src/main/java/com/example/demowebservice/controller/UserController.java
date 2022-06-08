package com.example.demowebservice.controller;

import com.example.demowebservice.model.DTO.UserDTO;
import com.example.demowebservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("users")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @PostMapping()
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        UserDTO createdUser = userService.createUser(userDTO);
        return new ResponseEntity<>(userDTO,HttpStatus.CREATED);
    }


}
