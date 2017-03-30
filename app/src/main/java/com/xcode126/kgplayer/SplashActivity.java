package com.xcode126.kgplayer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;

import com.xcode126.kgplayer.util.DisplayUtil;
import com.xcode126.kgplayer.util.LogUtils;


/**
 * 启动页
 */
public class SplashActivity extends SuperActivity {
    private Handler handler = new Handler();
    private boolean isStartMain = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        DisplayUtil.getSystemInfo(SplashActivity.this); //获取屏幕宽高
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //3S后才执行到这里
                //执行在主线程中
                startMainActivity();
                LogUtils.i("当前线程名称==" + Thread.currentThread().getName());
            }
        }, 3000);
    }

    /**
     * 跳转到主页面，并且把当前页面关闭掉
     */
    private void startMainActivity() {
        if (!isStartMain) {
            isStartMain = true;
            Intent intent = new Intent(this, MainActivity.class);
            SplashActivity.this.startActivity(intent);
            //关闭当前页面
            SplashActivity.this.finish();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtils.i("onTouchEvent==Action" + event.getAction());
        startMainActivity();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        //把所有的消息和回调移除
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
