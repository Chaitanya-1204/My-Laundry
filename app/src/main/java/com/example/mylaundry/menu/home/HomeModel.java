package com.example.mylaundry.menu.home;

public class HomeModel {
    int image;

    String orderId;
    String price;
    String orderStatus;




    public HomeModel(int image  , String orderId , String price, String orderStatus){
        this.image  = image;
        this.orderId = orderId;
        this.price = price;
        this.orderStatus = orderStatus;


    }
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
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

    public String getOrderId() {

        return orderId;
    }

    public void setOrderId(String orderId) {

        this.orderId = orderId;
    }
}
