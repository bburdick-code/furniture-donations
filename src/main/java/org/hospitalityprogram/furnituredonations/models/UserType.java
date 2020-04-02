package org.hospitalityprogram.furnituredonations.models;

public enum UserType {

    STUDENT("Student"),
    ADMIN("Admin");

    private final String displayName;

    UserType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
