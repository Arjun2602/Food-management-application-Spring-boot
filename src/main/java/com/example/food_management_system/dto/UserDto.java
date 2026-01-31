package com.example.food_management_system.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter @NoArgsConstructor
public class UserDto {
    private Long id;
    @NotBlank(message = "name cannot be blank")
    private String firstName;
    private String lastName;
    private String email;
    @NotBlank(message = "username cannot be blank")
    private String userName;
    @NotBlank(message = "password cannot be blank")
    private String password;
    @NotBlank(message = "role cannot be blank")
    private String role;
    private Date created_date;

    public UserDto(Long id, String firstName, String lastName,String email, String userName, String password, String role, Date created_date) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.created_date = created_date;
    }
}
