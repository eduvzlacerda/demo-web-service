package com.example.demowebservice.model.DTO;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
public class UserDTO {
    private String userId;
    @NotEmpty(message = "First name can't be empty")
    private String firstName;
    private String lastName;
    @NotEmpty(message = "email can't be empty")
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotEmpty(message = "password can't be empty")
    private String password;
}
