package com.example.administrator.testshop.bean;

/**
 * Created by Administrator on 2020/4/14.
 */

public class PlatformBean {

    String imgUrl;
    String title;       //模块二级名称

    String platformDesc;      //模块名称
    String platformLogo;        //模块logo

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlatformDesc() {
        return platformDesc;
    }

    public String getPlatformLogo() {
        return platformLogo;
    }
}
