package com.xcode126.kgplayer;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：sky
 * 邮箱：xcode126@126.com
 * QQ号：1397028339
 * 公众号：走近程序员
 * 作用：影音播放器
 */
public class MainActivity extends ComSuperActivity {

    private boolean isExit = false;//是否已经退出

    private RadioGroup main_rg;
    private List<BaseFragment> mBaseFragment;
    /**
     * 选中的Fragment的对应的位置
     */
    private int position;

    /**
     * 上次切换的Fragment
     */
    private Fragment mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化View
        initView();
        //初始化Fragment
        initFragment();
        //设置RadioGroup的监听
        setListener();
    }

    /**
     * 初始化View
     */
    private void initView() {
        setContentView(R.layout.activity_main);
        main_rg = (RadioGroup) findViewById(R.id.main_rg);
    }

    /**
     * 初始化几个tab的碎片
     */
    private void initFragment() {
        mBaseFragment = new ArrayList<>();
        mBaseFragment.add(new FragmentTab01());
        mBaseFragment.add(new FragmentTab02());
        mBaseFragment.add(new FragmentTab03());
        mBaseFragment.add(new FragmentTab04());
    }

    /**
     * 设置监听
     */
    private void setListener() {
        main_rg.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        //设置默认选中第一个Tab
        main_rg.check(R.id.main_rb1);
    }

    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.main_rb1:
                    position = 0;
                    break;
                case R.id.main_rb2:
                    position = 1;
                    break;
                case R.id.main_rb3:
                    position = 2;
                    break;
                case R.id.main_rb4:
                    position = 3;
                    break;
                default:
                    position = 0;
                    break;
            }
            //根据位置得到对应的Fragment
            BaseFragment to = getFragment();
            //替换
            switchFrament(mContent, to);
        }
    }

    /**
     * @param from 刚显示的Fragment,马上就要被隐藏了
     * @param to   马上要切换到的Fragment，一会要显示
     */
    private void switchFrament(Fragment from, Fragment to) {
        if (from != to) {
            mContent = to;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            //才切换
            //判断有没有被添加
            if (!to.isAdded()) {
                //to没有被添加
                //from隐藏
                if (from != null) {
                    ft.hide(from);
                }
                //添加to
                if (to != null) {
                    ft.add(R.id.main_frame_layout, to).commit();
                }
            } else {
                //to已经被添加
                // from隐藏
                if (from != null) {
                    ft.hide(from);
                }
                //显示to
                if (to != null) {
                    ft.show(to).commit();
                }
            }
        }

    }
    //    private void switchFrament(BaseFragment fragment) {
//        //1.得到FragmentManger
//        FragmentManager fm = getSupportFragmentManager();
//        //2.开启事务
//        FragmentTransaction transaction = fm.beginTransaction();
//        //3.替换
//        transaction.replace(R.id.fl_content, fragment);
//        //4.提交事务
//        transaction.commit();
//    }

    /**
     * 根据位置得到对应的Fragment
     *
     * @return
     */
    private BaseFragment getFragment() {
        BaseFragment fragment = mBaseFragment.get(position);
        return fragment;
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
