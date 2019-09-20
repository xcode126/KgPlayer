package com.xcode126.kgplayer.base.autolayout.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import com.xcode126.kgplayer.base.autolayout.AutoLayoutInfo;
import com.xcode126.kgplayer.base.autolayout.utils.AutoLayoutHelper;
import com.xcode126.kgplayer.base.autolayout.utils.AutoUtils;

import java.lang.reflect.Field;


/**
 * Created by hupei on 2015/12/28 20:33.
 */
public class AutoToolbar extends Toolbar {
    private static final int NO_VALID = -1;
    private int mTextSize;
    private int mSubTextSize;
    private final AutoLayoutHelper mHelper = new AutoLayoutHelper(this);

    public AutoToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AutoToolbar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoToolbar(Context context) {
        this(context, null);
    }


    private void setUpTitleTextSize() {
        CharSequence title = getTitle();
        if (!TextUtils.isEmpty(title) && mTextSize != NO_VALID)
            setUpTitleTextSize("mTitleTextView", mTextSize);
        CharSequence subtitle = getSubtitle();
        if (!TextUtils.isEmpty(subtitle) && mSubTextSize != NO_VALID)
            setUpTitleTextSize("mSubtitleTextView", mSubTextSize);
    }

    private void setUpTitleTextSize(String name, int val) {
        try {
            //反射 Toolbar 的 TextView
            Field f = getClass().getSuperclass().getDeclaredField(name);
            f.setAccessible(true);
            TextView textView = (TextView) f.get(this);
            if (textView != null) {
                int autoTextSize = AutoUtils.getPercentHeightSize(val);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, autoTextSize);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!this.isInEditMode()) {
            setUpTitleTextSize();
            this.mHelper.adjustChildren();
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(this.getContext(), attrs);
    }

    public static class LayoutParams extends Toolbar.LayoutParams implements AutoLayoutHelper.AutoLayoutParams {
        private AutoLayoutInfo mDimenLayoutInfo;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            this.mDimenLayoutInfo = AutoLayoutHelper.getAutoLayoutInfo(c, attrs);
        }

        @Override
        public AutoLayoutInfo getAutoLayoutInfo() {
            return this.mDimenLayoutInfo;
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams source) {
            super(source);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams source) {
            super(source);
        }
    }
}
