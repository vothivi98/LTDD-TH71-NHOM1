package com.example.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Products implements Serializable {
    private String productId;
    private int cateId;
    private int price;
    private String imgProduct;
    private String productName;
    private String description;

    public Products() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public Products(String productId, int cateId, int price, String imgProduct,
                    String productName, String description) {
        this.productId = productId;
        this.cateId = cateId;
        this.price = price;
        this.imgProduct = imgProduct;
        this.productName = productName;
        this.description = description;
    }
    public Products(int price, String productName, String imgProduct){
        this.price = price;
        this.productName = productName;
        this.imgProduct = imgProduct;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("cateId", cateId);
        result.put("description", description);
        result.put("imgProduct", imgProduct);
        result.put("price", price);
        result.put("productID", productId);
        result.put("productName", productName);

        return result;
    }

    public String getProductId() {
        return productId;
    }

    public int getCateId() {
        return cateId;
    }

    public int getPrice() {
        return price;
    }

    public String getImgProduct() {
        return imgProduct;
    }

    public String getProductName() {
        return productName;
    }

    public String getDescription() {
        return description;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setImgProduct(String imgProduct) {
        this.imgProduct = imgProduct;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
