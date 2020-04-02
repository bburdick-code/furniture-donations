package org.hospitalityprogram.furnituredonations.models.dto;

public class RegistrationDTO extends LoginDTO {

    private String verifyPassword;

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }

}

