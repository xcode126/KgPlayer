package com.xcode126.kgplayer.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.xcode126.kgplayer.R;
import com.xcode126.kgplayer.base.adapter.ViewHelper;
import com.xcode126.kgplayer.base.autolayout.AutoFrameLayout;
import com.xcode126.kgplayer.base.autolayout.AutoLinearLayout;
import com.xcode126.kgplayer.base.autolayout.AutoRelativeLayout;
import com.xcode126.kgplayer.base.autolayout.widget.AutoCardView;
import com.xcode126.kgplayer.base.autolayout.widget.AutoRadioGroup;
import com.xcode126.kgplayer.base.listener.OnViewHelper;
import com.xcode126.kgplayer.base.widget.TitleView;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;


/**
 * Author：sky on 2019/9/20 10:30.
 * Email：xcode126@126.com
 * Desc：基类,适配
 */

public abstract class WrapperActivity extends SupportActivity {

    private boolean focus = true;  //自动显示和隐藏输入法
    private long lastTime;
    private boolean interceptable;  //是否拦截快速点击事件

    protected LinearLayout root;
    protected TitleView titleView;
    protected FrameLayout container;
    protected Activity mActivity;
    protected View contentView;

    private static final String LAYOUT_LINEARLAYOUT = "LinearLayout";
    private static final String LAYOUT_FRAMELAYOUT = "FrameLayout";
    private static final String LAYOUT_RELATIVELAYOUT = "RelativeLayout";
    private static final String LAYOUT_RADIOGROUP = "RadioGroup";
    private static final String LAYOUT_CARDVIEW = "CardView";

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = null;
        if (name.equals(LAYOUT_FRAMELAYOUT)) {
            view = new AutoFrameLayout(context, attrs);
        } else if (name.equals(LAYOUT_LINEARLAYOUT)) {
            view = new AutoLinearLayout(context, attrs);
        } else if (name.equals(LAYOUT_RELATIVELAYOUT)) {
            view = new AutoRelativeLayout(context, attrs);
        } else if (name.equals(LAYOUT_RADIOGROUP)) {
            view = new AutoRadioGroup(context, attrs);
        } else if (name.equals(LAYOUT_CARDVIEW)) {
            view = new AutoCardView(context, attrs);
        }
        if (view != null) {
            return view;
        }
        return super.onCreateView(name, context, attrs);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeOnCreate();
        setContentView(R.layout.activity_wrapper);

        initView();
        initTitleView();
        initContentView();
        initView(savedInstanceState, titleView, getIntent());
        loadData(savedInstanceState, getIntent());
    }

    private void initView() {
        mActivity = this;
        root = findViewById(R.id.root);
        titleView = findViewById(R.id.mTitleView);
        container = findViewById(R.id.container);
    }

    private void initTitleView() {
        if (!isUseTitle()) {
            titleView.setVisibility(View.GONE);
            AutoLinearLayout.LayoutParams layoutParams = (AutoLinearLayout.LayoutParams) container.getLayoutParams();
            layoutParams.topMargin = 0;
        } else {
            titleView.setChildClickListener(R.id.iv_title_left, onBackClick());
        }
    }

    /**
     * 标题返回按钮 如需重写 覆盖此方法即可
     *
     * @return
     */
    protected View.OnClickListener onBackClick() {
        return v -> onBackPressedSupport();
    }

    protected void initContentView() {
        if (getViewLayout() != 0 && getViewLayout() != -1) {
            contentView = View.inflate(this, getViewLayout(), null);
            addContentView(contentView);
        }

        ButterKnife.bind(this);
    }

    protected View getContentView() {
        return contentView;
    }

    protected void addContentView(View view) {
        container.addView(view);
    }

    protected LinearLayout getRootView() {
        return root;
    }

    protected boolean isUseTitle() {
        return true;
    }

    protected void beforeOnCreate() {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);   //去除默认actionbar
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//统一管理竖屏
    }

    protected View getContainerView() {
        return container;
    }

    protected TitleView getTitleView() {
        return titleView;
    }

    /**
     * 返回值为0或者-1时不添加
     *
     * @return
     */
    protected abstract int getViewLayout();

    protected abstract void initView(Bundle savedInstanceState, TitleView titleView, Intent intent);

    /**
     * 从intent中获取请求参数，初始化vo对象，并发送请求
     *
     * @param savedInstanceState
     * @param intent
     */
    protected abstract void loadData(Bundle savedInstanceState, Intent intent);

    /**
     * 设置是否拦截快速点击
     *
     * @param interceptable 默认拦截   设置不拦截请设置为 false
     */
    protected void setInterceptable(boolean interceptable) {
        this.interceptable = !interceptable;
    }

    /**
     * 判断是否是快速点击
     *
     * @return
     */
    public boolean isInvalidClick() {
        long time = System.currentTimeMillis();
        long duration = time - lastTime;
        if (duration < 400) {
            return true;
        } else {
            lastTime = time;
            return false;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //快速点击拦截
        if (!interceptable && ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (isInvalidClick()) {
                return true;
            }
        }
        //键盘拦截判断
        if (focus) {
            if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                View v = getCurrentFocus();
                if (isShouldHideInput(v, ev)) {
                    hideKeyboard(v);
                }
                return super.dispatchTouchEvent(ev);
            }
            // 其他组件响应点击事件
            if (getWindow().superDispatchTouchEvent(ev)) {
                return true;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    public void hideKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    /**
     * 判断键盘是否应该隐藏
     * 点击除EditText的区域隐藏
     *
     * @param v
     * @param event
     * @return
     */
    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                ((EditText) v).setCursorVisible(true);
                return false;
            } else {
                ((EditText) v).setCursorVisible(false);  //隐藏光标
                return true;
            }
        }
        return false;
    }

    /**
     * 设置键盘显否
     *
     * @param v
     * @param visible
     */
    protected void setKeyboardVisible(View v, boolean visible) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            if (visible) {
                imm.showSoftInput(v, 0);
            } else {
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        }
    }

    /**
     * 实例化对应layoutId的view同时生成ViewHelper帮助类
     *
     * @param group    可为null
     * @param layoutId
     * @param listener
     * @return
     */
    protected View getHelperView(ViewGroup group, int layoutId, OnViewHelper listener) {
        ViewHelper helper = new ViewHelper(getLayoutInflater().inflate(layoutId, group == null ? null : group instanceof RecyclerView ? (ViewGroup) group.getParent() : group, false));
        if (listener != null) {
            listener.help(helper);
        }
        return helper.getItemView();
    }

    /**
     * 设置自动隐藏输入法
     *
     * @param focus 默认 true 自动隐藏
     */
    protected void setAutoFocus(boolean focus) {
        this.focus = focus;
    }

    /**
     * 普通Toast提示
     *
     * @param msg
     */
    protected void showToast(String msg) {
        Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 图文样式的Toast提示
     *
     * @param msg
     * @param resId
     */
    protected void showToast(String msg, int resId) {
        Toast.makeText(mActivity, getString(resId), Toast.LENGTH_SHORT).show();
    }
}
