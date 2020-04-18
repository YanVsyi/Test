package com.example.administrator.testshop;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.testshop.adapter.RvAdapter;
import com.example.administrator.testshop.adapter.ViewPAdapter;
import com.example.administrator.testshop.bean.DataStrBean;
import com.example.administrator.testshop.bean.PlatformBean;
import com.example.administrator.testshop.bean.ProListStrBean;
import com.example.administrator.testshop.bean.ProductBean;
import com.example.administrator.testshop.inter.GetJdDataInter;
import com.example.administrator.testshop.inter.GetShareInter;
import com.example.administrator.testshop.inter.GetTbDataInter;
import com.example.administrator.testshop.model.BaseGetDataModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements GetTbDataInter,
        GetJdDataInter, GetShareInter, ViewPager.OnPageChangeListener{

    TabLayout tbLayout;
    ViewPager viewPager;

    private static int[] titles = {R.string.jd, R.string.tb, R.string.share_make};

    RvAdapter adapterJd, adapterTb, adapterShare;

    ImageView imgJdIcon, imgTbIcon, imgShareIcon;
    TextView tvJdTitle, tvJdSub, tvTbTitle, tvTbSub, tvShareTitle, tvShareSub;

    List<View> mList = new ArrayList<>();
    BaseGetDataModel dataModel;

    List<ProductBean> tbList = new ArrayList<>();
    List<ProductBean> jdList = new ArrayList<>();
    List<ProductBean> shareList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        setStatusBarFullTransparent();

        initView();
        dataModel = new BaseGetDataModel(this,this, this, this);
        dataModel.loadJDListData();
        dataModel.loadJDGrideData();

        dataModel.loadTbListData();
        dataModel.loadTbGrideData();

        dataModel.loadShareListData();
        dataModel.loadShareGrideData();

    }
    /**
     * 全透状态栏
     */
    protected void setStatusBarFullTransparent() {
        if (Build.VERSION.SDK_INT >= 21) {//21表示5.0
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= 19) {//19表示4.4
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }



    private void initView(){

        tbLayout = (TabLayout) findViewById(R.id.tbl_main);
        viewPager = findViewById(R.id.viewpager);

        RecyclerView recycler1 = (RecyclerView) LayoutInflater.from(this).inflate(R.layout.layout_recycler, null);
        RecyclerView recycler2 = (RecyclerView) LayoutInflater.from(this).inflate(R.layout.layout_recycler, null);
        RecyclerView recycler3 = (RecyclerView) LayoutInflater.from(this).inflate(R.layout.layout_recycler, null);

        // 设置布局
        GridLayoutManager linearLayoutManager1 = new GridLayoutManager(this, 2);
        GridLayoutManager linearLayoutManager2 = new GridLayoutManager(this, 2);
        GridLayoutManager linearLayoutManager3 = new GridLayoutManager(this, 2);
        recycler1.setLayoutManager(linearLayoutManager1);
        recycler2.setLayoutManager(linearLayoutManager2);
        recycler3.setLayoutManager(linearLayoutManager3);

        adapterJd = new RvAdapter(this, false, false);
        adapterTb = new RvAdapter(this, false, true);
        adapterShare = new RvAdapter(this, true, false);

        recycler1.setAdapter(adapterJd);
        recycler2.setAdapter(adapterTb);
        recycler3.setAdapter(adapterShare);

        mList.add(recycler1);
        mList.add(recycler2);
        mList.add(recycler3);

        ViewPAdapter adapter = new ViewPAdapter(mList);
        viewPager.setAdapter(adapter);
        tbLayout.setupWithViewPager(viewPager);

        viewPager.setOnPageChangeListener(this);

        //设置自定义视图
        for (int i = 0; i < titles.length; i++) {
            tbLayout.getTabAt(i).setCustomView(getTabView(i));
        }
        tbLayout.setTabMode(TabLayout.MODE_FIXED);
    }


    /**
     * 自定义Tab
     * @param position  位置
     * @return
     */
    public View getTabView(int position) {
        View v = LayoutInflater.from(this).inflate(R.layout.layout_tab, null);
        ImageView imgIcon = (ImageView) v.findViewById(R.id.img_tab_icon);
        TextView tv = (TextView) v.findViewById(R.id.tv_tab_jd);
        TextView tvDes = (TextView) v.findViewById(R.id.tv_tab_jd_tip);
        tv.setText(getString(titles[position]));
        switch (position){
            case 0:
                imgJdIcon = imgIcon;
                tvJdTitle = tv;
                tvJdSub = tvDes;
                break;
            case 1:
                imgTbIcon = imgIcon;
                tvTbTitle = tv;
                tvTbSub = tvDes;
                break;
            case 2:
                imgShareIcon = imgIcon;
                tvShareTitle = tv;
                tvShareSub = tvDes;
                break;
        }

        return v;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }
    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                if(jdList == null || jdList.isEmpty()){
                    dataModel.loadJDListData();
                    dataModel.loadJDGrideData();
                }
                break;
            case 1:
                if(tbList == null || tbList.isEmpty()){
                    dataModel.loadTbListData();
                    dataModel.loadTbGrideData();
                }
                break;
            case 2:
                if(shareList == null || shareList.isEmpty()){
                    dataModel.loadShareListData();
                    dataModel.loadShareGrideData();
                }
                break;
            default:
                break;
        }
    }
    @Override
    public void onPageScrollStateChanged(int state) {
    }

    //淘宝 列表
    @Override
    public void onTbListSuccess(ProListStrBean.ProStrBean proStrBean) {
        if(proStrBean!= null && proStrBean.getHotGoods() != null){
            tbList = proStrBean.getHotGoods().getProducts();
            adapterTb.update(tbList, null, null, proStrBean.getBanner());

            PlatformBean platform = proStrBean.getHotGoods().getPlatform();
            if(platform != null){
                Glide.with(this).load(HttpUrl.Image_Url + platform.getPlatformLogo())
                        .error(R.mipmap.ic_launcher).into(imgTbIcon);
                tvTbTitle.setText(platform.getPlatformDesc());
                tvTbSub.setText(platform.getTitle());
            }
        }
    }


    //淘宝网格
    @Override
    public void onTbGrideSuccess(DataStrBean.ResultBean resultBean) {
        if(resultBean != null && resultBean.getData() != null){
            adapterTb.updateStr(resultBean.getTitle(), resultBean.getDescription());
            adapterTb.update(null, resultBean.getData(), null, null);
        }

    }

    @Override
    public void onShareListSuccess(ProListStrBean.ProStrBean strBean) {
        if(strBean!= null && strBean.getHotGoods() != null){
            shareList = strBean.getHotGoods().getProducts();
            adapterShare.update(shareList, null, strBean.getCategories(), strBean.getShareBanner());

            PlatformBean platform = strBean.getHotGoods().getPlatform();
            if(platform != null){
                Glide.with(this).load(HttpUrl.Image_Url + platform.getPlatformLogo())
                        .error(R.mipmap.ic_launcher).into(imgShareIcon);
                tvShareTitle.setText(platform.getPlatformDesc());
                tvShareSub.setText(platform.getTitle());
            }
        }
    }

    @Override
    public void onJDListSuccess(ProListStrBean.ProStrBean strBean) {
        if(strBean!= null && strBean.getHotGoods() != null){
            jdList = strBean.getHotGoods().getProducts();
            adapterJd.update(jdList, null, null, strBean.getBanner());

            PlatformBean platform = strBean.getHotGoods().getPlatform();
            if(platform != null){
                Glide.with(this).load(HttpUrl.Image_Url + platform.getPlatformLogo())
                        .error(R.mipmap.ic_launcher).into(imgJdIcon);
                tvJdTitle.setText(platform.getPlatformDesc());
                tvJdSub.setText(platform.getTitle());
            }
        }
    }

    /**
     * 京东网格
     * @param resultBean
     */
    @Override
    public void onJDGridSuccess(DataStrBean.ResultBean resultBean) {
        if(resultBean != null && resultBean.getData() != null){
            adapterJd.updateStr(resultBean.getTitle(), resultBean.getDescription());
            adapterJd.update(null, resultBean.getData(), null, null);
        }
    }

    /**
     * 分享网格
     * @param resultBean
     */
    @Override
    public void onShareGridSuccess(DataStrBean.ResultBean resultBean) {
        if(resultBean != null ){
            resultBean.isShare();
            adapterShare.updateStr(resultBean.getTitle(), resultBean.getDescription());
            adapterShare.update(null, resultBean.getData(), null, null);
        }
    }
}
