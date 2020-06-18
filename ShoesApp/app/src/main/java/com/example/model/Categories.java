package com.example.model;

public class Categories {

    private int cateId;
    private String cateName;
    private String imgCate;

    public Categories() {

    }

    public Categories(int cateId, String cateName, String imgCate) {
        this.cateId = cateId;
        this.cateName = cateName;
        this.imgCate = imgCate;
    }


    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public void setImgCate(String imgCate) {
        this.imgCate = imgCate;
    }

    public int getCateId() {
        return cateId;
    }

    public String getCateName() {
        return cateName;
    }

    public String getImgCate() {
        return imgCate;
    }
}
