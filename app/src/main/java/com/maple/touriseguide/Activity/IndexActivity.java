package com.maple.touriseguide.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.maple.touriseguide.R;

/**
 * Created by Maple on 2017/11/15.
 */

public class IndexActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGHT = 3000;
    private Handler handler;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.index_activity);
        sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        initView();
    }

    private void initView() {
        handler = new Handler();
        // 延迟SPLASH_DISPLAY_LENGHT时间然后跳转到MainActivity
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sp.getBoolean("isLogin",false)){
                    Intent intent = new Intent(IndexActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(IndexActivity.this, LoginActivity.class);
                    startActivity(intent);
                }

                IndexActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGHT);
    }
}
