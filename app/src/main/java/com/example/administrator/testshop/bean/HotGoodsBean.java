package com.example.administrator.testshop.bean;

import java.util.List;

/**
 * Created by Administrator on 2020/4/14.
 */

public class HotGoodsBean {

    PlatformBean platform;
    List<ProductBean> products;


    public PlatformBean getPlatform() {
        return platform;
    }

    public void setPlatform(PlatformBean platform) {
        this.platform = platform;
    }

    public List<ProductBean> getProducts() {
        return products;
    }

    public void setProducts(List<ProductBean> products) {
        this.products = products;
    }


}
