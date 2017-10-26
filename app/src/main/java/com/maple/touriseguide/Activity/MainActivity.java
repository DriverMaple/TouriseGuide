package com.maple.touriseguide.Activity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.maple.touriseguide.Common.MyFragmentStateAdapter;
import com.maple.touriseguide.Fragment.FirstFragment;
import com.maple.touriseguide.Fragment.MapFragment;
import com.maple.touriseguide.Fragment.SecondFragment;
import com.maple.touriseguide.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        //构造适配器
        List<Fragment> fragments=new ArrayList<Fragment>();
        fragments.add(new MapFragment());
        fragments.add(new FirstFragment());
        fragments.add(new SecondFragment());
        MyFragmentStateAdapter adapter = new MyFragmentStateAdapter(getSupportFragmentManager(), fragments);

        //设定适配器
        ViewPager vp = (ViewPager)findViewById(R.id.viewpager);
        vp.setAdapter(adapter);

    }

}
