package com.xcode126.kgplayer.base.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xcode126.kgplayer.R;

/**
 * Auther:  winds
 * Data:    2017/4/12
 * Desc:
 */

public class TitleView extends RelativeLayout {

    public TitleView(Context context) {
        this(context, null);
    }

    public TitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View.inflate(context, R.layout.view_title, this);
    }

    public void setTitle(String title) {
        setText(R.id.tv_title_center, title);
    }

    public <T extends View> T getView(int viewId) {
        return (T) findViewById(viewId);
    }

    public TitleView setText(TextView view, String text) {
        view.setVisibility(VISIBLE);
        view.setText(text);
        return this;
    }

    public TitleView setText(int viewId, String text) {
        View view = findViewById(viewId);
        if (view instanceof TextView) {
            return setText((TextView) view, text);
        }
        return this;
    }


    public TitleView setImageResource(int viewId, int resId) {
        View view = findViewById(viewId);
        if (view instanceof ImageView) {
            return setImageResource((ImageView) view, resId);
        }
        return this;
    }

    public TitleView setImageResource(ImageView view, int resId) {
        view.setVisibility(VISIBLE);
        view.setImageResource(resId);
        return this;
    }

    public boolean checkVisible(View view) {
        return view == null ? false : view.getVisibility() == VISIBLE ? true : false;
    }

    public TitleView setVisible(int viewId, boolean visible) {
        return setVisible(findViewById(viewId), visible);
    }

    public TitleView setVisible(View view, boolean visible) {
        view.setVisibility(visible ? VISIBLE : GONE);
        return this;
    }


    public TitleView setChildClickListener(View view, OnClickListener listener) {
        if (view != null) {
            view.setOnClickListener(listener);
        }
        return this;
    }

    public TitleView setChildClickListener(int viewId, OnClickListener listener) {
        return setChildClickListener(findViewById(viewId), listener);
    }
}
