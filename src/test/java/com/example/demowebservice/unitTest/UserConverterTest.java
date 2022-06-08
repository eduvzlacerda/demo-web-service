package com.example.demowebservice.unitTest;

import com.example.demowebservice.model.DTO.UserDTO;
import com.example.demowebservice.model.entity.User;
import com.example.demowebservice.utils.UserConverter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
public class UserConverterTest {

    @Test
    public void ConvertUserDTO2UserTest(){
        UserDTO userDTO = UserDTO.builder()
                .userId(UUID.randomUUID())
                .firstName("firstName")
                .lastName("lastName")
                .email("email")
                .password("password")
                .build();
        User user = UserConverter.convertUserDTO2User(userDTO);
        Assert.assertEquals(user.getId(),userDTO.getUserId());
        Assert.assertEquals(user.getFirstName(),userDTO.getFirstName());
        Assert.assertEquals(user.getLastName(),userDTO.getLastName());
        Assert.assertEquals(user.getEmail(),userDTO.getEmail());
        Assert.assertEquals(user.getEncPassword(),userDTO.getPassword());

    }

    @Test
    public void ConvertUser2UserDTOTest(){
        User user = User.builder()
                .id(UUID.randomUUID())
                .firstName("firstName")
                .lastName("lastName")
                .email("email")
                .encPassword("password")
                .build();

        UserDTO userDTO = UserConverter.convertUser2UserDTO(user);
        Assert.assertEquals(user.getId(),userDTO.getUserId());
        Assert.assertEquals(user.getFirstName(),userDTO.getFirstName());
        Assert.assertEquals(user.getLastName(),userDTO.getLastName());
        Assert.assertEquals(user.getEmail(),userDTO.getEmail());
        Assert.assertEquals(user.getEncPassword(),userDTO.getPassword());

    }
}
