package com.xcode126.kgplayer.base.widget;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.xcode126.kgplayer.base.autolayout.utils.AutoUtils;



/**
 * Author：sky on 2019/4/23 15:02.
 * Email：xcode126@126.com
 * Desc：自定义BaseRecyclerHolder
 */


public class BaseRecyclerHolder extends BaseViewHolder {

    public BaseRecyclerHolder(View view) {
        super(view);
        AutoUtils.auto(view);
    }

    public BaseRecyclerHolder setVisibleText(int viewId, CharSequence msg) {
        TextView view = getView(viewId);
        view.setVisibility(View.VISIBLE);
        view.setText(msg);
        return this;
    }
}
