package com.example.mylaundry.menu.home;

public class homeModel {
    String bedSheet , customerName , extra ,imageUrl , itemCount , pants , phoneNumber , price , shirts;

    public homeModel(String bedSheet, String customerName, String extra, String imageUrl, String itemCount, String pants, String phoneNumber, String price, String shirts) {
        this.bedSheet = bedSheet;
        this.customerName = customerName;
        this.extra = extra;
        this.imageUrl = imageUrl;
        this.itemCount = itemCount;
        this.pants = pants;
        this.phoneNumber = phoneNumber;
        this.price = price;
        this.shirts = shirts;
    }

    public homeModel() {
    }

    public String getBedSheet() {
        return bedSheet;
    }

    public void setBedSheet(String bedSheet) {
        this.bedSheet = bedSheet;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getItemCount() {
        return itemCount;
    }

    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
    }

    public String getPants() {
        return pants;
    }

    public void setPants(String pants) {
        this.pants = pants;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getShirts() {
        return shirts;
    }

    public void setShirts(String shirts) {
        this.shirts = shirts;
    }
}
