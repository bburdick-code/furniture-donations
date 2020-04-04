package org.hospitalityprogram.furnituredonations.models.enums;

public enum UserType {

    STUDENT("Student"),
    VOLUNTEER("Volunteer"),
    ADMIN("Admin");

    private final String displayName;

    UserType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
