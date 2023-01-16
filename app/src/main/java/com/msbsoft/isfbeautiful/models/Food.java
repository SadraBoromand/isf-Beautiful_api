package com.msbsoft.isfbeautiful.models;

public class Food {
    public String title;
    public String desc1;
    public String image;

    public Food(String title, String desc) {
        this.title = title;
        this.desc1 = desc;
    }

    public Food(String title, String desc, String image) {
        this.title = title;
        this.desc1 = desc;
        this.image = image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
