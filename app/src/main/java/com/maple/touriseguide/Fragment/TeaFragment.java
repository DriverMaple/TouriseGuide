package com.maple.touriseguide.Fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maple.touriseguide.R;

/**
 * Created by rrr on 2017/10/26.
 */

public class TeaFragment extends Fragment implements View.OnClickListener {
    private LinearLayout team;
    private LinearLayout tourise;

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

        team.setOnClickListener(this);
        tourise.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.team:
                tourise.setBackgroundResource(0);
                team.setBackgroundResource(R.drawable.selector_background_underline);
                break;
            case R.id.tourise:
                team.setBackgroundResource(0);
                tourise.setBackgroundResource(R.drawable.selector_background_underline);
                break;
            default:
                break;
        }
    }
}
