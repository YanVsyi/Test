package com.example.administrator.testshop.bean;

/**
 * Created by Administrator on 2020/4/14.
 * 产品数据
 */

public class ProductBean {

    String name;
    String price;
    String priceName;
    String afterCouponPrice;    //用优惠价之后的价格
    String finalPrice;      //最终价格
    String imgUrl;          //图片URL
//    String[] smallImages;       //图片数组  分享赚钱模块
    String url;
    String shopTitle;

    public void isFinalPrice(){
        if(finalPrice != null && !"".equals(finalPrice)){
            afterCouponPrice = finalPrice;
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceName() {
        return priceName;
    }

    public void setPriceName(String priceName) {
        this.priceName = priceName;
    }

    public String getAfterCouponPrice() {
        return afterCouponPrice;
    }

    public void setAfterCouponPrice(String afterCouponPrice) {
        this.afterCouponPrice = afterCouponPrice;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

//    public String[] getSmallImages() {
//        return smallImages;
//    }
//
//    public void setSmallImages(String[] smallImages) {
//        this.smallImages = smallImages;
//    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getShopTitle() {
        return shopTitle;
    }

    public void setShopTitle(String shopTitle) {
        this.shopTitle = shopTitle;
    }

    public String getFinalPrice() {
        return finalPrice;
    }
}
