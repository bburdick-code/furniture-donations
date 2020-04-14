package org.hospitalityprogram.furnituredonations.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
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

    @Pattern(regexp="^[0-9]{5}(?:-[0-9]{4})?$", message = "Must be a valid ZIP code")
    private String donorZIP;

    @NotBlank(message = "Required field")
    private String donorPhone;

    private Date donorPostedDate;

    @OneToMany(cascade = CascadeType.ALL)
    private final List<Donation> donations = new ArrayList<>();

    public DonationBatch(String donorName, String donorEmail, String donorAddress, String donorZIP, String donorPhone, Date donorPostedDate) {
        this.donorName = donorName;
        this.donorEmail = donorEmail;
        this.donorAddress = donorAddress;
        this.donorZIP = donorZIP;
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

    public String getDonorZIP() { return donorZIP; }

    public void setDonorZIP(String donorZIP) { this.donorZIP = donorZIP; }

    public String getDonorPhone() { return donorPhone; }

    public void setDonorPhone(String donorPhone) { this.donorPhone = donorPhone; }

    public Date getDonorPostedDate() { return donorPostedDate; }

    public void setDonorPostedDate(Date donorPostedDate) { this.donorPostedDate = donorPostedDate; }

    public List<Donation> getDonations() { return donations; }

    public void addDonation(Donation donation) { donations.add(donation); }

    public void removeDonation(Donation donation) { donations.remove(donation); }

}
