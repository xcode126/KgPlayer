package com.xcode126.kgplayer.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * Author：sky on 2019/9/20 14:05.
 * Email：xcode126@126.com
 * Desc：图片加载工具类
 */

public class ImageManager {

    private static ImageManager instance;

    private ImageManager() {
    }

    public static ImageManager getDefault() {
        if (instance == null) {
            synchronized (ImageManager.class) {
                instance = new ImageManager();
            }
            return instance;
        }
        return instance;
    }

    /**
     * 加载指定URL的图片并设置到targetView
     *
     * @param context
     * @param obj
     * @param targetView
     */
    public static void load(Context context, Object obj, final ImageView targetView) {
        Glide.with(context).load(obj).into(targetView);
    }

    public static void load(Context context, Object obj, int place, final ImageView targetView) {
        Glide.with(context).load(obj).placeholder(place).into(targetView);
    }

    public static void load(Context context, Object obj, int place, BitmapTransformation transformation, final ImageView targetView) {
        Glide.with(context).load(obj).placeholder(place).bitmapTransform(transformation).into(targetView);
    }

    public static void load(Context context, Object obj, int place, int error, BitmapTransformation transformation, final ImageView targetView) {
        Glide.with(context).load(obj).placeholder(place).error(error).bitmapTransform(transformation).into(targetView);
    }
}
