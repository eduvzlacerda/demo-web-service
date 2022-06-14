package com.example.demowebservice.service;

import com.example.demowebservice.model.DTO.UserDTO;
import com.example.demowebservice.model.entity.UserEntity;
import com.example.demowebservice.repository.UserRepository;
import com.example.demowebservice.utils.UserConverter;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserDTO createUser(UserDTO userDTO) {

        if (userAlreadyExists(userDTO.getEmail())) {
            throw new RuntimeException(" The provided Email is already used");
        }
        setUUID(userDTO);
        encodePassword(userDTO);

        UserEntity savedUserEntity = userRepository.save(UserConverter.convertUserDTO2User(userDTO));
        return UserConverter.convertUser2UserDTO(savedUserEntity);
    }

    public UserEntity findUserByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (email == null) {
            throw new UsernameNotFoundException("user not found in db");
        }
        return userEntity;
    }


    private boolean userAlreadyExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    private void encodePassword(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
    }


    private void setUUID(UserDTO userDTO) {
        userDTO.setUserId(UUID.randomUUID());
    }

    //TODO: Hardcoded array list
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = findUserByEmail(email);
        return new User(userEntity.getEmail(), userEntity.getEncPassword(), new ArrayList<>());
    }
}
