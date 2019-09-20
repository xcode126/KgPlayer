package com.xcode126.kgplayer.home;

import android.os.Bundle;
import com.xcode126.kgplayer.R;
import com.xcode126.kgplayer.base.WrapperFragment;


/**
 * 作者：sky
 * 邮箱：xcode126@126.com
 * QQ号：1397028339
 * 公众号：程序教科书
 * desc：首页
 */

public class HomeFragment extends WrapperFragment {

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public int getViewLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }
    @Override
    protected void loadData(Bundle savedInstanceState) {

    }
}