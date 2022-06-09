package com.example.demowebservice.service;

import com.example.demowebservice.model.DTO.UserDTO;
import com.example.demowebservice.model.entity.User;
import com.example.demowebservice.repository.UserRepository;
import com.example.demowebservice.utils.UserConverter;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserDTO createUser(UserDTO userDTO){

        if(userAlreadyExists(userDTO.getEmail())){
            throw new RuntimeException(" The provided Email is already used");
        }
        setUUID(userDTO);
        encodePassword(userDTO);

        User savedUser = userRepository.save(UserConverter.convertUserDTO2User(userDTO));
        UserDTO savedUserDTO = UserConverter.convertUser2UserDTO(savedUser);
        return savedUserDTO;
    }

    public User findUserByEmail(String email){
        User user = userRepository.findByEmail(email);
        if(email == null){
            throw new UsernameNotFoundException("user not found in db");
        }
        return user;
    }


    private boolean userAlreadyExists(String email){
       return userRepository.findByEmail(email) != null;
    }

    private void encodePassword(UserDTO userDTO){
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
    }



    private void setUUID(UserDTO userDTO){
        userDTO.setUserId(UUID.randomUUID());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserByEmail(username);
        return null;
    }
}
