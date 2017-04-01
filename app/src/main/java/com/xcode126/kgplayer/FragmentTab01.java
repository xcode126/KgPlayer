package com.xcode126.kgplayer;

import android.view.View;

/**
 * Created by xcode126 on 2016/12/7.
 * 第一个Tab的界面
 */

public class FragmentTab01 extends BaseFragment{
    @Override
    protected View initView() {
        View view = View.inflate(mContext, R.layout.fragment_tab01, null);
        return view;
    }
}
