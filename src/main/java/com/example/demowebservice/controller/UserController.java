package com.example.demowebservice.controller;

import com.example.demowebservice.model.DTO.UserDTO;
import com.example.demowebservice.service.UserService;
import com.example.demowebservice.utils.UserConverter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@Controller
@RequestMapping("users")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @PostMapping()
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO){
        UserDTO createdUser = userService.createUser(userDTO);
        return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUserDetails( @PathVariable String id , @Valid @RequestBody UserDTO userDTO){
        UserDTO updatedUser = userService.updateUserDetails(id,userDTO);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Class> deleteUser(@PathVariable String id){
       userService.deleteUser(id);
       return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserDetails(@PathVariable String id){
       UserDTO userDTO = UserConverter.convertUser2UserDTO(userService.findUserEntityById(id)) ;
        return new ResponseEntity<>(userDTO,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
                                                  @RequestParam(value = "limit", defaultValue = "25") int limit)
    {
        return new ResponseEntity<>(userService.getAllUsersPaginated(page ,limit),HttpStatus.OK) ;
    }

}
