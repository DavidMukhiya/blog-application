package com.david.blogapplication.payloads;

import com.david.blogapplication.entities.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private int id;

    @NotEmpty
    @Size(min = 4, message = "Username must be min 4 of characters !!")
    private String name;

    @Email(message = "Email address is not valid !!")
    @NotEmpty(message = "Email is required")
    private String email;

    @NotEmpty
    @Size(min = 3, max = 10, message = "Password must be min of 3 characters and max of 10 characters !!")
    private String password;

    @NotEmpty
    private String about;

    private Set<RoleDto> roles = new HashSet<>();

    @JsonIgnore
    public String getPassword() {
        return this.password;
    }

    @JsonProperty
    public void setPassword(String password){
        this.password = password;
    }

}
