//package com.xcode126.kgplayer.me;
//
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//
//import com.xcode126.kgplayer.R;
//import com.xcode126.kgplayer.base.WrapperActivity;
//import com.xcode126.kgplayer.base.widget.TitleView;
//
//
///**
// * Auther:  winds
// * Data:    2017/6/11
// * Desc:    登录
// */
//
//public class LoginActivity extends WrapperActivity {
//
//    boolean retry;  //是否为再次登录
//    boolean enable = true; //是否开启选择站点功能
//
//    public static Intent getIntent(Context context) {
//        return new Intent(context, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//    }
//
//    /**
//     * @param context
//     * @param context
//     * @param retry   是否是重新登录
//     * @return
//     */
//    public static Intent getIntent(Context context, boolean retry) {
//        return new Intent(context, LoginActivity.class).putExtra("initiative", true).putExtra("retry", retry);
//    }
//
//    @Override
//    public int getViewLayout() {
//        return R.layout.activity_login;
//    }
//
//    @Override
//    protected void initView(Bundle savedInstanceState, TitleView titleView, Intent intent) {
//        if (!isTaskRoot()) {
//            finish();
//            return;
//        }
//        retry = intent.getBooleanExtra("retry", false);
//        setInterceptable(false);
//        loadView(intent.getBooleanExtra("initiative", false));
//    }
//
//
//    @Override
//    protected void loadData(Bundle savedInstanceState, Intent intent) {
//
//    }
//
//    @Override
//    public void showContentView(String url, BaseVo dataVo) {
//
//    }
//
//    @Override
//    protected boolean isUseTitle() {
//        return false;
//    }
//
//    /**
//     * 进入对应页面   请勿改动此逻辑 如无需站点选择功能 请主动设置enable为false或者打正式包
//     *
//     * @param initiative
//     */
//    private void loadView(boolean initiative) {
//        if (initiative) { //主动跳转
//            loadLoginPage();
//        } else { //自动打开  比如点击icon 安装
//            if (enable) { //是否开启站点选择功能
//                if (BuildConfig.FLAVOR.equals("beta")) { //是否为测试版
//                    loadChoosePage(); //仅在主动开启站点选择功能 且为测试版的情况下 加载站点选择页面
//                    return;
//                }
//            }
//            loadLoginPage();
//        }
//    }
//
//    private void loadChoosePage() {
//        loadRootFragment(R.id.container, TestingFragment.newInstance(), false, false);
//    }
//
//    public void loadLoginPage() {
//        loadRootFragment(R.id.container, LoginFragment.newInstance(), false, false);
//    }
//}
