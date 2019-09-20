package com.xcode126.kgplayer.me;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.xcode126.kgplayer.R;
import com.xcode126.kgplayer.base.WrapperFragment;


/**
 * Auther:  winds
 * Data:    2018/2/27
 * Version: 1.0
 * Desc:    我的
 */


public class MeFragment extends WrapperFragment {

//    @BindView(R.id.mNestedRefreshLayout)
//    NestedRefreshLayout mNestedRefreshLayout;


    public static MeFragment newInstance() {
        return new MeFragment();
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



    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
    }

}
