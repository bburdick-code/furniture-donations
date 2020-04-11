package org.hospitalityprogram.furnituredonations.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="STUDENT_PROFILE")
public class StudentProfile extends AbstractEntity {

    private String firstName;

    private String lastName;

    private String nickname;

    private String personalEmail;

    private String country;

    private String gender;

    private String maritalStatus;

    @OneToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    private final List<Item> items = new ArrayList<>();

    public StudentProfile(String firstName, String lastName, String nickname, String personalEmail, String country, String gender, String maritalStatus) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.personalEmail = personalEmail;
        this.country = country;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
    }

    public StudentProfile(User user) {
        this.user = user;
    }

    public StudentProfile() {

    }

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

    public void removeItem (Item item) {items.remove(item);}

}
