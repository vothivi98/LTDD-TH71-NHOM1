package com.example.model;

import java.util.HashMap;
import java.util.Map;

public class UserInfor {
    private String userId;
    private String fullName;
    private String address;
    private int phoneNumber;
    private String imgUser;

    public UserInfor() {
        // mặc định
    }

    public UserInfor(String userId, String fullName, String address, int phoneNumber, String imgUser) {
        this.userId = userId;
        this.fullName = fullName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.imgUser = imgUser;
    }
    // dùng để thêm dữ liệu hoặc cập nhật lên firebase
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("address", address);
        result.put("fullName", fullName);
        result.put("phoneNumber", phoneNumber);
        result.put("imgUser", imgUser);
        result.put("userID", userId);

        return result;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }
}
