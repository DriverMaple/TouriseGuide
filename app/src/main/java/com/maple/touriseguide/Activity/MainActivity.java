package com.maple.touriseguide.Activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.maple.touriseguide.Common.MyFragmentAdapter;
import com.maple.touriseguide.Fragment.CenFragment;
import com.maple.touriseguide.Fragment.SugFragment;
import com.maple.touriseguide.Fragment.MapFragment;
import com.maple.touriseguide.Fragment.TeaFragment;
import com.maple.touriseguide.R;
import com.maple.touriseguide.Util.MyViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MyViewPager vp;

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

        initView();
    }

    private void initView() {
        LinearLayout first = (LinearLayout) findViewById(R.id.first);
        LinearLayout second = (LinearLayout) findViewById(R.id.second);
        LinearLayout third = (LinearLayout) findViewById(R.id.third);
        LinearLayout forth = (LinearLayout) findViewById(R.id.forth);

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

        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp.setCurrentItem(0);
            }
        });
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp.setCurrentItem(1);
            }
        });
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp.setCurrentItem(2);
            }
        });
        forth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp.setCurrentItem(3);
            }
        });

        vp.setOnClickListener(new View.OnClickListener() {
            long[] mHints = new long[3];//初始全部为0
            @Override
            public void onClick(View v) {
                //将mHints数组内的所有元素左移一个位置
                System.arraycopy(mHints, 1, mHints, 0, mHints.length - 1);
                //获得当前系统已经启动的时间
                mHints[mHints.length - 1] = SystemClock.uptimeMillis();
                if (SystemClock.uptimeMillis() - mHints[0] <= 500)
                    Toast.makeText(getApplicationContext(), "当你点击三次之后才会出现", Toast.LENGTH_SHORT).show();
            }
        });

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
            finish();
            System.exit(0);
        }
    }
}
