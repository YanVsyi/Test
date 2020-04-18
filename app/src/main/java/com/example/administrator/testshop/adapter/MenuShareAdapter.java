package com.example.administrator.testshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.testshop.HttpUrl;
import com.example.administrator.testshop.R;
import com.example.administrator.testshop.bean.CategoriesBean;

import java.util.List;

/**
 * Created by Administrator on 2020/4/15.
 */

public class MenuShareAdapter extends BaseAdapter{

    Context mContext;
    List<CategoriesBean> mList;

    public MenuShareAdapter(Context context){
        mContext = context;
    }

    public void update( List<CategoriesBean> list){
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_gridview, null);
            holder = new ViewHolder();
            holder.imgIcon = view.findViewById(R.id.img_grid_icon);
            holder.tvName = view.findViewById(R.id.tv_item_grid_name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        CategoriesBean bean = mList.get(i);
        if(bean != null){
            holder.tvName.setText(bean.getName());
            if(bean.getImage() != null && !bean.getImage().startsWith("http")){
                Glide.with(mContext).load(HttpUrl.Image_Url + bean.getImage())
                        .error(R.mipmap.ic_launcher).into(holder.imgIcon);
            } else {
                Glide.with(mContext).load(bean.getImage()).error(R.mipmap.ic_launcher).into(holder.imgIcon);
            }

        }


        return view;
    }


    static class ViewHolder{
        ImageView imgIcon;
        TextView tvName;
    }

}
