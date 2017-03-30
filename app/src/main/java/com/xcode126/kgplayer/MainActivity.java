package com.xcode126.kgplayer;

import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.widget.Toast;

/**
 * 作者：sky
 * 邮箱：xcode126@126.com
 * QQ号：1397028339
 * 公众号：走近程序员
 * 作用：影音播放器
 */
public class MainActivity extends SuperActivity {

    private boolean isExit = false;//是否已经退出

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            if (position != 0) {//不是第一页面
//                position = 0;
//                rg_bottom_tag.check(R.id.rb_video);//首页
//                return true;
//            } else
            if (!isExit) {
                isExit = true;
                Toast.makeText(MainActivity.this, "再按一次推出", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isExit = false;
                    }
                }, 2000);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
