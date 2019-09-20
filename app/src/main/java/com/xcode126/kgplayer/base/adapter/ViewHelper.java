package com.xcode126.kgplayer.base.adapter;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import com.xcode126.kgplayer.base.autolayout.utils.AutoUtils;


/**
 * Auther:  winds
 * Data:    2017/8/25
 * Version: 1.0
 * Desc:
 */
public class ViewHelper {
    private SparseArray<View> views;
    public View itemView;

    public ViewHelper(final View itemView) {
        this.itemView = itemView;
        this.views = new SparseArray<View>();
        AutoUtils.auto(itemView);
    }

    /**
     * 获取传入的跟View
     *
     * @return
     */
    public View getItemView() {
        return itemView;
    }

    /**
     * 根据viewId获取控件
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }

        return (T) view;
    }
    /**
     * set the text of a TextView.
     *
     * @param viewId
     * @param value
     * @return
     */
    public ViewHelper setText(int viewId, CharSequence value) {
        TextView view = getView(viewId);
        view.setText(value);
        return this;
    }

    /**
     * set the hint of a TextView.
     *
     * @param viewId
     * @param value
     * @return
     */
    public ViewHelper setHint(int viewId, CharSequence value) {
        TextView view = getView(viewId);
        view.setHint(value);
        return this;
    }

    /**
     * set the hint of a TextView.
     *
     * @param viewId
     * @param strId
     * @return
     */
    public ViewHelper setHint(int viewId, @StringRes int strId) {
        TextView view = getView(viewId);
        view.setHint(strId);
        return this;
    }

    /**
     * set the text of a TextView.
     *
     * @param viewId
     * @param strId
     * @return
     */
    public ViewHelper setText(int viewId, @StringRes int strId) {
        TextView view = getView(viewId);
        view.setText(strId);
        return this;
    }

    /**
     * 将文本域设置可见再显示文字
     *
     * @param viewId
     * @param msg
     * @return
     */
    public ViewHelper setTextVisible(int viewId, CharSequence msg) {
        TextView view = getView(viewId);
        view.setVisibility(View.VISIBLE);
        view.setText(msg);
        return this;
    }

