package com.maple.touriseguide.Fragment;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.Text;
import com.maple.touriseguide.Common.MyFragmentAdapter;
import com.maple.touriseguide.R;
import com.maple.touriseguide.Util.MyViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rrr on 2017/10/26.
 */

public class TeaFragment extends Fragment {
    private LinearLayout team;
    private LinearLayout tourise;
    private MyViewPager viewPager;
    private TextView text_team;
    private TextView text_tourise;

    static int pre_color = Color.rgb(210,7,3);
    static int color = Color.rgb(255,255,255);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tea, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        team = (LinearLayout) view.findViewById(R.id.team);
        tourise = (LinearLayout) view.findViewById(R.id.tourise);
        viewPager = (MyViewPager) view.findViewById(R.id.vp);
        text_team = (TextView) view.findViewById(R.id.text_team);
        text_tourise = (TextView) view.findViewById(R.id.text_tourise);

        //构造适配器
        List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(new ChiMemberFragment());
        fragments.add(new ChiShareFragment());

        MyFragmentAdapter adapter = new MyFragmentAdapter(getActivity().getSupportFragmentManager(), fragments);

        //设定适配器
        viewPager.setScanScroll(false);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        tourise.setBackgroundResource(0);
        team.setBackgroundResource(R.drawable.selector_background_underline);
        text_team.setTextColor(pre_color);

        team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tourise.setBackgroundResource(0);
                text_tourise.setTextColor(color);
                team.setBackgroundResource(R.drawable.selector_background_underline);
                text_team.setTextColor(pre_color);
                viewPager.setCurrentItem(0);
            }
        });
        tourise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                team.setBackgroundResource(0);
                text_team.setTextColor(color);
                tourise.setBackgroundResource(R.drawable.selector_background_underline);
                text_tourise.setTextColor(pre_color);
                viewPager.setCurrentItem(1);
            }
        });
    }
}
