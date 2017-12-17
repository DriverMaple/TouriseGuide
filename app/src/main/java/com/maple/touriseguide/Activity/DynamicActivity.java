package com.maple.touriseguide.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.maple.touriseguide.Common.Global;
import com.maple.touriseguide.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rrr on 2017/12/15.
 */

public class DynamicActivity extends AppCompatActivity {
    private SharedPreferences sp;

    private EditText myMessage;
    private ImageView chooseImg;
    private Button send;
    private Button cancle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dynamic);
        sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        initView();
    }

    private void initView() {
        myMessage = (EditText) findViewById(R.id.myMessage);
        chooseImg = (ImageView) findViewById(R.id.chooseImg);
        send = (Button) findViewById(R.id.send);
        cancle = (Button) findViewById(R.id.cancle);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String str = sdf.format(new Date());
                String content = myMessage.getText().toString();
                Global.adddata(sp.getString("nick_name",""),str,content);
                Toast.makeText(getApplicationContext(),"发表成功！",Toast.LENGTH_SHORT);
                DynamicActivity.this.finish();
            }
        });

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DynamicActivity.this.finish();
            }
        });
    }
}
