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

    public User(@NotNull String username, @NotNull String password, @NotNull String userAddress, @NotNull String userPhone, @NotNull UserType userType) {
        this.username = username;
        this.pwHash = encoder.encode(password);
        this.userAddress = userAddress;
        this.userPhone = userPhone;
        this.userType = userType;
    }

    public User() {}

    public String getUsername() {
        return username;
    }

    public String getUserAddress() { return userAddress; }

    public void setUserAddress(String userAddress) { this.userAddress = userAddress; }

    public String getUserPhone() { return userPhone; }

    public void setUserPhone(String userPhone) { this.userPhone = userPhone; }

    public UserType getUserType() { return userType; }

    public void setUserType(UserType userType) { this.userType = userType; }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }
}
