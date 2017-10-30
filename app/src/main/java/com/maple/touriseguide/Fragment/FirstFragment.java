package com.maple.touriseguide.Fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.maple.touriseguide.R;
import com.maple.touriseguide.Util.MyScrollView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
        View view = inflater.inflate(R.layout.view1, container, false);
        myScrollView = (MyScrollView) view.findViewById(R.id.myScrollView);
        firstClo = (LinearLayout) view.findViewById(R.id.firstClo);
        secondClo = (LinearLayout) view.findViewById(R.id.sceondClo);
        initView();
        return view;
    }

    private void httplink(int i) {
        String url = "http://images.tuanpubao.cn/banner20171019.png";
        //初始化OkHttpClient
        final OkHttpClient client = new OkHttpClient();

        final ImageView imageView = new ImageView(getActivity());
        // 宽度是屏幕的1/3
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(imgvWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
        imageView.setLayoutParams(lp);
        OkHttpUtils
                .get()//
                .url(url)//
                .build()//
                .execute(new BitmapCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getActivity().getApplicationContext(), "onError:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Bitmap response, int id) {
                        final Bitmap bitmap = response;
                        getActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                //获取图片宽高
                                int bitH = bitmap.getHeight();
                                int bitW = bitmap.getWidth();
                                //获取LayoutParams设置宽高，使图片填充整个ImageView
                                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) imageView.getLayoutParams();
                                //图片的宽高比等于ImageView的宽高比，因为ImageView宽度已经确定(1/2父窗体)，通过计算得到ImageView的高度
                                lp.height = bitH * lp.width / bitW;
                                lp.setMargins(0,1,0,0);
                                //将参数设置到ImageView
                                imageView.setLayoutParams(lp);
                                imageView.setImageBitmap(bitmap);
                            }
                        });
                    }
                });
        clos[i % 2].addView(imageView);
    }

    private void initView() {
        // 用来盛放屏幕信息
        DisplayMetrics outMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        //设置每个LinearLayout的宽度为整个屏幕的1/2
        imgvWidth = outMetrics.widthPixels / 2;
        clos[0] = firstClo;
        clos[1] = secondClo;
        // 第一次加载图片
        loadImage();
        //设置scrollView的滚动监听
        myScrollView.setOnScrollListener(new MyScrollView.OnScrollListener() {
            @Override
            public void loadMore() {
                if (pageIndex * pageSize >= 20) {
                    Toast.makeText(getActivity().getApplicationContext(), "数据加载完成", Toast.LENGTH_SHORT).show();
                } else {
                    loadImage();
                }
            }
        });
    }

    // 进行图片加载
    private void loadImage() {
        for (int i = pageIndex * pageSize; i < pageIndex * pageSize + pageSize
                && i < 20; i++) {
            httplink(i);
        }
        pageIndex++;
    }
}
