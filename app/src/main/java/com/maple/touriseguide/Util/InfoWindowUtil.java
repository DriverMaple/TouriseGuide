package com.maple.touriseguide.Util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.model.LatLng;
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
import com.baidu.mapapi.walknavi.WalkNavigateHelper;
import com.baidu.mapapi.walknavi.adapter.IWEngineInitListener;
import com.baidu.mapapi.walknavi.adapter.IWNaviStatusListener;
import com.baidu.mapapi.walknavi.adapter.IWRoutePlanListener;
import com.baidu.mapapi.walknavi.model.WalkRoutePlanError;
import com.baidu.mapapi.walknavi.params.WalkNaviLaunchParam;
import com.baidu.platform.comapi.walknavi.WalkNaviModeSwitchListener;
import com.maple.touriseguide.Fragment.MapFragment;
import com.maple.touriseguide.R;

/**
 * Created by rrr on 2017/11/28.
 */

public class InfoWindowUtil {
    private MarkerInfoUtil info;
    private Context context;
    private TextView name;
    private TextView addr;
    private LinearLayout navigation;
    private LinearLayout call;
    private InfoWindow infoWindow;

    public InfoWindowUtil(MarkerInfoUtil info, Context context) {
        this.info = info;
        this.context = context;
    }

    public InfoWindow init(final LatLng myPosition){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_info_window, null);
        final LatLng latLng = new LatLng(info.getLatitude(),info.getLongitude());

        name = (TextView) view.findViewById(R.id.name);
        addr = (TextView) view.findViewById(R.id.addr);
        navigation = (LinearLayout) view.findViewById(R.id.navigation);
        call = (LinearLayout) view.findViewById(R.id.call);

        name.setText(info.getUser_name());
        addr.setText(info.getPhone());
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+info.getPhone()));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }catch (SecurityException e){

                }
            }
        });
        navigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*RoutePlanSearch mSearch = null;
                OnGetRoutePlanResultListener listener = new OnGetRoutePlanResultListener() {

                    public void onGetWalkingRouteResult(WalkingRouteResult result) {
                        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                            Toast.makeText(context, "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
                        }
                        if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
                            // 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
                            // result.getSuggestAddrInfo()
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder.setTitle("提示");
                            builder.setMessage("检索地址有歧义，请重新设置。\n可通过getSuggestAddrInfo()接口获得建议查询信息");
                            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            builder.create().show();
                            return;
                        }
                        if (result.error == SearchResult.ERRORNO.NO_ERROR) {
                            nodeIndex = -1;
                            mBtnPre.setVisibility(View.VISIBLE);
                            mBtnNext.setVisibility(View.VISIBLE);

                            if (result.getRouteLines().size() > 1) {
                                nowResultwalk = result;
                                if (!hasShownDialogue) {
                                    MyTransitDlg myTransitDlg = new MyTransitDlg(RoutePlanDemo.this,
                                            result.getRouteLines(),
                                            RouteLineAdapter.Type.WALKING_ROUTE);
                                    myTransitDlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                        @Override
                                        public void onDismiss(DialogInterface dialog) {
                                            hasShownDialogue = false;
                                        }
                                    });
                                    myTransitDlg.setOnItemInDlgClickLinster(new OnItemInDlgClickListener() {
                                        public void onItemClick(int position) {
                                            route = nowResultwalk.getRouteLines().get(position);
                                            WalkingRouteOverlay overlay = new MyWalkingRouteOverlay(mBaidumap);
                                            mBaidumap.setOnMarkerClickListener(overlay);
                                            routeOverlay = overlay;
                                            overlay.setData(nowResultwalk.getRouteLines().get(position));
                                            overlay.addToMap();
                                            overlay.zoomToSpan();
                                        }

                                    });
                                    myTransitDlg.show();
                                    hasShownDialogue = true;
                                }
                            } else if (result.getRouteLines().size() == 1) {
                                // 直接显示
                                route = result.getRouteLines().get(0);
                                WalkingRouteOverlay overlay = new MyWalkingRouteOverlay(mBaidumap);
                                mBaidumap.setOnMarkerClickListener(overlay);
                                routeOverlay = overlay;
                                overlay.setData(result.getRouteLines().get(0));
                                overlay.addToMap();
                                overlay.zoomToSpan();

                            } else {
                                Log.d("route result", "结果数<0");
                                return;
                            }

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
                };
                mSearch.setOnGetRoutePlanResultListener(listener);
                PlanNode stNode = PlanNode.withLocation(latLng);
                PlanNode enNode = PlanNode.withLocation(myPosition);
                mSearch.walkingSearch((new WalkingRoutePlanOption())
                        .from(stNode)
                        .to(enNode));*/
            }
        });

        infoWindow = new InfoWindow(view, latLng, -85);

        return infoWindow;
    }
}
