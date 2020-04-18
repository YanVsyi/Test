package com.example.administrator.testshop.adapter;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.testshop.HttpUrl;
import com.example.administrator.testshop.R;
import com.example.administrator.testshop.bean.BannerBean;
import com.example.administrator.testshop.bean.CategoriesBean;
import com.example.administrator.testshop.bean.ProductBean;
import com.example.administrator.testshop.view.ChildViewPager;
import com.example.administrator.testshop.view.GridViewInScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/4/13.
 */

public class RvAdapter extends RecyclerView.Adapter {

    private static final int Type_View_Head = 0;    //头部，广告和子网格
    private static final int Type_View_List = 1;    //线性列表
    private static final int Type_View_Gride = 2;   //最下面的网格
    private static final int Type_View_Line = 3;    //两个布局之间的文字

    private static final int staCount = 0;

    Context mContext;
    List<ProductBean> mList, gList;
    List<CategoriesBean> cateList;  //分享模块中的列表数据
    BannerBean bannerBean;      //广告

    String description = "";        //两个不同布局中间的文字间隔
    String title = "";

    boolean isShare = false;
    boolean isTb = false;

    public RvAdapter(Context context, boolean isShare, boolean isTb){
        mContext = context;
        this.isShare = isShare;
        this.isTb = isTb;
    }

    /**
     *
     * @param list  线性的列表数据
     * @param list2     网格列表数据
     * @param cateList  recyclerView中的子网格数据
     * @param bannerBean    广告
     */
    public void update(List<ProductBean> list, List<ProductBean> list2,
                       List<CategoriesBean> cateList, BannerBean bannerBean){
        if(list != null)mList = list;
        if(list2 != null) gList = list2;
        this.cateList = cateList;
        this.bannerBean = bannerBean;
        notifyDataSetChanged();
    }

