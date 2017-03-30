package com.xcode126.kgplayer.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;

/**
 * 分辨率转换工具
 */
public class DisplayUtil {

    public static int SYS_SCREEN_WIDTH;
    public static int SYS_SCREEN_HEIGHT;
    public static int CONTACT_GROUP_LABLE;

    /**
     * dip转px
     *
     * @param context
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * px转dip
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取屏幕宽度和高度，单位为px
     *
     * @param context
     * @return
     */
    public static Point getScreenMetrics(Context context) {
        int w_screen = getScreenWidth(context);
        int h_screen = getScreenHeight(context);
        return new Point(w_screen, h_screen);

    }

    /**
     * 获取屏幕长宽比
     *
     * @param context
     * @return
     */
    public static float getScreenRate(Context context) {
        Point P = getScreenMetrics(context);
        float H = P.y;
        float W = P.x;
        return (H / W);
    }

    /**
     * 屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;
        return w_screen;
    }

    /**
     * 屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int h_screen = dm.heightPixels;
        return h_screen;
    }

    /**
     * 获取当前屏幕的尺寸信息
     *
     * @param activity
     */
    public static void getSystemInfo(Activity activity) {
        SYS_SCREEN_WIDTH = getScreenWidth(activity);
        SYS_SCREEN_HEIGHT = getScreenHeight(activity);
        CONTACT_GROUP_LABLE = SYS_SCREEN_WIDTH / 5 * 3;
    }
//    /**
//     * 获取屏幕宽度
//     *
//     * @param context
//     * @return
//     */
//    public static int getScreenWidth(Context context) {
//        if (width == 0) {
//            WindowManager manager = (WindowManager) context
//                    .getSystemService(Context.WINDOW_SERVICE);
//            Display display = manager.getDefaultDisplay();
//            width = display.getWidth();
//        }
//        return width;
//    }
}
