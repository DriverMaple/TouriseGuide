package com.maple.touriseguide.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

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

    SimpleAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.chi_fragment_share, container, false);
        lv = (ListView) view.findViewById(R.id.share);
        bt_share = (ImageView) view.findViewById(R.id.bt_share);
        setData();
        initView(view);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView like = (ImageView) view.findViewById(R.id.like);
                like.setImageResource(R.mipmap.press_like);
            }
        });
        bt_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DynamicActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void initView(View view) {
        adapter = new SimpleAdapter(getActivity(), Global.dynamic, R.layout.item_share, new String[]{"head_pic", "share_name", "share_time", "share_content", "share_pic"}, new int[]{R.id.head_pic, R.id.share_name, R.id.share_time, R.id.share_content, R.id.share_pic});
        lv.setAdapter(adapter);
    }

    private List<Map<String, Object>> setData() {
        Global.initdata();
        return Global.dynamic;
    }

    @Override
    public void onStart() {
        super.onStart();  // Always call the superclass method first
        adapter = new SimpleAdapter(getActivity(), Global.dynamic, R.layout.item_share, new String[]{"head_pic", "share_name", "share_time", "share_content", "share_pic"}, new int[]{R.id.head_pic, R.id.share_name, R.id.share_time, R.id.share_content, R.id.share_pic});
        lv.setAdapter(adapter);
    }
}
