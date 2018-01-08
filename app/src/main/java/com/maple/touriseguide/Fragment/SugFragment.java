package com.maple.touriseguide.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.maple.touriseguide.Activity.DynamicActivity;
import com.maple.touriseguide.Activity.SugActivity;
import com.maple.touriseguide.Common.Global;
import com.maple.touriseguide.R;
import com.maple.touriseguide.Util.MyScrollView;

import java.io.InputStream;
import java.util.Map;

/**
 * Created by rrr on 2017/10/26.
 */

public class SugFragment extends Fragment {
    private SharedPreferences sp;
    private LinearLayout firstClo, secondClo;
    // 每一个ImageView的宽度，是屏幕宽度的1/2
    private int imgvWidth;
    // 每次加载多少个
    private int pageSize = 10;
    //加载次数，每加载一次，pageIndex++
    private int pageIndex = 0;

    private LayoutInflater myInflater;

    private String[] imgName = {"123.jpg"};
    //布局有三个，添加到数组里，以便更新UI的时候使用。
    private LinearLayout clos[] = new LinearLayout[2];

    private MyScrollView myScrollView;
    View view;

    static BitmapFactory.Options opts;
    static BitmapFactory.Options options;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myInflater = inflater;
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_sug, container, false);
        sp = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        myScrollView = (MyScrollView) view.findViewById(R.id.myScrollView);
        firstClo = (LinearLayout) view.findViewById(R.id.firstClo);
        secondClo = (LinearLayout) view.findViewById(R.id.sceondClo);
        Global.initsug(sp.getInt("sug_id",0));
        initView();
        return view;
    }

    private void httplink(Map<String, Object> map, int i) {
            View view=myInflater.inflate(R.layout.item_sug, null);
            ImageView img = (ImageView) view.findViewById(R.id.sug_img);
            TextView text = (TextView) view.findViewById(R.id.sug_title);
            FrameLayout sugs = (FrameLayout) view.findViewById(R.id.sugs);
            //InputStream is = getResources().openRawResource(R.drawable.pic1);
            BitmapFactory.Options bitmap= decodeBitmapResource(getResources(), (Integer) map.get("sug_pic"));
            int bitH = bitmap.outHeight;
            int bitW = bitmap.outWidth;
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(imgvWidth, FrameLayout.LayoutParams.WRAP_CONTENT);
            lp.height = bitH * lp.width / bitW;
            lp.setMargins(0, 5, 0, 0);
            view.setLayoutParams(lp);

            img.setImageBitmap(decodeSampledBitmapFromResource(getResources(), (Integer) map.get("sug_pic"), lp.width/8, lp.height/8));
            text.setText((String)map.get("sug_title"));
            clos[i % 2].addView(view);

            final int sug_no = i;
            sugs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences.Editor editor = sp.edit();

                    Intent intent = new Intent(getActivity(), SugActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("sug_no",sug_no);

                    editor.putInt("sug_id",(int)Global.sug.get(sug_no).get("sug_id"));
                    editor.commit();

                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
    }



    public static BitmapFactory.Options decodeBitmapResource(Resources resources, int id) {
        opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, id, opts);
        return opts;
    }
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        // 调用上面定义的方法计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // 源图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            // 计算出实际宽高和目标宽高的比率
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
            // 一定都会大于等于目标的宽和高。
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
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
            httplink(Global.sug.get(i),i);
        }
        pageIndex++;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
