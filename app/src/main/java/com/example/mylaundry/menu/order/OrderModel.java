package com.example.mylaundry.menu.order;

public class OrderModel {
    int image;

    String price;
   String itemCount;

    public OrderModel(int image, String price , String itemCount) {
        this.image = image;
        this.price = price;
        this.itemCount = itemCount;


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
