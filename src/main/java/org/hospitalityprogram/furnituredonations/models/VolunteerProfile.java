package org.hospitalityprogram.furnituredonations.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="VOLUNTEER_PROFILE")
public class VolunteerProfile extends AbstractEntity {

    private String firstName;

    private String lastName;

    @OneToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    private final List<StudentProfile> myStudents = new ArrayList<>();

    public VolunteerProfile(String firstName, String lastName, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.user = user;
    }

    public VolunteerProfile(User user) {
        this.user = user;
    }

    public VolunteerProfile() {

    }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public char getFirstLetter() { return lastName.charAt(0); }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public List<StudentProfile> getStudents() { return myStudents; }

    public void addStudent(StudentProfile studentProfile) { myStudents.add(studentProfile); }

    public void removeStudent(StudentProfile studentProfile) { myStudents.remove(studentProfile); }

    public void removeAllStudents() { myStudents.clear(); }

}
