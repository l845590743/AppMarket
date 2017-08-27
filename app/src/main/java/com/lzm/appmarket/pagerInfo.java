package com.lzm.appmarket;

import android.support.v4.app.Fragment;

/**
 * Created by lzm on 2017/8/27.
 */
public class pagerInfo {
    public String mTitle;
    public Fragment mFragment;

    public pagerInfo(String title,Fragment fragment) {
        this.mTitle = title;
        this.mFragment = fragment;
    }
}
