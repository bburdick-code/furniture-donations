package org.hospitalityprogram.furnituredonations.models.enums;

public enum DonationStatus {

    POSTED("Posted"),
    COMPLETE("Complete");

    private final String displayName;

    DonationStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
