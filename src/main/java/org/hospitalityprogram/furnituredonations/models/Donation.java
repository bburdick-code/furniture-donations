package org.hospitalityprogram.furnituredonations.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Donation extends AbstractEntity {

    @ManyToOne
    private ItemCategory itemCategory;

    private String description;

    @ManyToOne
    private DonationBatch donationBatch;

    public Donation(ItemCategory itemCategory, String description, DonationBatch donationBatch) {
        this.itemCategory = itemCategory;
        this.description = description;
        this.donationBatch = donationBatch;
    }

    public ItemCategory getItemCategory() { return itemCategory; }

    public void setItemCategory(ItemCategory itemCategory) { this.itemCategory = itemCategory; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public DonationBatch getDonationBatch() { return donationBatch; }

    public void setDonationBatch(DonationBatch donationBatch) { this.donationBatch = donationBatch; }

}
