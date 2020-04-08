package org.hospitalityprogram.furnituredonations.models;

import org.hospitalityprogram.furnituredonations.models.enums.UserType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

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

    private String firstName;

    private String lastName;

    private String nickname;

    private String personalEmail;

    private String country;

    private String gender;

    private String maritalStatus;

    @ManyToMany(cascade = CascadeType.ALL)
    private final List<Item> items = new ArrayList<>();

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User() {}

    public User(@NotNull String username, @NotNull String password, @NotNull String userAddress, @NotNull String userPhone, @NotNull UserType userType) {
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

    public void setUserAddress(String userAddress) { this.userAddress = userAddress; }

    public String getUserPhone() { return userPhone; }

    public void setUserPhone(String userPhone) { this.userPhone = userPhone; }

    public UserType getUserType() { return userType; }

    public void setUserType(UserType userType) { this.userType = userType; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getNickname() { return nickname; }

    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getPersonalEmail() { return personalEmail; }

    public void setPersonalEmail(String personalEmail) { this.personalEmail = personalEmail; }

    public String getCountry() { return country; }

    public void setCountry(String country) { this.country = country; }

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    public String getMaritalStatus() { return maritalStatus; }

    public void setMaritalStatus(String maritalStatus) { this.maritalStatus = maritalStatus; }

    public List<Item> getItems() { return items; }

    public void removeAllItems() { items.clear(); }

    public void addItem(Item item) {items.add(item);}

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }
}
