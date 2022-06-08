package com.example.demowebservice.model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder(toBuilder = true)
public class UserDTO {
    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
    @JsonIgnore
    private String password;
}
