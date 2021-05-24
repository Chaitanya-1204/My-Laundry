package com.example.mylaundry.Model;

public class addOrderData {
    private String customerName;
    private String phoneNumber;

    private String bedSheet , shirts , pants , extra , price ,itemCount;
    private String imageUrl;





    public addOrderData(String customerName, String phoneNumber, String bedSheet , String shirts , String pants , String extra , String imageUrl , String price , String itemCount) {
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.shirts = shirts;
        this.pants = pants;
        this.bedSheet = bedSheet;
        this.extra = extra;
        this.imageUrl = imageUrl;
        this.price = price;
        this.itemCount = itemCount;
    }
    public addOrderData(){

    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getItemCount() {
        return itemCount;
    }

    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