    /**
     * 显示带删除线字体
     *
     * @param viewId
     * @param text
     */
    public ViewHelper setTextDelete(int viewId, CharSequence text) {
        TextView view = getView(viewId);
        view.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);//删除线
        view.setText(text);
        return this;
    }

    /**
     * set the image of an ImageView from a resource id.
     *
     * @param viewId
     * @param imageResId
     * @return
     */
    public ViewHelper setImageResource(int viewId, @DrawableRes int imageResId) {
        ImageView view = getView(viewId);
        view.setImageResource(imageResId);
        return this;
    }

    /**
     * set background color of a view.
     *
     * @param viewId
     * @param color
     * @return
     */
    public ViewHelper setBackgroundColor(int viewId, int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    /**
     * set background drawable of a view.
     *
     * @param viewId
     * @param drawable
     * @return
     */
    public ViewHelper setBackgroundDrawable(int viewId, Drawable drawable) {
        View view = getView(viewId);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
        return this;
    }

    /**
     * set background of a view.
     *
     * @param viewId
     * @param backgroundRes
     * @return
     */
    public ViewHelper setBackgroundRes(int viewId, @DrawableRes int backgroundRes) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    /**
     * Will set text color of a TextView.
     *
     * @param viewId    The view id.
     * @param textColor The text color (not a resource id).
     * @return The BaseViewHolder for chaining.
     */
    public ViewHelper setTextColor(int viewId, int textColor) {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    /**
     * Will set the image of an ImageView from a drawable.
     *
     * @param viewId
     * @param drawable
     * @return
     */
    public ViewHelper setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    /**
     * Add an action to set the image of an image view. Can be called multiple times.
     *
     * @param viewId
     * @param bitmap
     * @return
     */
    public ViewHelper setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    /**
     * dd an action to set the alpha of a view. Can be called multiple times.
     *
     * @param viewId
     * @param value  Alpha between 0-1.
     * @return
     */
    public ViewHelper setAlpha(int viewId, float value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getView(viewId).setAlpha(value);
        } else {
            // Pre-honeycomb hack to set Alpha value
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            getView(viewId).startAnimation(alpha);
        }
        return this;
    }

    /**
     * Set a view visibility to VISIBLE (true) or GONE (false).
     *
     * @param viewId  The view id.
     * @param visible True for VISIBLE, false for GONE.
     * @return The BaseViewHolder for chaining.
     */
    public ViewHelper setVisible(int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    public ViewHelper setViewInVisible(int viewId) {
        View view = getView(viewId);
        view.setVisibility(View.INVISIBLE);
        return this;
    }


    public ViewHelper setViewVisible(int viewId) {
        View view = getView(viewId);
        view.setVisibility(View.VISIBLE);
        return this;
    }


    public ViewHelper setViewGone(int viewId) {
        View view = getView(viewId);
        view.setVisibility(View.GONE);
        return this;
    }

    /**
     * Add links into a TextView.
     *
     * @param viewId The id of the TextView to linkify.
     * @return The BaseViewHolder for chaining.
     */
    public ViewHelper linkify(int viewId) {
        TextView view = getView(viewId);
        Linkify.addLinks(view, Linkify.ALL);
        return this;
    }

    /**
     * Apply the typeface to the given viewId, and enable subpixel rendering.
     *
     * @param viewId
     * @param typeface
     * @return
     */
    public ViewHelper setTypeface(int viewId, Typeface typeface) {
        TextView view = getView(viewId);
        view.setTypeface(typeface);
        view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        return this;
    }

    /**
     * Apply the typeface to all the given viewIds, and enable subpixel rendering.
     *
     * @param typeface
     * @param viewIds
     * @return
     */
    public ViewHelper setTypeface(Typeface typeface, int... viewIds) {
        for (int viewId : viewIds) {
            TextView view = getView(viewId);
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }
        return this;
    }

    /**
     * Sets the progress of a ProgressBar.
     *
     * @param viewId   The view id.
     * @param progress The progress.
     * @return The BaseViewHolder for chaining.
     */
    public ViewHelper setProgress(int viewId, int progress) {
        ProgressBar view = getView(viewId);
        view.setProgress(progress);
        return this;
    }

    /**
     * Sets the progress and max of a ProgressBar.
     *
     * @param viewId   The view id.
     * @param progress The progress.
     * @param max      The max value of a ProgressBar.
     * @return The BaseViewHolder for chaining.
     */
    public ViewHelper setProgress(int viewId, int progress, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    /**
     * Sets the rating (the number of stars filled) of a RatingBar.
     *
     * @param viewId The view id.
     * @param rating The rating.
     * @return The BaseViewHolder for chaining.
     */
    public ViewHelper setRating(int viewId, float rating) {
        RatingBar view = getView(viewId);
        view.setRating(rating);
        return this;
    }

    /**
     * Sets the rating (the number of stars filled) and max of a RatingBar.
     *
     * @param viewId The view id.
     * @param rating The rating.
     * @param max    The range of the RatingBar to 0...max.
     * @return The BaseViewHolder for chaining.
     */
    public ViewHelper setRating(int viewId, float rating, int max) {
        RatingBar view = getView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

    /**
     * Sets the on click listener of the view.
     *
     * @param viewId   The view id.
     * @param listener The on click listener;
     * @return The BaseViewHolder for chaining.
     */
    public ViewHelper setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    /**
     * 给多个控件设置点击事件
     * @param listener
     * @param viewId
     * @return
     */
    public ViewHelper setOnClickListener(View.OnClickListener listener, int... viewId) {
        if (viewId != null && viewId.length > 0) {
            for (int id : viewId) {
                setOnClickListener(id, listener);
            }
        }
        return this;
    }

    /**
     * Sets the on touch listener of the view.
     *
     * @param viewId   The view id.
     * @param listener The on touch listener;
     * @return The BaseViewHolder for chaining.
     */
    public ViewHelper setOnTouchListener(int viewId, View.OnTouchListener listener) {
        View view = getView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

    /**
     * Sets the on long click listener of the view.
     *
     * @param viewId   The view id.
     * @param listener The on long click listener;
     * @return The BaseViewHolder for chaining.
     */
    public ViewHelper setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }

    /**
     * Sets the on checked change listener of the view.
     *
     * @param viewId   The view id.
     * @param listener The checked change listener of compound button.
     * @return The BaseViewHolder for chaining.
     */
    public ViewHelper setOnCheckedChangeListener(int viewId, CompoundButton.OnCheckedChangeListener listener) {
        CompoundButton view = getView(viewId);
        view.setOnCheckedChangeListener(listener);
        return this;
    }

    /**
     * Sets the tag of the view.
     *
     * @param viewId The view id.
     * @param tag    The tag;
     * @return The BaseViewHolder for chaining.
     */
    public ViewHelper setTag(int viewId, Object tag) {
        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

    /**
     * Sets the tag of the view.
     *
     * @param viewId The view id.
     * @param key    The key of tag;
     * @param tag    The tag;
     * @return The BaseViewHolder for chaining.
     */
    public ViewHelper setTag(int viewId, int key, Object tag) {
        View view = getView(viewId);
        view.setTag(key, tag);
        return this;
    }

    public Object getTag(int viewId) {
        View view = getView(viewId);
        return view.getTag();
    }


    public Object getTag(int viewId, int key) {
        View view = getView(viewId);
        return view.getTag(key);
    }

    /**
     * Sets the checked status of a checkable.
     *
     * @param viewId  The view id.
     * @param checked The checked status;
     * @return The BaseViewHolder for chaining.
     */
    public ViewHelper setChecked(int viewId, boolean checked) {
        View view = getView(viewId);
        // View unable cast to Checkable
        if (view instanceof CompoundButton) {
            ((CompoundButton) view).setChecked(checked);
        } else if (view instanceof CheckedTextView) {
            ((CheckedTextView) view).setChecked(checked);
        }
        return this;
    }

    /**
     * 将view设置成selected状态
     *
     * @param viewId
     * @param selected
     * @return
     */
    public ViewHelper setSelected(int viewId, boolean selected) {
        View view = getView(viewId);
        view.setSelected(selected);
        return this;
    }

}
