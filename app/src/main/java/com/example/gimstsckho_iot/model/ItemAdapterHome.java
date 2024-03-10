package com.example.gimstsckho_iot.model;

public class ItemAdapterHome {
    private int img;
    private String index;
    private String imgName;

    public ItemAdapterHome(int img, String index, String imgName) {
        this.img = img;
        this.index = index;
        this.imgName = imgName;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }
}
