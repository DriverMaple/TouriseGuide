package com.maple.touriseguide.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maple.touriseguide.Common.Global;
import com.maple.touriseguide.Fragment.SugFragment;
import com.maple.touriseguide.R;

import java.util.Map;

/**
 * Created by rrr on 2017/12/20.
 */

public class SugActivity extends AppCompatActivity {
    private LinearLayout sug_pic;
    private TextView sug_name;
    private TextView sug_content;

    private Drawable drawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sug);
        initView();
    }

    private void initView() {
        sug_pic = (LinearLayout) findViewById(R.id.sug_pic);
        sug_name = (TextView) findViewById(R.id.sug_name);
        sug_content = (TextView) findViewById(R.id.sug_content);

        Bundle bundle = this.getIntent().getExtras();
        Integer no = bundle.getInt("sug_no");
        Map<String,Object> sug = Global.sug.get(no);

        BitmapFactory.Options pic= SugFragment.decodeBitmapResource(getResources(), (Integer) sug.get("sug_pic"));
        drawable = new BitmapDrawable(SugFragment.decodeSampledBitmapFromResource(getResources(),(Integer) sug.get("sug_pic"),pic.outWidth/2,pic.outHeight/2));

        sug_content.setMovementMethod(ScrollingMovementMethod.getInstance());
        sug_pic.setBackground(drawable);
        //sug_pic.setBackgroundResource((Integer) sug.get("sug_pic"));
        sug_name.setText(sug.get("sug_title").toString());
        sug_content.setText(sug.get("sug_content").toString());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SugActivity.this.finish();
    }

    @Override
    protected void onDestroy() {
        drawable = null;
        super.onDestroy();
    }
}
