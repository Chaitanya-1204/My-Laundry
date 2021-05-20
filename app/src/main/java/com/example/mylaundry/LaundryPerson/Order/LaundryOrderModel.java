package com.example.mylaundry.LaundryPerson.Order;

public class LaundryOrderModel {
    int image;

    String itemCount;
    String price;


    public LaundryOrderModel(int image, String price, String itemCount) {
        this.image = image;
        this.itemCount = itemCount;
        this.price = price;

    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
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
