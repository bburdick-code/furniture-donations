package org.hospitalityprogram.furnituredonations.models.dto;

public class DonationStatusChangedDTO {

    private String donationId;

    private String donationStatus;


    public String getDonationId() { return donationId; }

    public void setDonationId(String donationId) { this.donationId = donationId; }

    public String getDonationStatus() { return donationStatus; }

    public void setDonationStatus(String donationStatus) { this.donationStatus = donationStatus; }
}
