package com.xcode126.kgplayer.local.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xcode126.kgplayer.R;
import com.xcode126.kgplayer.base.widget.BaseRecyclerHolder;
import com.xcode126.kgplayer.local.model.MediaItem;
import com.xcode126.kgplayer.util.DateUtils;

/**
 * Author：sky on 2019/4/23 15:02.
 * Email：xcode126@126.com
 * Desc：本地扫描adapter
 */

public class LocalAdapter extends BaseQuickAdapter<MediaItem,BaseRecyclerHolder> {

    public LocalAdapter() {
        super(R.layout.item_local);
    }

    @Override
    protected void convert(BaseRecyclerHolder helper, MediaItem item) {
//        helper.setImageManager(mContext,R.id.iv_image,item.get);
        helper.setText(R.id.tv_title,item.getName());
        helper.setText(R.id.tv_time, DateUtils.timeToString(item.getDuration()));
        helper.setText(R.id.tv_size,android.text.format.Formatter.formatFileSize(mContext, item.getSize()));

    }
}
