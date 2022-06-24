package com.example.demowebservice.controller;

import com.example.demowebservice.model.DTO.UserDTO;
import com.example.demowebservice.security.SecurityConstants;
import com.example.demowebservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Validated
@Controller
@RequestMapping("users")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @PostMapping()
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO){
        UserDTO createdUser = userService.createUser(userDTO);
        return new ResponseEntity<>(userDTO,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserDetails(@PathVariable String id){
       UserDTO userDTO = userService.findUserById(id);
        return new ResponseEntity<>(userDTO,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers(){
        return new ResponseEntity<>(userService.getUsers(),HttpStatus.OK) ;
    }

}
