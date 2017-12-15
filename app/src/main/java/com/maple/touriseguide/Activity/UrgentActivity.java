package com.maple.touriseguide.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.maple.touriseguide.R;
import com.maple.touriseguide.Util.CircleImageView;

/**
 * Created by Maple on 2017/11/26.
 */

public class UrgentActivity extends Dialog {
    protected ImageView cancle;
    protected CircleImageView call_police;
    protected CircleImageView call_tourise;
    protected TextView callOrMessage;
    private int user_role;

    public UrgentActivity(@NonNull Context context,int user_role) {
        super(context, R.style.CustomDialogStyle);
        this.user_role = user_role;
    }

    public void setCancleButton(View.OnClickListener clickListener) {
        cancle.setOnClickListener(clickListener);
    }

    public void setPoliceButton(View.OnClickListener clickListener) {
        call_police.setOnClickListener(clickListener);
    }

    public void setTouriseButton(View.OnClickListener clickListener) {
        call_tourise.setOnClickListener(clickListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setCancelable(false);
        setContentView(R.layout.activity_urgent);
        //设置window背景，默认的背景会有Padding值，不能全屏。当然不一定要是透明，你可以设置其他背景，替换默认的背景即可。
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //一定要在setContentView之后调用，否则无效
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        cancle = (ImageView) findViewById(R.id.cancle);
        call_police = (CircleImageView) findViewById(R.id.call_police);
        call_tourise = (CircleImageView) findViewById(R.id.call_tourise);
        callOrMessage = (TextView) findViewById(R.id.callOrMessage);

        if (user_role == 1){
            callOrMessage.setText("提醒全体游客归团");
        }
    }
}
