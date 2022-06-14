package com.example.demowebservice.utils;

import com.example.demowebservice.model.DTO.UserDTO;
import com.example.demowebservice.model.entity.UserEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserConverter {

    public static UserEntity convertUserDTO2User(UserDTO userDTO){
        return UserEntity.builder()
                .id(userDTO.getUserId())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .encPassword(userDTO.getPassword())
                .build();
    }
    public static UserDTO convertUser2UserDTO(UserEntity userEntity){
        return UserDTO.builder()
                .userId(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .password(userEntity.getEncPassword())
                .build();
    }

}
