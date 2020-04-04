package org.hospitalityprogram.furnituredonations.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class StudentProfile extends AbstractEntity {

    private String firstName;

    private String lastName;

    private String nickname;

    private String personalEmail;

    private String country;

    private String gender;

    private String maritalStatus;

    @ManyToMany
    private final List<Item> items = new ArrayList<>();

    


}
