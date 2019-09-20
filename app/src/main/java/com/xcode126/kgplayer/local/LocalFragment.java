package com.xcode126.kgplayer.local;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xcode126.kgplayer.R;
import com.xcode126.kgplayer.base.WrapperFragment;
import com.xcode126.kgplayer.local.adapter.LocalAdapter;
import com.xcode126.kgplayer.local.model.MediaItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Author：sky on 2019/4/23 11:58.
 * Email：xcode126@126.com
 * Desc：本地
 */
public class LocalFragment extends WrapperFragment {

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    private LocalAdapter localAdapter;
    private List<MediaItem> mMediaItems;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mMediaItems != null && mMediaItems.size() > 0) {
                localAdapter.setNewData(mMediaItems);
            } else {
                localAdapter.setEmptyView(getHelperView(mRecyclerView, R.layout.common_empty, null));
                localAdapter.notifyDataSetChanged();
            }
        }
    };

    public static LocalFragment newInstance() {
        return new LocalFragment();
    }

    @Override
    public int getViewLayout() {
        return R.layout.common_recycler;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(_mActivity));
        localAdapter = new LocalAdapter();
        mRecyclerView.setAdapter(localAdapter);
        localAdapter.setOnItemClickListener((adapter, view, position) -> {

        });
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        getDataFromLocal();
    }

    /**
     * 从本地的sdcard得到数据
     * //1.遍历sdcard,后缀名
     * //2.从内容提供者里面获取视频
     * //3.如果是6.0的系统，动态获取读取sdcard的权限
     */
    private void getDataFromLocal() {
        new Thread(() -> {
//                showLoadingDialog();
            mMediaItems = new ArrayList<>();
            ContentResolver resolver = _mActivity.getContentResolver();
            Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            //具体需要获取的参数
            String[] objs = {
                    MediaStore.Video.Media.DISPLAY_NAME,//视频文件在sdcard的名称
                    MediaStore.Video.Media.DURATION,//视频总时长
                    MediaStore.Video.Media.SIZE,//视频的文件大小
                    MediaStore.Video.Media.DATA,//视频的绝对地址
                    MediaStore.Video.Media.ARTIST,//歌曲的演唱者
            };

            //访问内部信息
            Cursor cursor = resolver.query(uri, objs, null, null, null);

            while (cursor.moveToNext()) {

                MediaItem mediaItem = new MediaItem();

                String name = cursor.getString(0);//视频的名称
                mediaItem.setName(name);

                long duration = cursor.getLong(1);//视频的时长
                mediaItem.setDuration(duration);

                long size = cursor.getLong(2);//视频的文件大小
                mediaItem.setSize(size);

                String data = cursor.getString(3);//视频的播放地址
                mediaItem.setData(data);

                String artist = cursor.getString(4);//艺术家
                mediaItem.setArtist(artist);

                mMediaItems.add(mediaItem);
            }
            cursor.close();
//                onStopLoading();
            mHandler.sendEmptyMessage(0);
        }).start();
    }
}
