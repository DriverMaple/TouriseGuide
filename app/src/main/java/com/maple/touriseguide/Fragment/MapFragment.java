package com.maple.touriseguide.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.overlayutil.OverlayManager;
import com.baidu.mapapi.overlayutil.WalkingRouteOverlay;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.maple.touriseguide.Activity.LoginActivity;
import com.maple.touriseguide.Activity.MainActivity;
import com.maple.touriseguide.Common.Global;
import com.maple.touriseguide.Common.Result;
import com.maple.touriseguide.Entity.User;
import com.maple.touriseguide.R;
import com.maple.touriseguide.Util.InfoWindowUtil;
import com.maple.touriseguide.Util.MarkerInfoUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Response;

/**
 * Created by Maple on 2017/10/25.
 */

public class MapFragment extends Fragment implements BaiduMap.OnMapClickListener, OnGetRoutePlanResultListener {
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

    //是否清空地图
    private boolean isFresh = true;

    //定位回调
    private BDLocationListener mBDLocationListener = new MyLocationListener();

    //上一个节点
    //Button mBtnPre = null;
    //下一个节点
    //Button mBtnNext = null;
    //节点索引,供浏览节点时使用
    int nodeIndex = -1;

    /**
     * 路线数据结构的基类,表示一条路线，路线可能包括：路线规划中的换乘/驾车/步行路线
     * 此类为路线数据结构的基类，一般关注其子类对象即可，无需直接生成该类对象
     */
    RouteLine route = null;

    /**
     * 该类提供一个能够显示和管理多个Overlay的基类
     */
    OverlayManager routeOverlay = null;

