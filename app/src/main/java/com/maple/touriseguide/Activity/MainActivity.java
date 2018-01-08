package com.maple.touriseguide.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.maple.touriseguide.Common.ActivityCollector;
import com.maple.touriseguide.Common.Global;
import com.maple.touriseguide.Common.MyFragmentAdapter;
import com.maple.touriseguide.Common.Result;
import com.maple.touriseguide.Entity.Team;
import com.maple.touriseguide.Fragment.CenFragment;
import com.maple.touriseguide.Fragment.SugFragment;
import com.maple.touriseguide.Fragment.MapFragment;
import com.maple.touriseguide.Fragment.TeaFragment;
import com.maple.touriseguide.R;
import com.maple.touriseguide.Util.CircleImageView;
import com.maple.touriseguide.Util.MyViewPager;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.request.RequestCall;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;

public class MainActivity extends AppCompatActivity {
    private MyViewPager vp;
    static int pre_color = Color.rgb(210, 7, 3);
    static int color = Color.rgb(90, 90, 90);

    private ImageView img_fir;
    private ImageView img_sec;
    private ImageView img_thi;
    private ImageView img_for;

    private TextView text_fir;
    private TextView text_sec;
    private TextView text_thi;
    private TextView text_for;

    private CircleImageView urgent;
    private SharedPreferences sp;
    //Context mContext = getApplicationContext();


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
        setContentView(R.layout.activity_main);
        sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        initView();
    }



    private void initView() {
        LinearLayout first = (LinearLayout) findViewById(R.id.first);
        LinearLayout second = (LinearLayout) findViewById(R.id.second);
        final LinearLayout third = (LinearLayout) findViewById(R.id.third);
        LinearLayout forth = (LinearLayout) findViewById(R.id.forth);

        img_fir = (ImageView) findViewById(R.id.img_fir);
        img_sec = (ImageView) findViewById(R.id.img_sec);
        img_thi = (ImageView) findViewById(R.id.img_thi);
        img_for = (ImageView) findViewById(R.id.img_for);

        text_fir = (TextView) findViewById(R.id.text_fir);
        text_sec = (TextView) findViewById(R.id.text_sec);
        text_thi = (TextView) findViewById(R.id.text_thi);
        text_for = (TextView) findViewById(R.id.text_for);

        urgent = (CircleImageView) findViewById(R.id.urgent);

        //构造适配器
        List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(new SugFragment());
        fragments.add(new TeaFragment());
        fragments.add(new MapFragment());
        fragments.add(new CenFragment());

        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(), fragments);

        //设定适配器
        vp = (MyViewPager) findViewById(R.id.viewpager);
        vp.setScanScroll(false);
        vp.setAdapter(adapter);
        vp.setCurrentItem(0);
        change(0);

        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp.setCurrentItem(0);
                change(0);
            }
        });
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp.setCurrentItem(1);
                change(1);
            }
        });
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp.setCurrentItem(2);
                change(2);
            }
        });
        forth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp.setCurrentItem(3);
                change(3);
            }
        });

        urgent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "长按两秒紧急呼叫", Toast.LENGTH_SHORT).show();
            }
        });

        urgent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getApplicationContext(), "紧急呼叫", Toast.LENGTH_SHORT).show();
                showDialog();
                return true;
            }
        });

    }

    void change(int tab) {
        img_fir.setImageResource(R.mipmap.icon_sug);
        img_sec.setImageResource(R.mipmap.icon_tem);
        img_thi.setImageResource(R.mipmap.icon_map);
        img_for.setImageResource(R.mipmap.icon_cen);

        text_fir.setTextColor(color);
        text_sec.setTextColor(color);
        text_thi.setTextColor(color);
        text_for.setTextColor(color);

        switch (tab) {
            case 0:
                img_fir.setImageResource(R.mipmap.pre_icon_sug);
                text_fir.setTextColor(pre_color);
                break;
            case 1:
                img_sec.setImageResource(R.mipmap.pre_icon_tem);
                text_sec.setTextColor(pre_color);
                break;
            case 2:
                img_thi.setImageResource(R.mipmap.pre_icon_map);
                text_thi.setTextColor(pre_color);
                break;
            case 3:
                img_for.setImageResource(R.mipmap.pre_icon_cen);
                text_for.setTextColor(pre_color);
                break;
            default:
                break;
        }
    }

    /**
     * 以下为实现点击两次返回退出应用
     *
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
            MainActivity.this.finish();
            System.exit(0);
        }
    }

    private void showDialog() {
        final UrgentActivity dialog1 = new UrgentActivity(this,sp.getInt("user_role",0));
        dialog1.show();
        dialog1.setCancleButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });
        dialog1.setPoliceButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder normalDialog =
                        new AlertDialog.Builder(MainActivity.this);
                normalDialog.setTitle("注意");
                normalDialog.setMessage("即将拨通报警电话？");
                normalDialog.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                try {
                                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+"10000"));
                                    startActivity(intent);
                                }catch (SecurityException e){

                                }
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
        });
        dialog1.setTouriseButton(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sp.getInt("user_role",0) == 1){
                    AlertDialog.Builder normalDialog =
                            new AlertDialog.Builder(MainActivity.this);
                    normalDialog.setTitle("注意");
                    normalDialog.setMessage("将向所有成员发送归团提醒短信？");
                    normalDialog.setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
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
                } else {
                    try {
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+sp.getString("guider_phone","")));
                        startActivity(intent);
                    }catch (SecurityException e){

                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //删除该活动
        ActivityCollector.removeActivity(this);
    }
}
