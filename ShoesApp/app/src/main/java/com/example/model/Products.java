package com.example.model;

import java.util.HashMap;
import java.util.Map;

public class Products {
    private String productId;
    private String cateID;
    private int price;
    private String imgProduct;
    private String productName;
    private String description;

    public Products() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public Products(String productId, String cateID, int price, String imgProduct,
                    String productName, String description) {
        this.productId = productId;
        this.cateID = cateID;
        this.price = price;
        this.imgProduct = imgProduct;
        this.productName = productName;
        this.description = description;
    }
    public Products(String productId, String productName, String imgProduct){
        this.productId = productId;
        this.productName = productName;
        this.imgProduct = imgProduct;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("cateID", cateID);
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

    public String getCateID() {
        return cateID;
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

    public void setCateID(String cateID) {
        this.cateID = cateID;
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
