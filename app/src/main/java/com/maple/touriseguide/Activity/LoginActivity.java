package com.maple.touriseguide.Activity;

import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.maple.touriseguide.Common.Global;
import com.maple.touriseguide.Common.Result;
import com.maple.touriseguide.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {
    private Button login;
    private EditText phone_text;
    private EditText password_text;
    private TextView register;

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
        initView();


    }

    private void initView() {
        login = (Button) findViewById(R.id.login);
        phone_text = (EditText) findViewById(R.id.phone);
        password_text = (EditText) findViewById(R.id.password);
        register = (TextView) findViewById(R.id.register);


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
                                Result result = new Gson().fromJson(string, Result.class);
                                if (result.getResult() == 0){
                                    Looper.prepare();
                                    Toast.makeText(getApplicationContext(), "再按一次退出程序",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                    Looper.loop();
                                } else {
                                    Looper.prepare();
                                    Toast.makeText(getApplicationContext(), "再按一次退出程序",
                                            Toast.LENGTH_SHORT).show();
                                    Looper.loop();
                                }
                                return null;
                            }

                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
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
}
