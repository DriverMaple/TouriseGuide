package com.maple.touriseguide.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.maple.touriseguide.R;

/**
 * Created by rrr on 2017/10/31.
 */

public class RegisterActivity extends AppCompatActivity {
    private EditText phone_text;
    private EditText code_text;
    private EditText password_text;
    private EditText repassword_text;
    private Button getcode;
    private Button register;
    private Button back;

    private String phone;
    private String code;
    private String passwrod;
    private String repassword;

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
        setContentView(R.layout.activity_register);
        initView();


    }

    private void initView() {
        phone_text = (EditText) findViewById(R.id.phone);
        code_text = (EditText) findViewById(R.id.code);
        password_text = (EditText) findViewById(R.id.password);
        repassword_text = (EditText) findViewById(R.id.repassword);
        getcode = (Button) findViewById(R.id.getcode);
        register = (Button) findViewById(R.id.register);
        back = (Button) findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                code = code_text.getText().toString();
                phone = phone_text.getText().toString();
                passwrod = password_text.getText().toString();
                repassword = repassword_text.getText().toString();
                if (phone.equals("")&&passwrod.equals("")&&repassword.equals("")){
                    Toast.makeText(getApplicationContext(),"手机号或密码不能为空！",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!passwrod.equals(repassword)){
                    Toast.makeText(getApplicationContext(),"两次密码不一致！",Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
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
