package com.jd.gms.mongo.connect.bean;

/**
 * @Author: jiangjiabin3
 * @Date: Created in 2019/12/12 12:30
 * @Description:
 */

public class TestBean {

    private String title;
    private String description;
    private Integer likes;
    private String by;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", likes=" + likes +
                ", by='" + by + '\'' +
                '}';
    }
}