    boolean useDefaultIcon = false;
    //private TextView popupText = null;//泡泡view
    /**
     * 路径规划搜索接口
     */
    RoutePlanSearch mSearch = null;    // 搜索模块，也可去掉地图模块独立使用

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getActivity().getApplicationContext());
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        sp = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        user_id = String.valueOf(sp.getInt("user_id", 0));

        /**
         * public static RoutePlanSearch newInstance()
         * 获取RoutePlan检索实例
         * @param RoutePlan检索实例
         * */
        mSearch = RoutePlanSearch.newInstance();
        mSearch.setOnGetRoutePlanResultListener(this);

        //初始化试图
        initView(view);
        //初始化定位相关
        initLoc();
        //开始定位
        mLocationClient.start();
        return view;
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
        if (child != null && (child instanceof ImageView || child instanceof ZoomControls)) {
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

    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    public boolean onMapPoiClick(MapPoi mapPoi) {
        return false;
    }

    @Override
    public void onGetWalkingRouteResult(WalkingRouteResult result) {
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(getActivity(), "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
        }
        if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
            //起终点或途经点地址有岐义，通过以下接口获取建议查询信息
            //result.getSuggestAddrInfo()
            return;
        }
        if (result.error == SearchResult.ERRORNO.NO_ERROR) {
            nodeIndex = -1;
            //mBtnPre.setVisibility(View.VISIBLE);
            //mBtnNext.setVisibility(View.VISIBLE);

            /**
             * public java.util.List<WalkingRouteLine> getRouteLines()
             * 获取所有步行规划路线
             * 返回:所有步行规划路线
             * */
            route = result.getRouteLines().get(0);

            WalkingRouteOverlay overlay = new MyWalkingRouteOverlay(mBaiduMap);

            /**
             * 设置地图 Marker 覆盖物点击事件监听者
             * 需要实现的方法：     onMarkerClick(Marker marker)
             * */
            mBaiduMap.setOnMarkerClickListener(overlay);
            routeOverlay = overlay;

            /**
             * public void setData(WalkingRouteLine line)设置路线数据。
             * 参数:line - 路线数据
             * */
            overlay.setData(result.getRouteLines().get(0));

            /**
             * public final void addToMap()将所有Overlay 添加到地图上
             * */
            overlay.addToMap();

            /**
             * public void zoomToSpan()
             * 缩放地图，使所有Overlay都在合适的视野内
             * 注： 该方法只对Marker类型的overlay有效
             * */
            overlay.zoomToSpan();
        }
    }

    @Override
    public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

    }

    @Override
    public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

    }

    @Override
    public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {

    }

    @Override
    public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

    }

    @Override
    public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

    }

    private class MyLocationListener implements BDLocationListener {
        List<MarkerInfoUtil> infos = new ArrayList<>();
        MarkerInfoUtil info = new MarkerInfoUtil();

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

            ll = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
            //第一次定位需要更新下地图显示状态
            if (isFirstLoc) {
                isFirstLoc = false;
                MapStatus.Builder builder = new MapStatus.Builder()
                        .target(ll)//地图缩放中心点
                        .zoom(18f);//缩放倍数 百度地图支持缩放21级 部分特殊图层为20级
                //改变地图状态
                mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
            MyPosition(bdLocation);
            if (sp.getInt("team_id", 0) != 0) {
                if (sp.getInt("user_role", 0) == 2) {
                    showGuider();
                } else {
                    showTourise();
                }
            } else {
                mBaiduMap.clear();
            }
        }

        private void MyPosition(BDLocation bdLocation) {
            String url = Global.MyIP + "/location";
            OkHttpUtils
                    .postString()
                    .url(url)
                    .content("{" +
                            "\"user_id\":" + "\"" + user_id + "\"," +
                            "\"longitude\":" + "\"" + String.valueOf(bdLocation.getLongitude()) + "\"," +
                            "\"latitude\":" + "\"" + String.valueOf(bdLocation.getLatitude()) + "\"" +
                            "}")
                    .mediaType(MediaType.parse("application/json; charset=utf-8"))
                    .build()
                    .execute(new Callback() {
                        @Override
                        public Object parseNetworkResponse(Response response, int id) throws Exception {
                            String string = response.body().string();
                            Result result = new Result(string, null, false);
                            if (result.getResult() == 0) {
                            } else {
                                Toast.makeText(getActivity().getApplicationContext(), result.getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                            return null;
                        }

                        @Override
                        public void onError(Call call, Exception e, int id) {
                            call.cancel();
                            Looper.prepare();
                            Toast.makeText(getActivity().getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }

                        @Override
                        public void onResponse(Object response, int id) {
                        }
                    });
        }

        private void showGuider() {
            String url = Global.MyIP + "/getGuide";

            OkHttpUtils
                    .postString()
                    .url(url)
                    .content("{" +
                            "\"team_id\":" + "\"" + sp.getInt("team_id", 0) + "\"" +
                            "}")
                    .mediaType(MediaType.parse("application/json; charset=utf-8"))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            call.cancel();
                            Looper.prepare();
                            Toast.makeText(getActivity().getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Result result = new Result(response, User.class, false);
                            User user = (User) result.getValue();
                            if (result.getResult() == 0) {
                                infos.clear();
                                infos.add(new MarkerInfoUtil(user.getLatitude(), user.getLongitude(), user.getNick_name(), user.getAccount()));
                                if (isFresh) {
                                    addOverlay(infos);
                                }
                            } else {
                                Looper.prepare();
                                Toast.makeText(getActivity().getApplicationContext(), result.getMessage(),
                                        Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }
                        }
                    });
        }

        private void showTourise() {
            String url = Global.MyIP + "/getTourise";

            OkHttpUtils
                    .postString()
                    .url(url)
                    .content("{" +
                            "\"team_id\":" + "\"" + sp.getInt("team_id", 0) + "\"" +
                            "}")
                    .mediaType(MediaType.parse("application/json; charset=utf-8"))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            call.cancel();
                            Looper.prepare();
                            Toast.makeText(getActivity().getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Result result = new Result(response, User.class, true);
                            List<User> users = (List<User>) result.getValue();
                            if (result.getResult() == 0) {
                                infos.clear();
                                for (User user : users) {
                                    infos.add(new MarkerInfoUtil(user.getLatitude(), user.getLongitude(), user.getNick_name(), user.getAccount()));
                                }
                                if (isFresh) {
                                    addOverlay(infos);
                                }
                            } else {
                                Toast.makeText(getActivity().getApplicationContext(), result.getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

        //显示marker
        private void addOverlay(final List<MarkerInfoUtil> infos) {
            //清空地图
            mBaiduMap.clear();
            //创建marker的显示图标
            BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.bmap_icon);
            LatLng latLng = null;
            Marker marker;
            OverlayOptions options;
            for (MarkerInfoUtil info : infos) {
                //获取经纬度
                latLng = new LatLng(info.getLatitude(), info.getLongitude());
                //设置marker
                options = new MarkerOptions()
                        .position(latLng)//设置位置
                        .icon(bitmap)//设置图标样式
                        .zIndex(9) // 设置marker所在层级
                        .draggable(true); // 设置手势拖拽;
                //添加marker
                marker = (Marker) mBaiduMap.addOverlay(options);
                //使用marker携带info信息，当点击事件的时候可以通过marker获得info信息
                Bundle bundle = new Bundle();
                //info必须实现序列化接口
                bundle.putSerializable("info", info);
                marker.setExtraInfo(bundle);
            }
            //将地图显示在最后一个marker的位置
            //MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
            //mBaiduMap.setMapStatus(msu);
            mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    //从marker中获取info信息
                    Bundle bundle = marker.getExtraInfo();
                    final MarkerInfoUtil infoUtil = (MarkerInfoUtil) bundle.getSerializable("info");
                    InfoWindowUtil myInfoWindow = new InfoWindowUtil(infoUtil, getActivity().getApplicationContext(), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //重置浏览节点的路线数据
                            route = null;
                            PlanNode stNode = PlanNode.withLocation(ll);
                            PlanNode enNode = PlanNode.withLocation(new LatLng(infoUtil.getLatitude(), infoUtil.getLongitude()));
                            mSearch.walkingSearch((new WalkingRoutePlanOption())
                                    .from(stNode)
                                    .to(enNode));
                        }
                    });
                    mBaiduMap.showInfoWindow(myInfoWindow.init(ll));
                    mBaiduMap.setOnMapTouchListener(new BaiduMap.OnMapTouchListener() {
                        @Override
                        public void onTouch(MotionEvent motionEvent) {
                            mBaiduMap.hideInfoWindow();
                            isFresh = true;
                        }
                    });
                    isFresh = false;
                    return true;
                }
            });
        }
    }

    /**
     * WalkingRouteOverlay已经实现了BaiduMap.OnMarkerClickListener接口
     */
    private class MyWalkingRouteOverlay extends WalkingRouteOverlay {

        public MyWalkingRouteOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        /**
         * public BitmapDescriptor getStartMarker()
         * 覆写此方法以改变默认起点图标
         * 返回:起点图标
         */
        @Override
        public BitmapDescriptor getStartMarker() {
            if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.mipmap.rmap_icon);
            }
            return null;
        }

        /**
         * public BitmapDescriptor getTerminalMarker()
         * 覆写此方法以改变默认终点图标
         * 返回:终点图标
         */
        @Override
        public BitmapDescriptor getTerminalMarker() {
            if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.mipmap.bmap_icon);
            }
            return null;
        }
    }

    @Override
    public void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    public void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        if (mLocationClient != null)
            mLocationClient.stop();
        mMapView.onDestroy();
        super.onDestroy();
    }
}
