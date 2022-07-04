package com.example.demowebservice.service;

import com.example.demowebservice.ExceptionHandling.ErrorMessages;
import com.example.demowebservice.ExceptionHandling.UserServiceException;
import com.example.demowebservice.model.DTO.UserDTO;
import com.example.demowebservice.model.entity.UserEntity;
import com.example.demowebservice.repository.UserRepository;
import com.example.demowebservice.utils.UserConverter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

        if (userEmailAlreadyExists(userDTO.getEmail())) {
            throw new UserServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
        }
        setUserId(userDTO);
        encodePassword(userDTO);
        UserEntity savedUserEntity = userRepository.save(UserConverter.convertUserDTO2User(userDTO));
        return UserConverter.convertUser2UserDTO(savedUserEntity);
    }

    public UserDTO updateUserDetails(String id,UserDTO userDTO) {

        UserEntity userToUpdate = findUserEntityById(id);
        userToUpdate.setFirstName(userDTO.getFirstName());
        userToUpdate.setLastName(userDTO.getLastName());

        UserEntity updatedUserEntity = userRepository.save(userToUpdate);
        return UserConverter.convertUser2UserDTO(updatedUserEntity);
    }

    public UserEntity findUserByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(()->new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()));

        return userEntity;
    }

    public UserEntity findUserEntityById(String id){
       return userRepository.findById(id)
               .orElseThrow(()-> new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()));

    }

    public List<UserDTO> getAllUsersPaginated(int page, int limit) {

        Pageable paging = PageRequest.of(page,limit);

        return userRepository.findAll(paging).stream()
                .map( u -> UserConverter.convertUser2UserDTO(u))
                .collect(Collectors.toList());
    }
    public void deleteUser(String id) {
        UserEntity userEntity = findUserEntityById(id);
        userRepository.delete(userEntity);
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = findUserByEmail(email);
        return new User(userEntity.getEmail(), userEntity.getEncPassword(), new ArrayList<>());
    }
    private boolean userEmailAlreadyExists(String email) {
        return userRepository.existsByEmail(email);
    }
    private void encodePassword(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
    }

    private void setUserId(UserDTO userDTO) {
        userDTO.setUserId(UUID.randomUUID().toString());
    }

}
