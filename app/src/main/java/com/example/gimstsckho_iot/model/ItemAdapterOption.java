package com.example.gimstsckho_iot.model;

public class ItemAdapterOption {
    private int image;
    private String nameItemOption;

    public ItemAdapterOption(int image, String nameItemOption) {
        this.image = image;
        this.nameItemOption = nameItemOption;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNameItemOption() {
        return nameItemOption;
    }

    public void setNameItemOption(String nameItemOption) {
        this.nameItemOption = nameItemOption;
    }
}
