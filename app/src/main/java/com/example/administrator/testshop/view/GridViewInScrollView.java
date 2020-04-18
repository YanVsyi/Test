package com.example.administrator.testshop.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by Administrator on 2020/4/15.
 */

public class GridViewInScrollView extends GridView {

    public GridViewInScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GridViewInScrollView(Context context) {
        super(context);
    }

    public GridViewInScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
