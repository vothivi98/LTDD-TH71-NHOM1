package com.example.model;

import java.util.HashMap;
import java.util.Map;

public class OrderDetail {

    private String orderId;
    private String productId;
    private String discountId;
    private int price;
    private int quantity;

    public OrderDetail() {
    }

    public OrderDetail(String orderid, String productId, String discountId,
                       int price, int quantity) {
        this.orderId = orderid;
        this.productId = productId;
        this.discountId = discountId;
        this.price = price;
        this.quantity = quantity;
    }

    // dùng để thêm dữ liệu hoặc cập nhật lên firebase
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("orderID", orderId);
        result.put("price", price);
        result.put("productID", productId);
        result.put("quantity", quantity);
        result.put("discountID", discountId);

        return result;
    }

    public void setOrderId(String orderid) {
        this.orderId = orderid;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setDiscountId(String discountId) {
        this.discountId = discountId;
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

    public String getDiscountId() {
        return discountId;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
