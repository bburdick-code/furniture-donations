package org.hospitalityprogram.furnituredonations.models;

import javax.persistence.OneToOne;

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

    public StudentProfile(String firstName, String lastName, String nickname, String personalEmail, String country, String gender, String maritalStatus, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.personalEmail = personalEmail;
        this.country = country;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
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

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

}
