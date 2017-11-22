package com.maple.touriseguide.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.maple.touriseguide.Activity.LoginActivity;
import com.maple.touriseguide.Activity.MainActivity;
import com.maple.touriseguide.Common.Global;
import com.maple.touriseguide.Common.Result;
import com.maple.touriseguide.Entity.User;
import com.maple.touriseguide.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Response;

/**
 * Created by Maple on 2017/10/25.
 */

public class MapFragment extends Fragment {
    private MapView mMapView = null;
    private BaiduMap mBaiduMap;
    private ImageView locate_you;
    private LatLng ll;
    private String user_id;
    private SharedPreferences sp;
    //显示定位点
    private BitmapDescriptor mMarker;
    //定位类
    private LocationClient mLocationClient = null;
    //是否是第一次定位
    private boolean isFirstLoc = true;

    //定位回调
    private BDLocationListener mBDLocationListener = new MyLocationListener();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getActivity().getApplicationContext());
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_map, container, false);
        sp = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        user_id = String.valueOf(sp.getInt("user_id",0));

        //初始化试图
        initView(view);
        //初始化定位相关
        initLoc();
        //开始定位
        mLocationClient.start();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //在Fragment执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //在<span style="font-family: 微软雅黑, 'Microsoft YaHei', sans-serif;">Fragment</span>执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在Fragment执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    /**
     * 定位设置
     */
    private void initLoc() {

        //开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        //定位相关参数设置
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备

        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系
        //共有三种坐标可选
        //1. gcj02：国测局坐标；
        //2. bd09：百度墨卡托坐标；
        //3. bd09ll：百度经纬度坐标；

        int span = 3000;
        option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的

        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要

        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps

        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果

        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”

        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到

        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死

        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集

        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要

        //加载设置
        mLocationClient.setLocOption(option);

    }

    private void initView(View view) {
        //地图显示控件 同时需要处理它的生命周期
        mMapView = (MapView) view.findViewById(R.id.bmapView);
        locate_you = (ImageView) view.findViewById(R.id.locate_you);

        locate_you.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //让地图以被点击的覆盖物为中心
                MapStatusUpdate status = MapStatusUpdateFactory.newLatLng(ll);
//                mBaiduMap.setMapStatus(status);
                //以动画方式更新地图状态，动画耗时 500 ms
                mBaiduMap.animateMapStatus(status, 500);
            }
        });
        //隐藏地图logo
        View child = mMapView.getChildAt(1);
        if (child != null && (child instanceof ImageView || child instanceof ZoomControls)){
            child.setVisibility(View.INVISIBLE);
        }
        //隐藏地图缩放按钮
        mMapView.showZoomControls(false);
        //获取BadiuMap实例 地图控制器类
        mBaiduMap = mMapView.getMap();
        mLocationClient = new LocationClient(getActivity());
        //注册定位回调
        mLocationClient.registerLocationListener(mBDLocationListener);

        //覆盖物 用于显示当前位置
        mMarker = BitmapDescriptorFactory.fromResource(R.mipmap.rmap_icon);
    }

    private class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            //构造定位数据
            MyLocationData data = new MyLocationData.Builder()
                    .accuracy(bdLocation.getRadius())//定位精度
                    .latitude(bdLocation.getLatitude())//纬度
                    .longitude(bdLocation.getLongitude())//经度
                    .direction(bdLocation.getDirection())//方向 可利用手机方向传感器获取 此处为方便写死
                    .build();
            //设置定位数据
            mBaiduMap.setMyLocationData(data);

            //配置定位图层显示方式
            //有两个不同的构造方法重载 分别为三个参数和五个参数的
            //这里主要讲一下常用的三个参数的构造方法
            //三个参数：LocationMode(定位模式：罗盘，跟随),enableDirection（是否允许显示方向信息）
            // ,customMarker（自定义图标）
            MyLocationConfiguration configuration = new MyLocationConfiguration(
                    MyLocationConfiguration.LocationMode.NORMAL, false, mMarker);

            mBaiduMap.setMyLocationConfiguration(configuration);

            ll = new LatLng(bdLocation.getLatitude(),bdLocation.getLongitude());
            //第一次定位需要更新下地图显示状态
            if (isFirstLoc) {
                isFirstLoc = false;
                MapStatus.Builder builder = new MapStatus.Builder()
                        .target(ll)//地图缩放中心点
                        .zoom(18f);//缩放倍数 百度地图支持缩放21级 部分特殊图层为20级
                //改变地图状态
                mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }

            String url = Global.MyIP+"/location";
            OkHttpUtils
                    .postString()
                    .url(url)
                    .content("{" +
                            "\"user_id\":"+"\""+user_id+"\","+
                            "\"longitude\":"+"\""+String.valueOf(bdLocation.getLongitude())+"\","+
                            "\"latitude\":"+"\""+String.valueOf(bdLocation.getLatitude())+"\""+
                            "}")
                    .mediaType(MediaType.parse("application/json; charset=utf-8"))
                    .build()
                    .execute(new Callback() {
                        @Override
                        public Object parseNetworkResponse(Response response, int id) throws Exception {
                            String string = response.body().string();
                            Result result = new Result(string,null,false);
                            User user = (User) result.getValue();
                            if (result.getResult() == 0){
                            } else {
                                Looper.prepare();
                                Toast.makeText(getActivity().getApplicationContext(), result.getMessage(),
                                        Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }
                            return null;
                        }

                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Looper.prepare();
                            Toast.makeText(getActivity().getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }

                        @Override
                        public void onResponse(Object response, int id) {
                        }
                    });


        }
    }
}
