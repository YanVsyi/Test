package com.example.administrator.testshop.bean;

/**
 * Created by Administrator on 2020/4/14.
 */

public class BannerBean {

    String id;
    String title;
    String cover;   //图片链接
    String link;
    String target_link;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTarget_link() {
        return target_link;
    }

    public void setTarget_link(String target_link) {
        this.target_link = target_link;
    }
}
