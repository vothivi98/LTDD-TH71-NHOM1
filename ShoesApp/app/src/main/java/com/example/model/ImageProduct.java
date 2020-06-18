package com.example.model;

public class ImageProduct {
    String productId;
    String img;

    public ImageProduct(String img, String productId){
        this.img = img;
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public ImageProduct() {

    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
