package org.hospitalityprogram.furnituredonations.models.enums;

public enum ItemStatus {

    REQUESTED("Requested"),
    PENDING("Pending"),
    DELIVERED("Delivered");

    private final String displayName;

    ItemStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
