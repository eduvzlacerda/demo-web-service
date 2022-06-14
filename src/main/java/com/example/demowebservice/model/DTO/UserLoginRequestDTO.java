package com.example.demowebservice.model.DTO;

import lombok.Data;

@Data
public class UserLoginRequestDTO {
    private String email;
    private String password;
}
