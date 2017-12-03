package com.maple.touriseguide.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.chi_fragment_share, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        lv = (ListView) view.findViewById(R.id.share);
        setData();
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), setData(), R.layout.item_share, new String[]{"head_pic", "share_name", "share_time", "share_content" ,"share_pic"}, new int[]{R.id.head_pic, R.id.share_name, R.id.share_time, R.id.share_content, R.id.share_pic});
        lv.setAdapter(adapter);
    }

    private List<Map<String, Object>> setData() {
        List<Map<String, Object>> members = new ArrayList<>();

        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("head_pic", R.drawable.tx);
        map1.put("share_name", "萧枫");
        map1.put("share_time", "2017-12-20");
        map1.put("share_content", "第一条！沙发！");
        map1.put("share_pic", R.drawable.fir);
        members.add(map1);

        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("head_pic", R.drawable.tx);
        map2.put("share_name", "萧枫");
        map2.put("share_time", "2017-12-21");
        map2.put("share_content", "第一条！沙发！");
        map2.put("share_pic", R.drawable.sec);
        members.add(map2);

        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("head_pic", R.drawable.tx);
        map3.put("share_name", "萧枫");
        map3.put("share_time", "2017-12-22");
        map3.put("share_content", "第一条！沙发！");
        map3.put("share_pic", R.drawable.tx);
        members.add(map3);

        Map<String, Object> map4 = new HashMap<String, Object>();
        map4.put("head_pic", R.drawable.tx);
        map4.put("share_name", "萧枫");
        map4.put("share_time", "2017-12-23");
        map4.put("share_content", "第一条！沙发！");
        map4.put("share_pic", R.drawable.thr);
        members.add(map4);

        Map<String, Object> map5 = new HashMap<String, Object>();
        map5.put("head_pic", R.drawable.tx);
        map5.put("share_name", "萧枫");
        map5.put("share_time", "2017-12-24");
        map5.put("share_content", "第一条！沙发！");
        map5.put("share_pic", R.drawable.forth);
        members.add(map5);

        Map<String, Object> map6 = new HashMap<String, Object>();
        map6.put("head_pic", R.drawable.tx);
        map6.put("share_name", "萧枫");
        map6.put("share_time", "2017-12-25");
        map6.put("share_content", "第一条！沙发！");
        map6.put("share_pic", null);
        members.add(map6);

        Map<String, Object> map7 = new HashMap<String, Object>();
        map7.put("head_pic", R.drawable.tx);
        map7.put("share_name", "萧枫");
        map7.put("share_time", "2017-12-26");
        map7.put("share_content", "第一条！沙发！");
        map7.put("share_pic", null);
        members.add(map7);

        Map<String, Object> map8 = new HashMap<String, Object>();
        map8.put("head_pic", R.drawable.tx);
        map8.put("share_name", "萧枫");
        map8.put("share_time", "2017-12-27");
        map8.put("share_content", "第一条！沙发！");
        map8.put("share_pic", R.drawable.fif);
        members.add(map8);

        Map<String, Object> map9 = new HashMap<String, Object>();
        map9.put("head_pic", R.drawable.tx);
        map9.put("share_name", "萧枫");
        map9.put("share_time", "2017-12-28");
        map9.put("share_content", "第一条！沙发！");
        map9.put("share_pic", null);
        members.add(map9);

        return members;

    }
}
