package com.maple.touriseguide.Util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Toast;

/**
 * Created by rrr on 2017/10/27.
 */

public class MyScrollView extends ScrollView{
    private OnScrollListener listener;
    public MyScrollView(Context context) {
        super(context);
    }
    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public interface OnScrollListener{
        void loadMore();
    }
    public void setOnScrollListener(OnScrollListener listener){
        this.listener = listener;
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        View childView = getChildAt(0);
        //获取测量高度(总高度)
        int measuredHeight = childView.getMeasuredHeight();
        //获取画出屏幕高度
        int scrollY = getScrollY();
        //获取可视区域
        int height = getHeight();
        if(measuredHeight==scrollY+height){
            Toast.makeText(getContext(), "到底了！",Toast.LENGTH_LONG).show();
            listener.loadMore();
        }
        return super.onTouchEvent(ev);
    }
}
