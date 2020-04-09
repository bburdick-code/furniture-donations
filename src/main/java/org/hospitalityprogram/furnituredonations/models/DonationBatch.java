package org.hospitalityprogram.furnituredonations.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class DonationBatch extends AbstractEntity{

    private String donorEmail;

    private String donorAddress;

    private String donorPhone;

    private Date donorPostedDate;

    @OneToMany(cascade = CascadeType.ALL)
    private final List<Donation> donations = new ArrayList<>();

    public DonationBatch(String donorEmail, String donorAddress, String donorPhone, Date donorPostedDate) {
        this.donorEmail = donorEmail;
        this.donorAddress = donorAddress;
        this.donorPhone = donorPhone;
        this.donorPostedDate = donorPostedDate;
    }

    public DonationBatch() {

    }

    public String getDonorEmail() { return donorEmail; }

    public void setDonorEmail(String donorEmail) { this.donorEmail = donorEmail; }

    public String getDonorAddress() { return donorAddress; }

    public void setDonorAddress(String donorAddress) { this.donorAddress = donorAddress; }

    public String getDonorPhone() { return donorPhone; }

    public void setDonorPhone(String donorPhone) { this.donorPhone = donorPhone; }

    public List<Donation> getDonations() { return donations; }

    public void addDonation(Donation donation) { donations.add(donation); }

    public void removeDonation(Donation donation) { donations.remove(donation); }

}