    public void updateStr(String title, String description){
        this.description = description;
        this.title = title;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        if(viewType == Type_View_Head){
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_part_ad, null);
            viewHolder = new HeadViewHolder(view);
        } else if(viewType == Type_View_Line){
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_part_tv, null);
            viewHolder = new LineViewHolder(view);
        } else if(viewType == Type_View_List){
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_list, null);
            viewHolder = new LViewHolder(view);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_g, null);
            viewHolder = new GViewHolder(view);
        }

        return viewHolder;
    }

    /**
     * 把网格布局换为线性布局
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager)
        {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){
                @Override
                public int getSpanSize(int position) {

                    if(checkViewType(position) == Type_View_Gride) { //网格
                        return Type_View_List;
                    }else{
                        return Type_View_Gride;
                    }
                }
            });
        }


    }

    /*
     * List分割
     */
    public static List<List<CategoriesBean>> groupList(List<CategoriesBean> list) {
        List<List<CategoriesBean>> listGroup = new ArrayList<List<CategoriesBean>>();
        int listSize = list.size();
        //子集合的长度
        int toIndex = 10;
        for (int i = 0; i < list.size(); i += toIndex) {
            if (i + toIndex > listSize) {
                toIndex = listSize - i;
            }
            List<CategoriesBean> newList = list.subList(i, i + toIndex);
            listGroup.add(newList);
        }
        return listGroup;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ProductBean bean;
        if(checkViewType(position) == Type_View_Head){  //头部
            HeadViewHolder h = (HeadViewHolder) holder;
            if(bannerBean != null){
                String imgUrl = bannerBean.getCover();
                if(null != imgUrl && !imgUrl.startsWith("http")){
                    imgUrl = HttpUrl.Image_Url + imgUrl;
                }
                Glide.with(mContext).load(imgUrl).error(R.mipmap.ic_launcher).into(h.imgAdIcon);
            }

            if(cateList != null && !cateList.isEmpty()){
                int a = cateList.size();
                int b = 10;
                int pager = a%b == 0 ? (a/b) : (a/b)+1;
                List<List<CategoriesBean>> catList = groupList(cateList);
                List<View> viewList = new ArrayList<>();
                for(int i=0; i<pager; i++){
                    LinearLayout layout = (LinearLayout) LayoutInflater.from(mContext)
                            .inflate(R.layout.item_part_ad_childvp, null);
                    GridViewInScrollView gridView = layout.findViewById(R.id.gridview_item);
                    MenuShareAdapter adapter = new MenuShareAdapter(mContext);
                    gridView.setAdapter(adapter);

                    viewList.add(layout);
                    adapter.update(catList.get(i));
                }
                if(isShare){
                    h.viewPager.setVisibility(View.VISIBLE);
                }else{
                    h.viewPager.setVisibility(View.GONE);
                }
                ViewPAdapter adapter = new ViewPAdapter(viewList);
                h.viewPager.setAdapter(adapter);
                h.tabLayout.setupWithViewPager(h.viewPager);
                h.tabLayout.setTabMode(TabLayout.MODE_FIXED);

                adapter.notifyDataSetChanged();
            }

        } else if(checkViewType(position) == Type_View_Line){   //显示线
            LineViewHolder h = (LineViewHolder) holder;
            h.tvName.setText(title);
            h.tvCon.setText(description);
        } else if(checkViewType(position) == Type_View_Gride){ //网格
            bean = gList.get(getGridPosition(position));
            GViewHolder h = (GViewHolder) holder;
            h.tvName.setText(bean.getName());
            h.tvPrice.setText(bean.getPrice());

            if(isTb){
                h.tvFTab.setVisibility(View.VISIBLE);
            } else {
                h.tvFTab.setVisibility(View.GONE);
            }

            bean.isFinalPrice();
            h.tvCurPrice.setText(bean.getPriceName() + mContext.getString(R.string.pri_tab)
                    +bean.getAfterCouponPrice());
            if(!bean.getImgUrl().startsWith("http")){
                bean.setImgUrl(HttpUrl.Image_Url + bean.getImgUrl());
            }
            Glide.with(mContext).load(bean.getImgUrl()).error(R.mipmap.ic_launcher).centerCrop().into(h.imgIcon);

        } else {    //列表数量
            bean = mList.get(getListPosition(position));
            LViewHolder h = (LViewHolder) holder;
            h.tvName.setText(bean.getName());
            h.tvPrice.setText(bean.getPrice());
            bean.isFinalPrice();
            h.tvCurPrice.setText(bean.getPriceName() + mContext.getString(R.string.pri_tab)
                    +bean.getAfterCouponPrice());
            if(!bean.getImgUrl().startsWith("http")){
                bean.setImgUrl(HttpUrl.Image_Url + bean.getImgUrl());
            }
            Glide.with(mContext).load(bean.getImgUrl()).error(R.mipmap.ic_launcher).centerCrop().into(h.imgIcon);

        }

    }

    @Override
    public int getItemViewType(int position) {
        return checkViewType(position);
    }

    /**
     * 判断属于那种类型
     * @param position
     * @return
     */
    private int checkViewType(int position){
        if(position == 0){
            return Type_View_Head;
        } else if(position > getListCount()+1) { //网格
            return Type_View_Gride;
        } else if(position == getListCount()+1){
            return Type_View_Line;
        } else{
            return Type_View_List;
        }
    }

    @Override
    public int getItemCount() {
        int count = staCount;

        if(mList != null){
            count = count + mList.size();
        }
        if(gList != null){
            count = count + gList.size();
        }
        return count;
    }



        /**
         * 获取列表数量
         * @return
         */
    private int getListCount(){
        if(mList != null){
            return mList.size();
        }
        return 0;
    }

    /**
     * 获取网格数量
     * @return
     */
    private int getGrideCount(){
        if(gList != null){
            return gList.size();
        }
        return 0;
    }

    private int getGridPosition(int position){
        int i = getListCount() + staCount;
        return position-i;
    }

    private int getListPosition(int position){

        return position -1;
    }


    static class HeadViewHolder extends RecyclerView.ViewHolder{
        ImageView imgAdIcon;
        ChildViewPager viewPager;
        TabLayout tabLayout;
        TextView tvTitle, tvSubTitle;
        public HeadViewHolder(View itemView) {
            super(itemView);

            imgAdIcon = itemView.findViewById(R.id.img_item_part_ad);
            viewPager = (ChildViewPager) itemView.findViewById(R.id.viewpager_ad);
            tabLayout = (TabLayout) itemView.findViewById(R.id.tbl_share_ad);
            tvTitle = itemView.findViewById(R.id.tv_item_part_con1);
            tvSubTitle = itemView.findViewById(R.id.tv_item_part_con2);


        }
    }


    static class LViewHolder extends RecyclerView.ViewHolder{

        ImageView imgIcon;
        TextView tvName, tvPrice, tvCurPrice;

        public LViewHolder(View itemView) {
            super(itemView);
            imgIcon = itemView.findViewById(R.id.img_item_l_icon);
            tvName = itemView.findViewById(R.id.tv_item_list_name);
            tvPrice = itemView.findViewById(R.id.tv_item_l_yuan_price);
            tvCurPrice = itemView.findViewById(R.id.tv_item_list_price);

        }
    }

    static class LineViewHolder extends RecyclerView.ViewHolder{
        TextView tvName, tvCon;
        public LineViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.item_part_name);
            tvCon = itemView.findViewById(R.id.item_part_con);
        }
    }

    static class GViewHolder extends RecyclerView.ViewHolder{

        ImageView imgIcon;
        TextView tvName, tvPrice, tvCurPrice, tvFTab;

        public GViewHolder(View itemView) {
            super(itemView);
            imgIcon = itemView.findViewById(R.id.img_item_g_icon);
            tvName = itemView.findViewById(R.id.tv_item_g_name);
            tvPrice = itemView.findViewById(R.id.tv_item_g_yuan_price);
            tvCurPrice = itemView.findViewById(R.id.tv_item_g_price);
            tvFTab = itemView.findViewById(R.id.tv_item_g_ftab);

        }
    }


}
