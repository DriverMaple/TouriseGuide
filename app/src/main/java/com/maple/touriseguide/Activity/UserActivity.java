package com.maple.touriseguide.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.maple.touriseguide.R;

/**
 * Created by rrr on 2017/12/27.
 */

public class UserActivity extends AppCompatActivity {
    private SharedPreferences sp;

    private EditText user_name;
    private EditText edit_motto;
    private Button change;
    private Button save;
    private TextView account;
    private ImageView male;
    private ImageView female;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_user);
        sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        initView();
    }

    private void initView() {
        user_name = (EditText) findViewById(R.id.user_name);
        edit_motto = (EditText) findViewById(R.id.edit_motto);
        change = (Button) findViewById(R.id.change);
        save = (Button) findViewById(R.id.save);
        account = (TextView) findViewById(R.id.account);
        male = (ImageView) findViewById(R.id.male);
        female = (ImageView) findViewById(R.id.female);

        user_name.setText(sp.getString("nick_name", ""));
        edit_motto.setText(sp.getString("motto", ""));
        account.setText(sp.getString("account", ""));

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_name.setFocusable(true);
                user_name.setFocusableInTouchMode(true);
                edit_motto.setFocusable(true);
                edit_motto.setFocusableInTouchMode(true);
                male.setClickable(true);
                female.setClickable(true);
                change.setVisibility(View.GONE);
                save.setVisibility(View.VISIBLE);
                user_name.setBackgroundResource(R.drawable.edit_text);
                edit_motto.setBackgroundResource(R.drawable.edit_text);

                male.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        male.setImageResource(R.mipmap.pre_male);
                        female.setImageResource(R.mipmap.female);
                    }
                });
                female.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        male.setImageResource(R.mipmap.male);
                        female.setImageResource(R.mipmap.pre_female);
                    }
                });
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_name.setFocusable(false);
                user_name.setFocusableInTouchMode(false);
                edit_motto.setFocusable(false);
                edit_motto.setFocusableInTouchMode(false);
                male.setClickable(false);
                female.setClickable(false);
                change.setVisibility(View.VISIBLE);
                save.setVisibility(View.GONE);

                user_name.setBackgroundResource(R.color.transparent);
                edit_motto.setBackgroundResource(R.color.transparent);

                Toast.makeText(getApplicationContext(),"修改已保存",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
