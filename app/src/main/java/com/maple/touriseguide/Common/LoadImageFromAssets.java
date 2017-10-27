package com.maple.touriseguide.Common;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by rrr on 2017/10/27.
 */

public class LoadImageFromAssets {
    private static final Bitmap bitmap = null;
    public static Bitmap load(final Activity activity, final String name){
        new Thread(){
            public void run(){
                try {
                    URL url = new URL("http://images.tuanpubao.cn/banner20171019.png");
                    //获取连接对象，并没有建立连接
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    //设置连接和读取超时
                    conn.setConnectTimeout(5000);
                    conn.setReadTimeout(5000);
                    //设置请求方法，注意必须大写
                    conn.setRequestMethod("GET");
                    //建立连接，发送get请求
                    //conn.connect();
                    //建立连接，然后获取响应吗，200说明请求成功
                    conn.getResponseCode();
                    //从assets/imgs里获取图片资源，传入图片名字
                    InputStream stream = conn.getInputStream();
                    final Bitmap bitmap = BitmapFactory.decodeStream(stream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        return bitmap;
    }

}
