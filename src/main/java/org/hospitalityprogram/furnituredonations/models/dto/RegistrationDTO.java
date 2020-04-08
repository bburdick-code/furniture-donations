package org.hospitalityprogram.furnituredonations.models.dto;

import javax.validation.constraints.Size;

public class RegistrationDTO extends LoginDTO {

    //@NotNull
    //@NotBlank
    //@Size(min = 3, max = 50, message = "Invalid username. Length 3-20")
    private String userAddress;

    //@NotNull
    //@NotBlank
    // TODO: need to check that phone number matches accepted patterns
    // @Pattern(regexp="^[a-zA-Z0-9]{3}",message="length must be 3")
    @Size(min = 3, max = 50, message = "Invalid username. Length 3-20")
    private String userPhone;

//    @NotNull
//    private UserType userType;

    private String verifyPassword;

    public String getUserAddress() { return userAddress; }

    public void setUserAddress(String userAddress) { this.userAddress = userAddress; }

    public String getUserPhone() { return userPhone; }

    public void setUserPhone(String userPhone) { this.userPhone = userPhone; }

//    public UserType getUserType() { return userType; }
//
//    public void setUserType(UserType userType) { this.userType = userType; }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }



}

