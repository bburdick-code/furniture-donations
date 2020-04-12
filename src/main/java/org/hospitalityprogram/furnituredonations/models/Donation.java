package org.hospitalityprogram.furnituredonations.models;

import org.hospitalityprogram.furnituredonations.models.enums.DonationStatus;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Donation extends AbstractEntity {

    @ManyToOne
    private ItemCategory itemCategory;

    private String description;

    private DonationStatus donationStatus;

    @ManyToOne
    private DonationBatch donationBatch;

    public Donation(ItemCategory itemCategory, String description, DonationStatus donationStatus, DonationBatch donationBatch) {
        this.itemCategory = itemCategory;
        this.description = description;
        this.donationStatus = donationStatus;
        this.donationBatch = donationBatch;
    }

    public Donation() {

    }

    public ItemCategory getItemCategory() { return itemCategory; }

    public void setItemCategory(ItemCategory itemCategory) { this.itemCategory = itemCategory; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public DonationStatus getDonationStatus() { return donationStatus; }

    public void setDonationStatus(DonationStatus donationStatus) { this.donationStatus = donationStatus; }

    public DonationBatch getDonationBatch() { return donationBatch; }

    public void setDonationBatch(DonationBatch donationBatch) { this.donationBatch = donationBatch; }

}
