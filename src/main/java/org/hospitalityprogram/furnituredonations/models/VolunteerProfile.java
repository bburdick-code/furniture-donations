package org.hospitalityprogram.furnituredonations.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="VOLUNTEER_PROFILE")
public class VolunteerProfile extends AbstractEntity {

    private String name;

    @OneToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    private final List<StudentProfile> myStudents = new ArrayList<>();

    public VolunteerProfile(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public VolunteerProfile(User user) {
        this.user = user;
    }

    public VolunteerProfile() {

    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public List<StudentProfile> getStudents() { return myStudents; }

    public void addStudent(StudentProfile studentProfile) { myStudents.add(studentProfile); }

    public void removeStudent(StudentProfile studentProfile) { myStudents.remove(studentProfile); }

    public void removeAllStudents() { myStudents.clear(); }

}
