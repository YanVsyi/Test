package com.example.administrator.testshop.inter;

import com.example.administrator.testshop.bean.DataStrBean;
import com.example.administrator.testshop.bean.ProListStrBean;
import com.example.administrator.testshop.bean.ProductBean;

import java.util.List;

/**
 * Created by Administrator on 2020/4/14.
 */

public interface GetJdDataInter {

    void onJDListSuccess(ProListStrBean.ProStrBean strBean);
    void onJDGridSuccess(DataStrBean.ResultBean resultBean);

}
