package com.example.mylaundry.Model;

public class addOrderData {
    private String customerName;
    private String phoneNumber;

    private String bedSheet , shirts , pants , extra;




    public addOrderData(String customerName, String phoneNumber, String bedSheet , String shirts , String pants , String extra) {
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.shirts = shirts;
        this.pants = pants;
        this.bedSheet = bedSheet;
        this.extra = extra;
    }
    public addOrderData(){

    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBedSheet() {
        return bedSheet;
    }

    public void setBedSheet(String bedSheet) {
        this.bedSheet = bedSheet;
    }

    public String getShirts() {
        return shirts;
    }

    public void setShirts(String shirts) {
        this.shirts = shirts;
    }

    public String getPants() {
        return pants;
    }

    public void setPants(String pants) {
        this.pants = pants;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
