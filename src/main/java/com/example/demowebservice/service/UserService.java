package com.example.demowebservice.service;

import com.example.demowebservice.model.DTO.UserDTO;
import com.example.demowebservice.model.entity.User;
import com.example.demowebservice.repository.UserRepository;
import com.example.demowebservice.utils.UserConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public UserDTO createUser(UserDTO userDTO){
        if(userAlreadyExists(userDTO.getEmail())){
            throw new RuntimeException(" The provided Email is already used");
        }
        setUUID(userDTO);

        User savedUser = userRepository.save(UserConverter.convertUserDTO2User(userDTO));
        UserDTO savedUserDTO = UserConverter.convertUser2UserDTO(savedUser);
        return savedUserDTO;
    }

    private boolean userAlreadyExists(String email){
        if(userRepository.existsByEmail(email)){
            return true;
        }
        return false;
    }

    private void setUUID(UserDTO userDTO){
        userDTO.setUserId(UUID.randomUUID());
    }

}
