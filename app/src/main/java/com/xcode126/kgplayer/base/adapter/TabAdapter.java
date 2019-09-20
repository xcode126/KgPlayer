package com.xcode126.kgplayer.base.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;

import com.xcode126.kgplayer.base.WrapperFragment;

import java.util.List;


/**
 * Author：sky on 2019/9/20 10:30.
 * Email：xcode126@126.com
 * Desc：基本adapter
 */

public class TabAdapter extends FragmentPagerAdapter {
    private int position;

    private List<WrapperFragment> list;

    public TabAdapter(FragmentManager fm, List<WrapperFragment> list) {
        super(fm);
        this.list = list;
    }

    public WrapperFragment getCurrentFragment() {
        return list.get(position);
    }

    public int getCurrentPosition() {
        return position;
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        this.position = position;
    }


    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}
