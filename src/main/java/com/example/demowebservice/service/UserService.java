package com.example.demowebservice.service;

import com.example.demowebservice.model.DTO.UserDTO;
import com.example.demowebservice.model.entity.User;
import com.example.demowebservice.repository.UserRepository;
import com.example.demowebservice.utils.UserConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public UserDTO createUser(UserDTO userDTO){
       User savedUser = userRepository.save(UserConverter.convertUserDTO2User(userDTO));
       UserDTO savedUserDTO = UserConverter.convertUser2UserDTO(savedUser);
       return savedUserDTO;
    }

}
