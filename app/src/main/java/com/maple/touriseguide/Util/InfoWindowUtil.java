package com.maple.touriseguide.Util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.model.LatLng;
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
    private View.OnClickListener myClickListener;

    public InfoWindowUtil(MarkerInfoUtil info, Context context,View.OnClickListener myClickListener) {
        this.info = info;
        this.context = context;
        this.myClickListener = myClickListener;
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

        navigation.setOnClickListener(myClickListener);

        infoWindow = new InfoWindow(view, latLng, -85);

        return infoWindow;
    }
}
