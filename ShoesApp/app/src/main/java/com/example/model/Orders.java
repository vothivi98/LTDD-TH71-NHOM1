package com.example.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Orders {
    private String userId;
    private String orderId;
    private Date orderDate;
    private int total;

    public Orders() {
    }

    public Orders(String userId, String orderId, Date orderDate, int total) {
        this.userId = userId;
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.total = total;
    }

    // dùng để thêm dữ liệu hoặc cập nhật lên firebase
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("orderDate", orderDate);
        result.put("orderID", orderId);
        result.put("total", total);
        result.put("userID", userId);

        return result;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getUserId() {
        return userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public int getTotal() {
        return total;
    }
}
