package org.hospitalityprogram.furnituredonations.models.dto;

import org.hospitalityprogram.furnituredonations.models.Item;
import org.hospitalityprogram.furnituredonations.models.StudentProfile;

import javax.validation.constraints.NotNull;

public class StudentItemDTO {

    @NotNull
    private StudentProfile studentProfile;

    @NotNull
    private Item item;

    public StudentItemDTO() {

    }

    public StudentProfile getStudentProfile() { return studentProfile; }

    public void setStudentProfile(StudentProfile studentProfile) { this.studentProfile = studentProfile; }

    public Item getItem() { return item; }

    public void setItem(Item item) { this.item = item; }
}
