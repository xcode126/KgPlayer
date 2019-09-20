package com.xcode126.kgplayer;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.xcode126.kgplayer.base.WrapperActivity;
import com.xcode126.kgplayer.base.widget.TitleView;

import butterknife.BindView;


/**
 * 启动页
 */
public class SplashActivity extends WrapperActivity {

    @BindView(R.id.iv_image)
    ImageView imageView;

    @Override
    protected int getViewLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView(Bundle savedInstanceState, TitleView titleView, Intent intent) {

//        Glide.with(this).load(R.drawable.splash_bg)
//                .apply(RequestOptions.bitmapTransform(new GlideScaleBottomTransform(AutoUtils.getScreenHeight() / AutoUtils.getScreenWidth())))
//                .into(imageView);

    }

    @Override
    protected void loadData(Bundle savedInstanceState, Intent intent) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(MainActivity.getIntent(mActivity));
            }
        }, 3000);
    }

    @Override
    protected boolean isUseTitle() {
        return false;
    }

}
