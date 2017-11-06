package com.maple.touriseguide.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.maple.touriseguide.R;

/**
 * Created by Maple on 2017/11/6.
 */

public class ChiMemberFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.chi_fragment_member, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
    }
}
