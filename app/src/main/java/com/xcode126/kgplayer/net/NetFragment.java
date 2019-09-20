package com.xcode126.kgplayer.net;

import android.os.Bundle;

import com.xcode126.kgplayer.R;
import com.xcode126.kgplayer.base.WrapperFragment;



/**
 * Author：sky on 2019/4/23 15:35.
 * Email：xcode126@126.com
 * Desc：网络
 */

public class NetFragment extends WrapperFragment {

//    @BindView(R.id.mNestedRefreshLayout)
//    NestedRefreshLayout mNestedRefreshLayout;
//    @BindView(R.id.mRecyclerView)
//    RecyclerView mRecyclerView;
//
//    private NetListAdapter mAdapter;

    public static NetFragment newInstance() {
        return new NetFragment();
    }

    @Override
    public int getViewLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
//        super.initView(savedInstanceState);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
//        mAdapter = new NetListAdapter();
//        mRecyclerView.setAdapter(mAdapter);
//        mNestedRefreshLayout.setOnRefreshListener(this);
    }


    @Override
    protected void loadData(Bundle savedInstanceState) {

    }


}
