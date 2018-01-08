package com.maple.touriseguide.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
    private LinearLayout exit_team;
    private Button join_team;
    private MyViewPager viewPager;
    private TextView text_team;
    private TextView text_tourise;
    private EditText e_team_name;
    private EditText e_team_pw;
    private SharedPreferences.Editor editor;

    private List<Fragment> fragments = new ArrayList<Fragment>();
    MyFragmentAdapter adapter;
    ChiMemberFragment chiMemberFragment;
    ChiShareFragment chiShareFragment;
    private boolean is_team = false;

    static int pre_color = Color.rgb(210, 7, 3);
    static int color = Color.rgb(255, 255, 255);
    private SharedPreferences sp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tea, container, false);
        sp = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        editor = sp.edit();
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
        exit_team = (LinearLayout) view.findViewById(R.id.exit_team);
        join_team = (Button) view.findViewById(R.id.join_team);
        e_team_name = (EditText) view.findViewById(R.id.team_name);
        e_team_pw = (EditText) view.findViewById(R.id.team_pw);

        //构造适配器
        chiMemberFragment = new ChiMemberFragment();
        chiShareFragment = new ChiShareFragment();
        fragments.add(chiMemberFragment);
        fragments.add(chiShareFragment);

        adapter = new MyFragmentAdapter(getActivity().getSupportFragmentManager(), fragments);
        //设定适配器
        viewPager.setScanScroll(false);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        tourise.setBackgroundResource(0);
        team.setBackgroundResource(R.drawable.selector_background_underline);
        text_team.setTextColor(pre_color);


        if (sp.getInt("team_id", 0) == 0) {
            showNullTeam();
            is_team = false;
        } else {
            showVp();
            is_team = true;
        }
    }

    private void showNullTeam() {
        have_team.setVisibility(View.GONE);
        exit_team.setVisibility(View.GONE);
        null_team.setVisibility(View.VISIBLE);
        if (sp.getInt("user_role", 0) == 1) {
            join_team.setText("创建团队");
        }

        join_team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String team_name = e_team_name.getText().toString();
                String team_pw = e_team_pw.getText().toString();
                int user_id = sp.getInt("user_id", 0);

                if (sp.getInt("user_role", 0) == 1) {
                    String url = Global.MyIP + "/createTeam";
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
                                             Looper.prepare();
                                             Toast.makeText(getActivity().getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                                             Looper.loop();
                                         }

                                         @Override
                                         public void onResponse(String response, int id) {
                                             Result result = new Result(response, Team.class, false);
                                             Team team = (Team) result.getValue();
                                             if (result.getResult() == 0) {
                                                 Toast.makeText(getActivity().getApplicationContext(), "成功创建！",
                                                         Toast.LENGTH_SHORT).show();
                                                 //记住用户名、密码、
                                                 editor.putInt("team_id", team.getTeam_id());
                                                 editor.commit();
                                                 showVp();
                                             } else {
                                                 Toast.makeText(getActivity().getApplicationContext(), result.getMessage(),
                                                         Toast.LENGTH_SHORT).show();
                                             }
                                         }
                                     }
                            );
                } else if (sp.getInt("user_role", 0) == 2) {
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
                                    //Looper.prepare();
                                    //Toast.makeText(getActivity().getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                                    //Looper.loop();
                                    e.printStackTrace();
                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    Result result = new Result(response, Team.class, false);
                                    Team team = (Team) result.getValue();
                                    if (result.getResult() == 0) {
                                        Toast.makeText(getActivity().getApplicationContext(), "成功加入！",
                                                Toast.LENGTH_SHORT).show();
                                        //记住用户名、密码、

                                        editor.putInt("team_id", team.getTeam_id());
                                        //editor.putInt("team_id", team.getTeam_id());
                                        editor.commit();
                                        if (is_team){
                                            chiMemberFragment.getMates();
                                        }
                                        is_team = true;
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

    private void showVp() {
        have_team.setVisibility(View.VISIBLE);
        exit_team.setVisibility(View.VISIBLE);
        null_team.setVisibility(View.GONE);

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
        exit_team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNormalDialog();
            }
        });
    }

    private void showNormalDialog() {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(getActivity());
        normalDialog.setTitle("注意");
        normalDialog.setMessage("确定退出团队？");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        showNullTeam();
                        String url = "";
                        if (sp.getInt("user_role", 0) == 1) {
                            url = Global.MyIP + "/deleteTeam";
                        } else {
                            url = Global.MyIP + "/exitTeam";
                        }
                        OkHttpUtils
                                .postString()
                                .url(url)
                                .content("{" +
                                        "\"team_id\":" + "\"" + sp.getInt("team_id", 0) + "\"," +
                                        "\"user_id\":" + "\"" + sp.getInt("user_id", 0) + "\"" +
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
                                            Toast.makeText(getActivity(), "成功退出！",
                                                    Toast.LENGTH_SHORT).show();
                                            //记住用户名、密码、
                                            editor.remove("team_id");
                                            editor.putString("guider_phone", "17764581380");
                                            editor.commit();
                                        } else {
                                            Toast.makeText(getActivity().getApplicationContext(), result.getMessage(),
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                });
        normalDialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        // 显示
        normalDialog.show();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
