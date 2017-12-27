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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maple.touriseguide.Activity.LoginActivity;
import com.maple.touriseguide.Activity.MainActivity;
import com.maple.touriseguide.Activity.UserActivity;
import com.maple.touriseguide.R;
import com.maple.touriseguide.Util.MyViewPager;

/**
 * Created by rrr on 2017/10/31.
 */

public class CenFragment extends Fragment {
    private Button exists;
    private SharedPreferences sp;
    private ImageView exit;
    private TextView user_name;
    private TextView motto;

    private LinearLayout jbxx;
    private LinearLayout xtsz;
    private LinearLayout xgmm;
    private LinearLayout bbxx;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cen, container, false);
        sp = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        initView(view);
        return view;
    }

    private void initView(View view) {
        exists = (Button) view.findViewById(R.id.exist);
        exit = (ImageView) view.findViewById(R.id.exit);
        user_name = (TextView) view.findViewById(R.id.user_name);
        motto = (TextView) view.findViewById(R.id.motto);
        user_name.setText(sp.getString("nick_name", ""));
        motto.setText(sp.getString("motto", ""));

        jbxx = (LinearLayout) view.findViewById(R.id.jbxx);
        xtsz = (LinearLayout) view.findViewById(R.id.xtsz);
        xgmm = (LinearLayout) view.findViewById(R.id.xgmm);
        bbxx = (LinearLayout) view.findViewById(R.id.bbxx);

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
                editor.remove("guider_phone");
                editor.remove("nick_name");
                editor.remove("motto");
                editor.putBoolean("isLogin", false);
                editor.commit();

                //跳转
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        jbxx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserActivity.class);
                startActivity(intent);
            }
        });
    }
}
