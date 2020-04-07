package org.hospitalityprogram.furnituredonations.models.enums;

public enum MaritalStatus {

    SINGLE("Single"),
    MARRIED("Married");

    private final String displayName;

    MaritalStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
