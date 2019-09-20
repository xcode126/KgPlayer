package com.xcode126.kgplayer.base.widget;

import net.lucode.hackware.magicindicator.FragmentContainerHelper;
import net.lucode.hackware.magicindicator.MagicIndicator;

/**
 * Auther:  winds
 * Data:    2018/3/3
 * Version: 1.0
 * Desc:    指示器的帮助类  可获得当前指示器的position
 */


public class IndicatorHelper extends FragmentContainerHelper {
    private int selectedIndex;

    public IndicatorHelper(MagicIndicator magicIndicator) {
        super(magicIndicator);
    }

    @Override
    public void handlePageSelected(int selectedIndex) {
        super.handlePageSelected(selectedIndex);
        this.selectedIndex = selectedIndex;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }
}
