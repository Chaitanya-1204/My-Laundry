package com.example.mylaundry.menu.home;

public class homeModel {
    String imageUrl;
    String shirts  , pants , extra , bedSheet , price , itemCount, customerName;

    public homeModel() {
    }

    public homeModel(String imageUrl, String shirts, String pants, String extra, String bedSheet, String price, String itemCount, String customerName) {
        this.imageUrl = imageUrl;
        this.shirts = shirts;
        this.pants = pants;
        this.extra = extra;
        this.bedSheet = bedSheet;
        this.price = price;
        this.itemCount = itemCount;
        this.customerName = customerName;

    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public String getBedSheet() {
        return bedSheet;
    }

    public void setBedSheet(String bedSheet) {
        this.bedSheet = bedSheet;
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
}
