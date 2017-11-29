package com.maple.touriseguide.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.maple.touriseguide.Common.Global;
import com.maple.touriseguide.Common.Result;
import com.maple.touriseguide.Entity.Team;
import com.maple.touriseguide.Entity.User;
import com.maple.touriseguide.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {
    private SharedPreferences sp;

    private Button login;
    private EditText phone_text;
    private EditText password_text;
    private TextView register;
    private RadioGroup role;
    private RadioButton youke;
    private RadioButton daoyou;

    private int user_role = 2;
    private String phone;
    private String password;

    // 定义一个变量，来标识是否退出
    private static boolean isExit = false;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        initView();


    }

    private void initView() {
        login = (Button) findViewById(R.id.login);
        phone_text = (EditText) findViewById(R.id.phone);
        password_text = (EditText) findViewById(R.id.password);
        register = (TextView) findViewById(R.id.register);
        role = (RadioGroup) findViewById(R.id.user_role);
        youke = (RadioButton) findViewById(R.id.youke);
        daoyou = (RadioButton) findViewById(R.id.daoyou);


        role.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(R.id.youke == checkedId){
                    user_role = 2;
                }
                else if(R.id.daoyou == checkedId){
                    user_role = 1;
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = phone_text.getText().toString();
                password = password_text.getText().toString();
                String url = Global.MyIP+"/login";
                OkHttpUtils
                        .postString()
                        .url(url)
                        .content("{" +
                                "\"account\":"+"\""+phone+"\","+
                                "\"password\":"+"\""+password+"\""+
                                "}")
                        .mediaType(MediaType.parse("application/json; charset=utf-8"))
                        .build()
                        .execute(new Callback() {
                            @Override
                            public Object parseNetworkResponse(Response response, int id) throws Exception {
                                String string = response.body().string();
                                Result result = new Result(string,User.class,false);
                                User user = (User) result.getValue();
                                if (result.getResult() == 0){
                                    Looper.prepare();
                                    Toast.makeText(getApplicationContext(), "欢迎使用FindU"+user.getUser_id().toString(),
                                            Toast.LENGTH_SHORT).show();
                                    //记住用户名、密码、
                                    SharedPreferences.Editor editor = sp.edit();
                                    editor.putInt("user_id",user.getUser_id());
                                    editor.putString("nick_name",user.getNick_name());
                                    editor.putString("motto",user.getMotto());
                                    editor.putInt("user_role",user_role);
                                    editor.putString("account", phone);
                                    editor.putString("password",password);
                                    editor.putBoolean("isLogin",true);
                                    editor.commit();
                                    checkTeam();
                                    //跳转
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();

                                    Looper.loop();
                                } else {
                                    Looper.prepare();
                                    Toast.makeText(getApplicationContext(), result.getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                    Looper.loop();
                                }
                                return null;
                            }

                            @Override
                            public void onError(Call call, Exception e, int id) {
                                call.cancel();
                                Looper.prepare();
                                Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }

                            @Override
                            public void onResponse(Object response, int id) {
                            }
                        });

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    /**
     * 以下为实现点击两次返回退出应用
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }

    private void checkTeam() {
        String url = Global.MyIP + "/getTeam";
        OkHttpUtils
                .postString()
                .url(url)
                .content("{" +
                        "\"user_id\":" + "\"" + sp.getInt("user_id", 0) + "\"" +
                        "}")
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        call.cancel();
                        Looper.prepare();
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Result result = new Result(response, Team.class, false);
                        Team team = (Team) result.getValue();
                        if (result.getResult() == 0) {
                            if (null != team) {
                                SharedPreferences.Editor editor = sp.edit();
                                //存储team_id
                                editor.putInt("team_id", team.getTeam_id());
                                editor.putString("guider_phone", team.getGuider_phone());
                                editor.commit();
                            }
                        } else {
                            Looper.prepare();
                            Toast.makeText(getApplicationContext(), result.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    }
                });
    }
}
