package com.xcode126.kgplayer.base.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IMeasurablePagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.badge.BadgeAnchor;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.badge.BadgeRule;

/**
 * Auther:  winds
 * Data:    2018/3/3
 * Version: 1.0
 * Desc:    可适配添加角标
 */


public class CompatBadgeTitleView extends FrameLayout implements IMeasurablePagerTitleView {
    private IPagerTitleView mInnerPagerTitleView;
    private View mBadgeView;
    private boolean mAutoCancelBadge = true;
    private BadgeRule mXBadgeRule;
    private BadgeRule mYBadgeRule;

    public CompatBadgeTitleView(Context context) {
        super(context);
    }

    public void onSelected(int index, int totalCount) {
        if (this.mInnerPagerTitleView != null) {
            this.mInnerPagerTitleView.onSelected(index, totalCount);
        }

        if (this.mAutoCancelBadge) {
            this.setBadgeView((View) null);
        }

    }

    public void onDeselected(int index, int totalCount) {
        if (this.mInnerPagerTitleView != null) {
            this.mInnerPagerTitleView.onDeselected(index, totalCount);
        }

    }

    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
        if (this.mInnerPagerTitleView != null) {
            this.mInnerPagerTitleView.onLeave(index, totalCount, leavePercent, leftToRight);
        }

    }

    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
        if (this.mInnerPagerTitleView != null) {
            this.mInnerPagerTitleView.onEnter(index, totalCount, enterPercent, leftToRight);
        }

    }

    public IPagerTitleView getInnerPagerTitleView() {
        return this.mInnerPagerTitleView;
    }

    public void setInnerPagerTitleView(IPagerTitleView innerPagerTitleView) {
        if (this.mInnerPagerTitleView != innerPagerTitleView) {
            this.mInnerPagerTitleView = innerPagerTitleView;
            this.removeAllViews();
            LayoutParams lp;
            if (this.mInnerPagerTitleView instanceof View) {
                lp = new LayoutParams(-1, -1);
                this.addView((View) this.mInnerPagerTitleView, lp);
            }

            if (this.mBadgeView != null) {
                if (this.mBadgeView.getLayoutParams() == null) {
                    lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, -2);
                    this.addView(this.mBadgeView, lp);
                } else {
                    this.addView(this.mBadgeView);
                }
            }

        }
    }

    public View getBadgeView() {
        return this.mBadgeView;
    }

    public void setBadgeView(View badgeView) {
        if (this.mBadgeView != badgeView) {
            this.mBadgeView = badgeView;
            this.removeAllViews();
            LayoutParams lp;
            if (this.mInnerPagerTitleView instanceof View) {
                lp = new LayoutParams(-1, -1);
                this.addView((View) this.mInnerPagerTitleView, lp);
            }

            if (this.mBadgeView != null) {
                if (this.mBadgeView.getLayoutParams() == null) {
                    lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, -2);
                    this.addView(this.mBadgeView, lp);
                } else {
                    this.addView(this.mBadgeView);
                }
            }

        }
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (this.mInnerPagerTitleView instanceof View && this.mBadgeView != null) {
            int[] position = new int[14];
            View v = (View) this.mInnerPagerTitleView;
            position[0] = v.getLeft();
            position[1] = v.getTop();
            position[2] = v.getRight();
            position[3] = v.getBottom();
            int y;
            if (this.mInnerPagerTitleView instanceof IMeasurablePagerTitleView) {
                IMeasurablePagerTitleView view = (IMeasurablePagerTitleView) this.mInnerPagerTitleView;
                position[4] = view.getContentLeft();
                position[5] = view.getContentTop();
                position[6] = view.getContentRight();
                position[7] = view.getContentBottom();
            } else {
                for (y = 4; y < 8; ++y) {
                    position[y] = position[y - 4];
                }
            }

            position[8] = v.getWidth() / 2;
            position[9] = v.getHeight() / 2;
            position[10] = position[4] / 2;
            position[11] = position[5] / 2;
            position[12] = position[6] + (position[2] - position[6]) / 2;
            position[13] = position[7] + (position[3] - position[7]) / 2;
            int offset;
            int newTop;
            if (this.mXBadgeRule != null) {
                y = position[this.mXBadgeRule.getAnchor().ordinal()];
                offset = this.mXBadgeRule.getOffset();
                newTop = y + offset;
                this.mBadgeView.offsetLeftAndRight(newTop - this.mBadgeView.getLeft());
            }

            if (this.mYBadgeRule != null) {
                y = position[this.mYBadgeRule.getAnchor().ordinal()];
                offset = this.mYBadgeRule.getOffset();
                newTop = y + offset;
                this.mBadgeView.offsetTopAndBottom(newTop - this.mBadgeView.getTop());
            }
        }

    }

    public int getContentLeft() {
        return this.mInnerPagerTitleView instanceof IMeasurablePagerTitleView ? this.getLeft() + ((IMeasurablePagerTitleView) this.mInnerPagerTitleView).getContentLeft() : this.getLeft();
    }

    public int getContentTop() {
        return this.mInnerPagerTitleView instanceof IMeasurablePagerTitleView ? ((IMeasurablePagerTitleView) this.mInnerPagerTitleView).getContentTop() : this.getTop();
    }

    public int getContentRight() {
        return this.mInnerPagerTitleView instanceof IMeasurablePagerTitleView ? this.getLeft() + ((IMeasurablePagerTitleView) this.mInnerPagerTitleView).getContentRight() : this.getRight();
    }

    public int getContentBottom() {
        return this.mInnerPagerTitleView instanceof IMeasurablePagerTitleView ? ((IMeasurablePagerTitleView) this.mInnerPagerTitleView).getContentBottom() : this.getBottom();
    }

    public BadgeRule getXBadgeRule() {
        return this.mXBadgeRule;
    }

    public void setXBadgeRule(BadgeRule badgeRule) {
        if (badgeRule != null) {
            BadgeAnchor anchor = badgeRule.getAnchor();
            if (anchor != BadgeAnchor.LEFT && anchor != BadgeAnchor.RIGHT && anchor != BadgeAnchor.CONTENT_LEFT && anchor != BadgeAnchor.CONTENT_RIGHT && anchor != BadgeAnchor.CENTER_X && anchor != BadgeAnchor.LEFT_EDGE_CENTER_X && anchor != BadgeAnchor.RIGHT_EDGE_CENTER_X) {
                throw new IllegalArgumentException("x badge rule is wrong.");
            }
        }

        this.mXBadgeRule = badgeRule;
    }

    public BadgeRule getYBadgeRule() {
        return this.mYBadgeRule;
    }

    public void setYBadgeRule(BadgeRule badgeRule) {
        if (badgeRule != null) {
            BadgeAnchor anchor = badgeRule.getAnchor();
            if (anchor != BadgeAnchor.TOP && anchor != BadgeAnchor.BOTTOM && anchor != BadgeAnchor.CONTENT_TOP && anchor != BadgeAnchor.CONTENT_BOTTOM && anchor != BadgeAnchor.CENTER_Y && anchor != BadgeAnchor.TOP_EDGE_CENTER_Y && anchor != BadgeAnchor.BOTTOM_EDGE_CENTER_Y) {
                throw new IllegalArgumentException("y badge rule is wrong.");
            }
        }

        this.mYBadgeRule = badgeRule;
    }

    public boolean isAutoCancelBadge() {
        return this.mAutoCancelBadge;
    }

    public void setAutoCancelBadge(boolean autoCancelBadge) {
        this.mAutoCancelBadge = autoCancelBadge;
    }
}
