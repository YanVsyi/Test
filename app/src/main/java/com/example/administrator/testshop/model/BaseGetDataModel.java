package com.example.administrator.testshop.model;

import android.content.Context;

import com.example.administrator.testshop.NetService;
import com.example.administrator.testshop.bean.DataStrBean;
import com.example.administrator.testshop.bean.ProListStrBean;
import com.example.administrator.testshop.inter.GetJdDataInter;
import com.example.administrator.testshop.inter.GetShareInter;
import com.example.administrator.testshop.inter.GetTbDataInter;
import com.example.administrator.testshop.net.InitRetrofit;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2020/4/14.
 */

public class BaseGetDataModel {

    GetTbDataInter tbDataInter;
    GetJdDataInter jdDataInter;
    GetShareInter shareInter;
    Context mContext;

    public BaseGetDataModel(Context context, GetTbDataInter tbDataInter, GetJdDataInter jdDataInter, GetShareInter shareInter){
        this.tbDataInter = tbDataInter;
        this.shareInter = shareInter;
        this.jdDataInter = jdDataInter;
        mContext = context;

    }

    /**
     * 获取淘宝列表
     */
    public void loadTbListData(){
        InitRetrofit.createApi(NetService.class)
                .loadTbListData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProListStrBean>() {
                    @Override
                    public void onCompleted() {

                    }
                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                    @Override
                    public void onNext(ProListStrBean strBean) {
                        tbDataInter.onTbListSuccess(strBean.getResult());
                    }
                });
    }

    /**
     * 获取淘宝网格
     */
    public void loadTbGrideData(){
        InitRetrofit.createApi(NetService.class)
                .loadTbGridData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataStrBean>() {
                    @Override
                    public void onCompleted() {

                    }
                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                    @Override
                    public void onNext(DataStrBean strBean) {
                        tbDataInter.onTbGrideSuccess(strBean.getResult());
                    }
                });
    }


    /**
     * 获取京东列表
     */
    public void loadJDListData(){
        InitRetrofit.createApi(NetService.class)
                .loadJDListData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProListStrBean>() {
                    @Override
                    public void onCompleted() {

                    }
                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                    @Override
                    public void onNext(ProListStrBean strBean) {
                        jdDataInter.onJDListSuccess(strBean.getResult());
                    }
                });
    }

    /**
     * 获取京东网格
     */
    public void loadJDGrideData(){
        InitRetrofit.createApi(NetService.class)
                .loadJDGridData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataStrBean>() {
                    @Override
                    public void onCompleted() {

                    }
                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                    @Override
                    public void onNext(DataStrBean strBean) {
                        jdDataInter.onJDGridSuccess(strBean.getResult());
                    }
                });
    }


    /**
     * 获取分享赚钱列表
     */
    public void loadShareListData(){
        InitRetrofit.createApi(NetService.class)
                .loadShareListData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProListStrBean>() {
                    @Override
                    public void onCompleted() {

                    }
                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                    @Override
                    public void onNext(ProListStrBean strBean) {
                        shareInter.onShareListSuccess(strBean.getResult());
                    }
                });
    }

    /**
     * 获取分享赚钱
     */
    public void loadShareGrideData(){
        InitRetrofit.createApi(NetService.class)
                .loadShareGridData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataStrBean>() {
                    @Override
                    public void onCompleted() {

                    }
                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                    @Override
                    public void onNext(DataStrBean strBean) {
                        shareInter.onShareGridSuccess(strBean.getResult());
                    }
                });
    }




}
