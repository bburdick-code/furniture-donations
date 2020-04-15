package org.hospitalityprogram.furnituredonations.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegistrationDTO extends LoginDTO {

    @NotNull
    @NotBlank
    @Size(min = 10, max = 50, message = "Invalid address. Must be greater that 10 characters.")
    private String userAddress;

    @NotNull
    @NotBlank
    // TODO: need to check that phone number matches accepted patterns
    // @Pattern(regexp="^[a-zA-Z0-9]{3}",message="length must be 3")
    @Size(min = 10, max = 50, message = "Invalid phone number.")
    private String userPhone;

    private String verifyPassword;

    public String getUserAddress() { return userAddress; }

    public void setUserAddress(String userAddress) { this.userAddress = userAddress; }

    public String getUserPhone() { return userPhone; }

    public void setUserPhone(String userPhone) { this.userPhone = userPhone; }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }



}

