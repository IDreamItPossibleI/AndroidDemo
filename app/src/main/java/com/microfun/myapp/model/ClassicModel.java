package com.microfun.myapp.model;

public class ClassicModel {

    private String content;
    private int fav_nums;
    private String image;
    private int index;
    private int like_status;
    private String pubdate;
    private String title;
    private int type;
    private int id;

    public ClassicModel(String content, int fav_nums, String image, int index, int like_status, String pubdate, String title, int type, int id) {
        this.content = content;
        this.fav_nums = fav_nums;
        this.image = image;
        this.index = index;
        this.like_status = like_status;
        this.pubdate = pubdate;
        this.title = title;
        this.type = type;
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public int getFav_nums() {
        return fav_nums;
    }

    public String getImage() {
        return image;
    }

    public int getIndex() {
        return index;
    }

    public int getLike_status() {
        return like_status;
    }

    public String getPubdate() {
        return pubdate;
    }

    public String getTitle() {
        return title;
    }

    public int getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setFav_nums(int fav_nums) {
        this.fav_nums = fav_nums;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setLike_status(int like_status) {
        this.like_status = like_status;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }
}
