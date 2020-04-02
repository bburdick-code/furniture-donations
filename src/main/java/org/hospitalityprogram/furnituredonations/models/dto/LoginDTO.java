package org.hospitalityprogram.furnituredonations.models.dto;

import org.hospitalityprogram.furnituredonations.models.UserType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginDTO {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 20, message = "Invalid username. Length 3-20")
    private String username;

    @NotNull
    @NotBlank
    @Size(min = 5, max = 20, message = "Invalid password. Length 5-20")
    private String password;

    @NotNull
    private UserType userType;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

}

