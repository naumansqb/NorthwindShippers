package com.pluralsight.model;

public class Shipper {
    private int shipperId;
    private String companyName;
    private String phoneNumber;

    public Shipper(int shipperId, String companyName, String phoneNumber) {
        this.shipperId = shipperId;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
    }

    public int getShipperId() {
        return shipperId;
    }

    public void setShipperId(int shipperId) {
        this.shipperId = shipperId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Shipper{" +
                "shipperId=" + shipperId +
                ", companyName='" + companyName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
