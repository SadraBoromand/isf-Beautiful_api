package com.msbsoft.isfbeautiful.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Place {

    public Place(String title, String desc1, String location) {
        this.title = title;
        this.desc1 = desc1;
        this.location = location;
    }

    public void setImages(List<ImagesEntity> images) {
        this.images = images;
    }

    @SerializedName("title")
    public String title;

    @SerializedName("desc1")
    public String desc1;

    @SerializedName("location")
    public String location;

    @SerializedName("images")
    public List<ImagesEntity> images = new ArrayList<>();

    public class ImagesEntity {
        public String url;
    }
}
