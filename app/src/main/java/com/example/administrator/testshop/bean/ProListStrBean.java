package com.example.administrator.testshop.bean;

import java.util.List;

/**
 * Created by Administrator on 2020/4/14.
 * 获取产品数据和广告的对象
 */

public class ProListStrBean {

    ProStrBean result;


    public class ProStrBean{
        BannerBean banner;      //淘宝京东的广告
        BannerBean shareBanner; //  分享的广告
        HotGoodsBean hotGoods;

        List<CategoriesBean> categories;


        public BannerBean getBanner() {
            return banner;
        }

        public HotGoodsBean getHotGoods() {
            return hotGoods;
        }


        public BannerBean getShareBanner() {
            return shareBanner;
        }

        public List<CategoriesBean> getCategories() {
            return categories;
        }
    }



    public ProStrBean getResult() {
        return result;
    }

    public void setResult(ProStrBean result) {
        this.result = result;
    }
}
