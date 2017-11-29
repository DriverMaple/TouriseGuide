package com.maple.touriseguide.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.maple.touriseguide.Common.Global;
import com.maple.touriseguide.Common.MyFragmentAdapter;
import com.maple.touriseguide.Common.Result;
import com.maple.touriseguide.Entity.Team;
import com.maple.touriseguide.R;
import com.maple.touriseguide.Util.MyViewPager;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by rrr on 2017/10/26.
 */

public class TeaFragment extends Fragment {
    private LinearLayout team;
    private LinearLayout tourise;
    private LinearLayout null_team;
    private LinearLayout have_team;
    private Button join_team;
    private MyViewPager viewPager;
    private TextView text_team;
    private TextView text_tourise;
    private EditText e_team_name;
    private EditText e_team_pw;

    static int pre_color = Color.rgb(210,7,3);
    static int color = Color.rgb(255,255,255);
    private SharedPreferences sp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tea, container, false);
        sp = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        initView(view);
        return view;
    }

    private void initView(View view) {
        team = (LinearLayout) view.findViewById(R.id.team);
        tourise = (LinearLayout) view.findViewById(R.id.tourise);
        viewPager = (MyViewPager) view.findViewById(R.id.vp);
        text_team = (TextView) view.findViewById(R.id.text_team);
        text_tourise = (TextView) view.findViewById(R.id.text_tourise);

        null_team = (LinearLayout) view.findViewById(R.id.null_team);
        have_team = (LinearLayout) view.findViewById(R.id.have_team);
        join_team = (Button) view.findViewById(R.id.join_team);
        e_team_name = (EditText) view.findViewById(R.id.team_name);
        e_team_pw = (EditText) view.findViewById(R.id.team_pw);



        if (sp.getInt("team_id",0) == 0){
            showNullTeam();
        } else {
            showVp();
        }
    }

    private void showNullTeam() {
        have_team.setVisibility(View.GONE);
        null_team.setVisibility(View.VISIBLE);
        if (sp.getInt("user_role",0) == 1){
            join_team.setText("创建团队");
        }

        join_team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String team_name = e_team_name.getText().toString();
                String team_pw = e_team_pw.getText().toString();
                int user_id = sp.getInt("user_id",0);

                if (sp.getInt("user_role",0) == 1){
                    String url = Global.MyIP+"/createTeam";
                    OkHttpUtils
                            .postString()
                            .url(url)
                            .content("{" +
                                    "\"team_name\":"+"\""+team_name+"\","+
                                    "\"team_pw\":"+"\""+team_pw+"\","+
                                    "\"user_id\":"+"\""+user_id+"\""+
                                    "}")
                            .mediaType(MediaType.parse("application/json; charset=utf-8"))
                            .build()
                            .execute(new StringCallback() {
                                         @Override
                                         public void onError(Call call, Exception e, int id) {
                                             call.cancel();
                                             Looper.prepare();
                                             Toast.makeText(getActivity().getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
                                             Looper.loop();
                                         }

                                         @Override
                                         public void onResponse(String response, int id) {
                                             Result result = new Result(response,Team.class,false);
                                             Team team = (Team) result.getValue();
                                             if (result.getResult() == 0){
                                                 Toast.makeText(getActivity().getApplicationContext(), "成功创建！"+team.getTeam_id().toString(),
                                                         Toast.LENGTH_SHORT).show();
                                                 //记住用户名、密码、
                                                 SharedPreferences.Editor editor = sp.edit();
                                                 editor.putInt("team_id",team.getTeam_id());
                                                 editor.commit();
                                                 showVp();
                                             } else {
                                                 Toast.makeText(getActivity().getApplicationContext(), result.getMessage(),
                                                         Toast.LENGTH_SHORT).show();
                                             }
                                         }
                                     }
                            );
                } else if (sp.getInt("user_role",0) == 2) {
                    String url = Global.MyIP + "/joinTeam";
                    OkHttpUtils
                            .postString()
                            .url(url)
                            .content("{" +
                                    "\"team_name\":" + "\"" + team_name + "\"," +
                                    "\"team_pw\":" + "\"" + team_pw + "\"," +
                                    "\"user_id\":" + "\"" + user_id + "\"" +
                                    "}")
                            .mediaType(MediaType.parse("application/json; charset=utf-8"))
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    call.cancel();
                                    /*Looper.prepare();
                                    Toast.makeText(getActivity().getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                                    Looper.loop();*/
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    Result result = new Result(response, Team.class, false);
                                    Team team = (Team) result.getValue();
                                    if (result.getResult() == 0) {
                                        Toast.makeText(getActivity().getApplicationContext(), "成功加入！" + team.getTeam_id().toString(),
                                                Toast.LENGTH_SHORT).show();
                                        //记住用户名、密码、
                                        SharedPreferences.Editor editor = sp.edit();
                                        editor.putInt("team_id", team.getTeam_id());
                                        editor.commit();
                                        showVp();
                                    } else {
                                        Toast.makeText(getActivity().getApplicationContext(), result.getMessage(),
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }

    private void showVp(){
        have_team.setVisibility(View.VISIBLE);
        null_team.setVisibility(View.GONE);
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
