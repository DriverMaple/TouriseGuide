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
import java.util.Objects;

/**
 * Created by Maple on 2017/11/6.
 */

public class ChiMemberFragment extends Fragment {
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.chi_fragment_member, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        listView = (ListView) view.findViewById(R.id.member);
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), getData(), R.layout.item_member, new String[]{"head_pic", "member_name", "introduction"}, new int[]{R.id.head_pic, R.id.member_name, R.id.introduction});
        listView.setAdapter(adapter);
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> members = new ArrayList<>();
        Map<String, Object> map = new HashMap<String, Object>();

        for (int i = 0; i < 20; i++) {
            map.put("head_pic", R.drawable.tx);
            map.put("member_name", "mr.maple");
            map.put("introduction", "follow your heart");
            members.add(map);
        }

        return members;
    }
}
