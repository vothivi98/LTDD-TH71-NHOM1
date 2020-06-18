package com.example.model;

import java.util.HashMap;
import java.util.Map;

public class    OrderDetail {

    private String orderId;
    private String productId;

    private int price;
    private int quantity;
    private String size;

    public OrderDetail() {
    }

    public OrderDetail(String orderid, String productId,
                       int price, int quantity, String size) {
        this.orderId = orderid;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
        this.size = size;
    }

    // dùng để thêm dữ liệu hoặc cập nhật lên firebase
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("orderID", orderId);
        result.put("price", price);
        result.put("productID", productId);
        result.put("quantity", quantity);
        result.put("size", size);
        return result;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setOrderId(String orderid) {
        this.orderId = orderid;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getProductId() {
        return productId;
    }


    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
