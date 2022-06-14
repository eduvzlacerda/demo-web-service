package com.example.demowebservice.unitTest;

import com.example.demowebservice.model.DTO.UserDTO;
import com.example.demowebservice.model.entity.UserEntity;
import com.example.demowebservice.utils.UserConverter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
public class UserEntityConverterTest {

    @Test
    public void ConvertUserDTO2UserTest(){
        UserDTO userDTO = UserDTO.builder()
                .userId(UUID.randomUUID())
                .firstName("firstName")
                .lastName("lastName")
                .email("email")
                .password("password")
                .build();
        UserEntity userEntity = UserConverter.convertUserDTO2User(userDTO);
        Assert.assertEquals(userEntity.getId(),userDTO.getUserId());
        Assert.assertEquals(userEntity.getFirstName(),userDTO.getFirstName());
        Assert.assertEquals(userEntity.getLastName(),userDTO.getLastName());
        Assert.assertEquals(userEntity.getEmail(),userDTO.getEmail());
        Assert.assertEquals(userEntity.getEncPassword(),userDTO.getPassword());

    }

    @Test
    public void ConvertUser2UserDTOTest(){
        UserEntity userEntity = UserEntity.builder()
                .id(UUID.randomUUID())
                .firstName("firstName")
                .lastName("lastName")
                .email("email")
                .encPassword("password")
                .build();

        UserDTO userDTO = UserConverter.convertUser2UserDTO(userEntity);
        Assert.assertEquals(userEntity.getId(),userDTO.getUserId());
        Assert.assertEquals(userEntity.getFirstName(),userDTO.getFirstName());
        Assert.assertEquals(userEntity.getLastName(),userDTO.getLastName());
        Assert.assertEquals(userEntity.getEmail(),userDTO.getEmail());
        Assert.assertEquals(userEntity.getEncPassword(),userDTO.getPassword());

    }
}
