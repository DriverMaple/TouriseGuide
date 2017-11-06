package com.maple.touriseguide.Fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

        team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"1",Toast.LENGTH_SHORT).show();
                tourise.setBackgroundResource(0);
                team.setBackgroundResource(R.drawable.selector_background_underline);
                viewPager.setCurrentItem(0);
            }
        });
        tourise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"2",Toast.LENGTH_SHORT).show();
                team.setBackgroundResource(0);
                tourise.setBackgroundResource(R.drawable.selector_background_underline);
                viewPager.setCurrentItem(1);
            }
        });
    }
}
