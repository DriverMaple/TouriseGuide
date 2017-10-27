package com.maple.touriseguide.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import com.maple.touriseguide.Common.MyFragmentStateAdapter;
import com.maple.touriseguide.Fragment.FirstFragment;
import com.maple.touriseguide.Fragment.MapFragment;
import com.maple.touriseguide.Fragment.SecondFragment;
import com.maple.touriseguide.R;
import com.maple.touriseguide.Util.MyViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MyViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        LinearLayout first = (LinearLayout) findViewById(R.id.first);
        LinearLayout second = (LinearLayout) findViewById(R.id.second);
        LinearLayout third = (LinearLayout) findViewById(R.id.third);
        LinearLayout forth = (LinearLayout) findViewById(R.id.forth);

        //构造适配器
        List<Fragment> fragments=new ArrayList<Fragment>();
        fragments.add(new MapFragment());
        fragments.add(new FirstFragment());
        fragments.add(new SecondFragment());
        MyFragmentStateAdapter adapter = new MyFragmentStateAdapter(getSupportFragmentManager(), fragments);

        //设定适配器
        vp = (MyViewPager) findViewById(R.id.viewpager);
        vp.setScanScroll(false);
        vp.setAdapter(adapter);
        vp.setCurrentItem(0);

        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp.setCurrentItem(0);
            }
        });
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp.setCurrentItem(1);
            }
        });
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp.setCurrentItem(3);
            }
        });
        /*forth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp.setCurrentItem(4);
            }
        });*/

    }
}
