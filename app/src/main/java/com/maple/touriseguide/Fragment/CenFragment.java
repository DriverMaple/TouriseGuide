package com.maple.touriseguide.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.maple.touriseguide.R;

/**
 * Created by rrr on 2017/10/31.
 */

public class CenFragment extends Fragment{
    private Button exists;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cen, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        exists = (Button) view.findViewById(R.id.exist);

        exists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                System.exit(0);
            }
        });
    }
}
