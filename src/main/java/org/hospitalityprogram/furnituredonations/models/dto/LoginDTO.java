package org.hospitalityprogram.furnituredonations.models.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginDTO {

    @NotNull
    @NotBlank
    @Email
    // TODO: Check that email is within the list of email addresses provided by SIUe
    private String username;

    @NotNull
    @NotBlank
    // TODO: implement more stringent requirements for passwords like adding a special char, etc
    @Size(min = 4, max = 20, message = "Invalid password. Length must be 4-20 characters")
    private String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

}

