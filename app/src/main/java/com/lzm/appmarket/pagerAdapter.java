package com.lzm.appmarket;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by lzm on 2017/8/27.
 */
public class pagerAdapter extends FragmentPagerAdapter {

    private ArrayList<pagerInfo> mList;

    public pagerAdapter(FragmentManager fm,ArrayList<pagerInfo> list) {
        super(fm);
        this.mList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position).mFragment;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mList.get(position).mTitle;
    }
}
