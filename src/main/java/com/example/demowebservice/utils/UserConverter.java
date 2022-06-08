package com.example.demowebservice.utils;

import com.example.demowebservice.model.DTO.UserDTO;
import com.example.demowebservice.model.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserConverter {

    public static User convertUserDTO2User(UserDTO userDTO){
        return User.builder()
                .id(userDTO.getUserId())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .encPassword("password")
                .build();
    }
    public static UserDTO convertUser2UserDTO(User user){
        return UserDTO.builder()
                .userId(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getEncPassword())
                .build();
    }

}
