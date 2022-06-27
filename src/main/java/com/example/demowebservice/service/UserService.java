package com.example.demowebservice.service;

import com.example.demowebservice.ExceptionHandling.ErrorMessages;
import com.example.demowebservice.ExceptionHandling.UserServiceException;
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
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserDTO createUser(UserDTO userDTO) {

        if (userAlreadyExists(userDTO.getEmail())) {
            throw new UserServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
        }
        setUserId(userDTO);
        encodePassword(userDTO);

        UserEntity savedUserEntity = userRepository.save(UserConverter.convertUserDTO2User(userDTO));
        return UserConverter.convertUser2UserDTO(savedUserEntity);
    }

    public UserDTO updateUser(UserDTO userDTO) {
        if(userDTO.getUserId() == null || !userRepository.existsById(userDTO.getUserId())){
            throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }
        encodePassword(userDTO);
        UserEntity updatedUserEntity = userRepository.save(UserConverter.convertUserDTO2User(userDTO));
        return UserConverter.convertUser2UserDTO(updatedUserEntity);
    }

    public UserEntity findUserByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(()->new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()));

        return userEntity;
    }

    public UserDTO findUserById(String id){
       UserEntity userEntity = userRepository.findById(id).orElseThrow(()-> new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()));
        return UserConverter.convertUser2UserDTO(userEntity);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map( u -> UserConverter.convertUser2UserDTO(u))
                .collect(Collectors.toList());
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = findUserByEmail(email);
        return new User(userEntity.getEmail(), userEntity.getEncPassword(), new ArrayList<>());
    }
    private boolean userAlreadyExists(String email) {
        return userRepository.findByEmail(email).get() != null;
    }
    private void encodePassword(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
    }
    private void setUserId(UserDTO userDTO) {
        userDTO.setUserId(UUID.randomUUID().toString().replaceAll("_", ""));
    }


}
