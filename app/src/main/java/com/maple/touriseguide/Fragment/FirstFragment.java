package com.maple.touriseguide.Fragment;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.maple.touriseguide.Common.LoadImageFromAssets;
import com.maple.touriseguide.R;
import com.maple.touriseguide.Util.MyScrollView;

import java.io.IOException;

/**
 * Created by rrr on 2017/10/26.
 */

public class FirstFragment extends Fragment {
    private LinearLayout firstClo, secondClo;
    // 每一个ImageView的宽度，是屏幕宽度的1/2
    private int imgvWidth;
    // 每次加载多少个
    private int pageSize = 15;
    //加载次数，每加载一次，pageIndex++
    private int pageIndex = 0;

    private String[] imgName = {"123.jpg"};
    //布局有三个，添加到数组里，以便更新UI的时候使用。
    private LinearLayout clos[] = new LinearLayout[2];

    private MyScrollView myScrollView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.view1, container, false);
        initView(view);
        return view;
    }

    private void initView(View v) {
        myScrollView = (MyScrollView) v.findViewById(R.id.myScrollView);

        // 用来盛放屏幕信息
        DisplayMetrics outMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        //设置每个LinearLayout的宽度为整个屏幕的1/2
        imgvWidth = outMetrics.widthPixels / 2;
        firstClo = (LinearLayout) v.findViewById(R.id.firstClo);
        secondClo = (LinearLayout) v.findViewById(R.id.sceondClo);
        clos[0] = firstClo;
        clos[1] = secondClo;

        //imgName[1] = "123.jpg";
        // 第一次加载图片
        loadImage();
        //设置scrollView的滚动监听
        myScrollView.setOnScrollListener(new MyScrollView.OnScrollListener() {
            @Override
            public void loadMore() {
                if (pageIndex * pageSize >= 2) {
                    Toast.makeText(getActivity().getApplicationContext(), "数据加载完成", Toast.LENGTH_SHORT).show();
                } else {
                    loadImage();
                }
            }
        });
    }
    // 进行图片加载
    private void loadImage() {
        //如果加载的数量比所有文件个数少，每次加载15张
        final Bitmap bitmap = LoadImageFromAssets.load(getActivity(), "123");
        for (int i = pageIndex * pageSize; i < pageIndex * pageSize + pageSize
                && i < 2; i++) {
            // 加载图片，实例化一个ImageView
            ImageView imgv = new ImageView(getActivity());
            // 宽度是屏幕的1/3
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(imgvWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
            imgv.setLayoutParams(lp);
            // 加载图片资源

            test(getActivity(),bitmap,imgv);
            //每次往不同的LinearLayout放一个带有图片的ImageView
            clos[i % 3].addView(imgv);
        }
        pageIndex++;
    }
    private void test(Activity activity, Bitmap bitmaps, final ImageView iv){
        final Bitmap bitmap = bitmaps;
        //主线程更新UI
        activity.runOnUiThread(new Runnable(){
            public void run(){
                //获取图片宽高
                int bitH = bitmap.getHeight();
                int bitW = bitmap.getWidth();
                //获取LayoutParams设置宽高，使图片填充整个ImageView
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) iv.getLayoutParams();
                /**
                 * 图片的宽高比等于ImageView的宽高比，因为ImageView宽度已经确定(1/2父窗体)，通过计算
                 * 得到ImageView的高度
                 */
                lp.height = bitH*lp.width/bitW;
                //将参数设置到ImageView
                iv.setLayoutParams(lp);
                iv.setImageBitmap(bitmap);
            }
        });
    }
}
