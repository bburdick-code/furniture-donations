package org.hospitalityprogram.furnituredonations.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class DonationBatch extends AbstractEntity{

    @NotBlank(message = "Required field")
    private String donorName;

    @NotBlank(message = "Required field")
    private String donorEmail;

    @NotBlank(message = "Required field")
    private String donorAddress;

    @NotBlank(message = "Required field")
    private String donorPhone;

    private Date donorPostedDate;

    @OneToMany(cascade = CascadeType.ALL)
    private final List<Donation> donations = new ArrayList<>();

    public DonationBatch(String donorName, String donorEmail, String donorAddress, String donorPhone, Date donorPostedDate) {
        this.donorName = donorName;
        this.donorEmail = donorEmail;
        this.donorAddress = donorAddress;
        this.donorPhone = donorPhone;
        this.donorPostedDate = donorPostedDate;
    }

    public DonationBatch() {

    }

    public String getDonorName() { return donorName; }

    public void setDonorName(String donorName) { this.donorName = donorName; }

    public String getDonorEmail() { return donorEmail; }

    public void setDonorEmail(String donorEmail) { this.donorEmail = donorEmail; }

    public String getDonorAddress() { return donorAddress; }

    public void setDonorAddress(String donorAddress) { this.donorAddress = donorAddress; }

    public String getDonorPhone() { return donorPhone; }

    public void setDonorPhone(String donorPhone) { this.donorPhone = donorPhone; }

    public Date getDonorPostedDate() { return donorPostedDate; }

    public void setDonorPostedDate(Date donorPostedDate) { this.donorPostedDate = donorPostedDate; }

    public List<Donation> getDonations() { return donations; }

    public void addDonation(Donation donation) { donations.add(donation); }

    public void removeDonation(Donation donation) { donations.remove(donation); }

}
