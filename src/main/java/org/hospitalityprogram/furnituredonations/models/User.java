package org.hospitalityprogram.furnituredonations.models;

import org.hospitalityprogram.furnituredonations.models.enums.UserType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class User extends AbstractEntity {

    @NotNull
    private String username;

    @NotNull
    private String pwHash;

    @NotNull
    private String userAddress;

    @NotNull
    private String userPhone;

    @NotNull
    private UserType userType;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User() {}

    public User (String username, String password, String userAddress, String userPhone, UserType userType) {
        this.username = username;
        this.pwHash = encoder.encode(password);
        this.userAddress = userAddress;
        this.userPhone = userPhone;
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public String getUserAddress() { return userAddress; }

    public String getUserPhone() { return userPhone; }

    public UserType getUserType() { return userType; }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }
}
