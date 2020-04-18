package com.example.administrator.testshop.bean;

import java.util.List;

/**
 * Created by Administrator on 2020/4/14.
 */

public class DataStrBean {

    String msg;
    ResultBean result;

    public class ResultBean{
        List<ProductBean> data;
        String title;
        String description;

        List<ProductBean> list;

        public void isShare(){
            if(null != list && !list.isEmpty()){
                data=list;
            }
        }

        public List<ProductBean> getData() {
            return data;
        }

        public void setData(List<ProductBean> data) {
            this.data = data;
        }

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

        public List<ProductBean> getList() {
            return list;
        }

    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }
}
