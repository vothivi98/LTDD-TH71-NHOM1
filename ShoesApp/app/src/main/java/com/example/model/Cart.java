package com.example.model;

public class Cart {
    private String productId;
    private String productName;
    private String img;
    private int price;
    private int  quantity;
    private String size;
    private int cateId;

    public Cart() {
    }

    public Cart(String productId, String productName, String img, int price,
                int quantity, String size, int cateId) {
        this.productId = productId;
        this.productName = productName;
        this.img = img;
        this.price = price;
        this.quantity = quantity;
        this.size = size;
        this.cateId = cateId;
    }

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getImg() {
        return img;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getSize() {
        return size;
    }
}
