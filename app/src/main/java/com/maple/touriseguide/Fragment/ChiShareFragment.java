package com.maple.touriseguide.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.maple.touriseguide.Activity.DynamicActivity;
import com.maple.touriseguide.Activity.LoginActivity;
import com.maple.touriseguide.Activity.MainActivity;
import com.maple.touriseguide.Common.Global;
import com.maple.touriseguide.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Maple on 2017/11/6.
 */

public class ChiShareFragment extends Fragment {
    private ListView lv;
    private ImageView bt_share;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.chi_fragment_share, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        lv = (ListView) view.findViewById(R.id.share);
        bt_share = (ImageView) view.findViewById(R.id.bt_share);
        setData();
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), setData(), R.layout.item_share, new String[]{"head_pic", "share_name", "share_time", "share_content" ,"share_pic"}, new int[]{R.id.head_pic, R.id.share_name, R.id.share_time, R.id.share_content, R.id.share_pic}){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                final View view=super.getView(position, convertView, parent);
                ImageView like = (ImageView)view.findViewById(R.id.like);
                like.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ImageView test = (ImageView) view.findViewById(R.id.like);
                        test.setImageResource(R.mipmap.press_like);
                    }
                });
                return view;
            }
        };
        lv.setAdapter(adapter);
        bt_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DynamicActivity.class);
                startActivity(intent);
            }
        });
    }

    private List<Map<String, Object>> setData() {
        Global.initdata();
        return Global.dynamic;
    }

    @Override
    public void onStart() {
        super.onStart();  // Always call the superclass method first
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), Global.dynamic, R.layout.item_share, new String[]{"head_pic", "share_name", "share_time", "share_content" ,"share_pic"}, new int[]{R.id.head_pic, R.id.share_name, R.id.share_time, R.id.share_content, R.id.share_pic});
        lv.setAdapter(adapter);
    }
}
