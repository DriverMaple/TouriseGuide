package com.maple.touriseguide.Fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.maple.touriseguide.Common.Global;
import com.maple.touriseguide.Common.Result;
import com.maple.touriseguide.Entity.Team;
import com.maple.touriseguide.Entity.User;
import com.maple.touriseguide.R;
import com.maple.touriseguide.Util.MyListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Maple on 2017/11/6.
 */

public class ChiMemberFragment extends Fragment {
    MyListView listView;
    private SharedPreferences sp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.chi_fragment_member, container, false);
        sp = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        getMates();
        initView(view);
        return view;
    }

    private void initView(View view) {
        listView = (MyListView) view.findViewById(R.id.member);
    }

    private List<Map<String, Object>> getData(List<User> users) {
        List<Map<String, Object>> members = new ArrayList<>();

        for (User user : users) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("head_pic", R.drawable.tx);
            map.put("member_name", user.getNick_name());
            map.put("introduction", user.getMotto());
            members.add(map);
        }
        return members;
    }

    public void getMates() {
        String url = Global.MyIP + "/getTeammate";
        OkHttpUtils
                .postString()
                .url(url)
                .content("{" +
                        "\"team_id\":" + "\"" + sp.getInt("team_id", 0) + "\"" +
                        "}")
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Looper.prepare();
                        Toast.makeText(getActivity().getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        final Result result = new Result(response, User.class, true);
                        List<User> us = (List<User>) result.getValue();

                        if (result.getResult() == 0) {
                            SimpleAdapter adapter = new SimpleAdapter(getActivity(), getData(us), R.layout.item_member, new String[]{"head_pic", "member_name", "introduction"}, new int[]{R.id.head_pic, R.id.member_name, R.id.introduction});
                            listView.setAdapter(adapter);
                            listView.setonRefreshListener(new MyListView.OnRefreshListener() {

                                @Override
                                public void onRefresh() {
                                    new AsyncTask<Void, Void, Void>() {
                                        protected Void doInBackground(Void... params) {
                                            try {
                                                Thread.sleep(1000);
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            //us.add(result.getValue());
                                            return null;
                                        }

                                        @Override
                                        protected void onPostExecute(Void result) {
                                            //adapter.notifyDataSetChanged();
                                            //lv.onRefreshComplete();
                                        }
                                    }.execute(null, null, null);
                                }
                            });
                        } else {
                            Looper.prepare();
                            Toast.makeText(getActivity().getApplicationContext(), result.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    }
                });
    }
}
