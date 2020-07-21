package com.example.recyclerview;

public class ItemList {
    private String imgId;
    private String heading;
    private String location;
    private String price;
    private String date;

    public ItemList(String imgId, String heading, String location, String price, String date) {
        this.imgId = imgId;
        this.heading = heading;
        this.location = location;
        this.price = price;
        this.date = date;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setString(String date) {
        this.date = date;
    }
}
