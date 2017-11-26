package com.maple.touriseguide.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.maple.touriseguide.Activity.LoginActivity;
import com.maple.touriseguide.Activity.MainActivity;
import com.maple.touriseguide.R;
import com.maple.touriseguide.Util.MyViewPager;

/**
 * Created by rrr on 2017/10/31.
 */

public class CenFragment extends Fragment{
    private Button exists;
    private SharedPreferences sp;
    private ImageView exit;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cen, container, false);
        sp = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        initView(view);
        return view;
    }

    private void initView(View view) {
        exists = (Button) view.findViewById(R.id.exist);
        exit = (ImageView) view.findViewById(R.id.exit);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });

        exists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //记住用户名、密码、
                SharedPreferences.Editor editor = sp.edit();
                editor.remove("account");
                editor.remove("password");
                editor.remove("user_id");
                editor.remove("user_role");
                editor.remove("team_id");
                editor.putBoolean("isLogin",false);
                editor.commit();

                //跳转
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
                //getActivity().finish();
                //System.exit(0);
            }
        });
    }
}
