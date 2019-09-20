package com.xcode126.kgplayer;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xcode126.kgplayer.base.WrapperActivity;
import com.xcode126.kgplayer.base.WrapperFragment;
import com.xcode126.kgplayer.base.adapter.TabAdapter;
import com.xcode126.kgplayer.base.widget.CompatMagicIndicator;
import com.xcode126.kgplayer.base.widget.OnSimplePagerTitleChangeListener;
import com.xcode126.kgplayer.base.widget.TitleView;
import com.xcode126.kgplayer.home.HomeFragment;
import com.xcode126.kgplayer.local.LocalFragment;
import com.xcode126.kgplayer.me.MeFragment;
import com.xcode126.kgplayer.net.NetFragment;

import net.lucode.hackware.magicindicator.abs.IPagerNavigator;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者：sky
 * 邮箱：xcode126@126.com
 * QQ号：1397028339
 * 公众号：程序教科书
 * 作用：影音播放器
 */
public class MainActivity extends WrapperActivity {

    @BindView(R.id.mViewPager)
    ViewPager pager;
    @BindView(R.id.indicator)
    CompatMagicIndicator indicator;

    private TabAdapter adapter;

    public static Intent getIntent(Context mContext) {
        return new Intent(mContext, MainActivity.class);
    }

    @Override
    protected int getViewLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState, TitleView titleView, Intent intent) {
        initTab();
        setBadgeText(3, 0);
    }

    @Override
    protected void loadData(Bundle savedInstanceState, Intent intent) {

    }

    @Override
    protected boolean isUseTitle() {
        return false;
    }

    /**
     * 初始化tab
     */
    private void initTab() {
        List<WrapperFragment> list = getFragments();
        adapter = new TabAdapter(getSupportFragmentManager(), list);
        pager.setAdapter(adapter);
        pager.setOffscreenPageLimit(list.size());
        pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (indicator != null && indicator.getIndicatorHelper() != null) {
                    indicator.getIndicatorHelper().handlePageSelected(position);
                }
                WrapperFragment fragment = adapter.getCurrentFragment();
                if (fragment != null && fragment.getView() != null) {
                    fragment.getView().requestLayout();
                }
            }
        });
        initIndicator();
    }

    /**
     * 获取tab页面
     *
     * @return
     */
    private List<WrapperFragment> getFragments() {
        List<WrapperFragment> list = new ArrayList<>();
        list.add(HomeFragment.newInstance());
        list.add(LocalFragment.newInstance());
        list.add(NetFragment.newInstance());
        list.add(MeFragment.newInstance());
        return list;
    }

    /**
     * 设置指示器
     */
    private void initIndicator() {
        final String[] titles = {"首页", "本地", "网络", "我的"};
        final int[] normal = {R.drawable.ic_tab_video, R.drawable.ic_tab_audio, R.drawable.ic_tab_netvideo, R.drawable.ic_tab_netaudio};
        final int[] selected = {R.drawable.ic_tab_video_press, R.drawable.ic_tab_audio_press, R.drawable.ic_tab_netvideo_press, R.drawable.ic_tab_netaudio_press};
        CommonNavigator navigator = new CommonNavigator(this);
        navigator.setAdjustMode(true);
        navigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return titles.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int i) {
                View view = LayoutInflater.from(context).inflate(R.layout.view_normal_tab, null);
                final ImageView image = view.findViewById(R.id.image);
                final TextView txt = view.findViewById(R.id.txt);
                txt.setText(titles[i]);
                image.setImageResource(normal[i]);
                CommonPagerTitleView container = new CommonPagerTitleView(context);
                container.setContentView(view);
                container.setOnPagerTitleChangeListener(new OnSimplePagerTitleChangeListener() {
                    @Override
                    public void onSelected(int index, int totalCount) {
                        super.onSelected(index, totalCount);
                        txt.setTextColor(Color.parseColor("#222222"));
                        image.setImageResource(selected[i]);
                    }

                    @Override
                    public void onDeselected(int index, int totalCount) {
                        super.onDeselected(index, totalCount);
                        txt.setTextColor(Color.parseColor("#666666"));
                        image.setImageResource(normal[i]);
                    }

                    @Override
                    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
                        super.onEnter(index, totalCount, enterPercent, leftToRight);
                        image.setScaleX(0.9f + (1.1f - 0.9f) * enterPercent);
                        image.setScaleY(0.9f + (1.1f - 0.9f) * enterPercent);
                    }

                    @Override
                    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
                        super.onLeave(index, totalCount, leavePercent, leftToRight);
                        image.setScaleX(1.1f + (0.9f - 1.1f) * leavePercent);
                        image.setScaleY(1.1f + (0.9f - 1.1f) * leavePercent);
                    }

                });
                container.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (indicator.getIndicatorHelper().getSelectedIndex() != i) {
//                            if (i == 4 && !WrapperApplication.isLogin()) {
//                                startActivity(LoginActivity.getIntent(mActivity, true));
//                            } else {
                            onTabClick(i);
//                            }
                        } else {//已被选中 重复点击
                            onTabReClick(i);
                        }
                    }
                });
                return container;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                return null;
            }
        });
        indicator.setNavigator(navigator);
        LinearLayout container = navigator.getTitleContainer();
        container.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        container.setDividerPadding(UIUtil.dip2px(this, 15));
        container.setDividerDrawable(getResources().getDrawable(R.drawable.shape_tab_divider));

        setBadgeDot(0, false);
        setBadgeText(1, 0);
        setBadgeText(2, 0);
    }

    /**
     * 当tab被点击回调
     *
     * @param i
     */
    private void onTabClick(int i) {
        if (!beforeTabSelected(i)) {
            if (pager != null && pager.getAdapter() != null && i >= 0 &&
                    i < pager.getAdapter().getCount()) {
                if (pager.getCurrentItem() != i) { //当前没被选中
                    onTabSelected(i);
                } else { //已被选中 重复点击
                    onTabReSelected(i);
                }
            }
        }
    }

    private void onTabReClick(int i) {
        onTabReSelected(i);
    }

    /**
     * tab被选中前回调 可用来判断是否登录
     *
     * @param i tab的位置
     * @return
     */
    private boolean beforeTabSelected(int i) {
        return false;
    }

    /**
     * 当tab被选中后回调
     *
     * @param i tab的位置
     */
    private void onTabSelected(int i) {
        pager.setCurrentItem(i, false);
    }

    /**
     * 当tab已被选中再次被选中后回调
     *
     * @param i tab的位置
     */
    private void onTabReSelected(int i) {

    }

    /**
     * 设置角标文字
     *
     * @param index
     * @param num
     */
    private void setBadgeText(int index, int num) {

        if (indicator != null) {
            IPagerNavigator navigator = indicator.getNavigator();
            if (navigator != null && navigator instanceof CommonNavigator) {
                IPagerTitleView iPagerTitleView = ((CommonNavigator) navigator).getPagerTitleView(index);
                if (iPagerTitleView != null && iPagerTitleView instanceof CommonPagerTitleView) {
                    View view = ((CommonPagerTitleView) iPagerTitleView).findViewById(R.id.msg);
                    if (view != null && view instanceof TextView) {
                        if (num > 0) {
                            view.setVisibility(View.VISIBLE);
                            ((TextView) view).setText(String.valueOf(num > 99 ? 99 : num));
                        } else {
                            view.setVisibility(View.GONE);
                        }
                    }
                }
            }
        }
    }

    /**
     * 设置角标红点
     *
     * @param index
     * @param visible
     */
    private void setBadgeDot(int index, boolean visible) {
        if (indicator != null) {
            IPagerNavigator navigator = indicator.getNavigator();
            if (navigator != null && navigator instanceof CommonNavigator) {
                IPagerTitleView iPagerTitleView = ((CommonNavigator) navigator).getPagerTitleView(index);
                if (iPagerTitleView != null && iPagerTitleView instanceof CommonPagerTitleView) {
                    View view = ((CommonPagerTitleView) iPagerTitleView).findViewById(R.id.dot);
                    if (view != null) {
                        view.setVisibility(visible ? View.VISIBLE : View.GONE);
                    }
                }
            }
        }

    }

}
