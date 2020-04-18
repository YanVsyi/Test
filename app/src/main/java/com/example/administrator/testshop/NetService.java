package com.example.administrator.testshop;

import com.example.administrator.testshop.bean.DataStrBean;
import com.example.administrator.testshop.bean.ProListStrBean;


import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Administrator on 2020/4/14.
 */

public interface NetService {

    /**
     * 获取淘宝列表
     * @return
     */
    @GET("/indexRight/taobao")
    Observable<ProListStrBean> loadTbListData();

    /**
     * 获取淘宝网格
     * @return
     */
    @GET("/indexRight/taobaoSearch?page=1&pageSize=20")
    Observable<DataStrBean> loadTbGridData();

    /**
     * 京东列表
     * @return
     */
    @GET("indexRight/jd")
    Observable<ProListStrBean> loadJDListData();

    /**
     * 京东
     * @return
     */
    @GET("indexRight/jdSearch?page=1&pageSize=20")
    Observable<DataStrBean> loadJDGridData();

    /**
     * 分享赚钱列表
     * @return
     */
    @GET("indexRight/kpl")
    Observable<ProListStrBean> loadShareListData();

    /**
     * 分享赚钱
     * @return
     */
    @GET("Search/pool?page=1&pageSize=20&v=v1")
    Observable<DataStrBean> loadShareGridData();

}
