package com.example.model;

public class Categories {

    private String cateID;
    private String cateName;
    private String imgCate;

    public Categories() {

    }

    public Categories(String cateID, String cateName, String imgCate) {
        this.cateID = cateID;
        this.cateName = cateName;
        this.imgCate = imgCate;
    }

    public void setCateID(String cateID) {
        this.cateID = cateID;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public void setImgCate(String imgCate) {
        this.imgCate = imgCate;
    }

    public String getCateID() {
        return cateID;
    }

    public String getCateName() {
        return cateName;
    }

    public String getImgCate() {
        return imgCate;
    }
}
