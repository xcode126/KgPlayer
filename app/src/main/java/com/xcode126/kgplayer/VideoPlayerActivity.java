package com.xcode126.kgplayer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.xcode126.kgplayer.base.WrapperActivity;
import com.xcode126.kgplayer.base.widget.TitleView;
import com.xcode126.kgplayer.util.MyJzvdStd;

import butterknife.BindView;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

/**
 * Author：sky on 2019/4/23 15:35.
 * Email：xcode126@126.com
 * Desc：视频播放界面
 */

public class VideoPlayerActivity extends WrapperActivity {

    @BindView(R.id.mJzvdStd)
    MyJzvdStd mJzvdStd;

    public static Intent getIntent(Context mContext, String title, String url) {
        Intent intent = new Intent(mContext, VideoPlayerActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        return intent;
    }

    @Override
    protected int getViewLayout() {
        return R.layout.activity_video_player;
    }

    @Override
    protected void initView(Bundle savedInstanceState, TitleView titleView, Intent intent) {
    }

    @Override
    protected void loadData(Bundle savedInstanceState, Intent intent) {
        startPlay(intent);
    }

    @Override
    protected boolean isUseTitle() {
        return false;
    }

    /**
     * 开启播放
     * @param intent
     */
    private void startPlay(Intent intent) {
        String url = intent.getStringExtra("url");
        String title = intent.getStringExtra("title");
//        JzvdStd.startFullscreenDirectly(this, JzvdStd.class, url, title);
        mJzvdStd.setUp(url, "饺子快长大", JzvdStd.SCREEN_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }

    @Override
    public void onBackPressedSupport() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressedSupport();
    }
}
