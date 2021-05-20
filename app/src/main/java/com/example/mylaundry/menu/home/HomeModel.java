package com.example.mylaundry.menu.home;

public class HomeModel {
    int image;


    String price;
    String itemOrder;




    public HomeModel(int image   , String price, String itemOrder){
        this.image  = image;

        this.price = price;
        this.itemOrder = itemOrder;


    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {

        this.price = price;
    }

    public int getImage() {

        return image;
    }

    public void setImage(int image) {

        this.image = image;
    }

    public String getItemOrder() {
        return itemOrder;
    }

    public void setItemOrder(String itemOrder) {
        this.itemOrder = itemOrder;
    }
}
