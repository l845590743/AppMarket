package com.lzm.appmarket;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.lzm.appmarket.fragment.CategoryFragment;
import com.lzm.appmarket.fragment.HomeFragment;
import com.lzm.appmarket.fragment.HotFragment;
import com.lzm.appmarket.fragment.RecommendFragment;
import com.lzm.appmarket.fragment.SubjectFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private PagerSlidingTab mPagerSlidingTab;
    private ViewPager mViewpager;
    private ArrayList<pagerInfo> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setActionBar();

        prepareData();

        PagerAdapter pagerAdapter = new pagerAdapter(getSupportFragmentManager(), mList);
        mViewpager.setAdapter(pagerAdapter);
        //4.绑定ViewPager和PagerSlidingTab
        mPagerSlidingTab.setViewPager(mViewpager);
    }

    private void setActionBar() {
        ActionBar actionBar = getSupportActionBar();
        //启用home按钮
        actionBar.setDisplayShowHomeEnabled(true);
        //显示出来home按钮
        actionBar.setDisplayHomeAsUpEnabled(true);

        //设置汉堡包按钮
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, 0, 0);
        mActionBarDrawerToggle.syncState();
        //给汉堡包按钮添加旋转动画
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
    }

    private void prepareData() {
        mList = new ArrayList<>();
        String[] stringArray = Utils.getStringArray(R.array.tab_names);
        mList.add(new pagerInfo(stringArray[0],new HomeFragment()));
        mList.add(new pagerInfo(stringArray[1],new CategoryFragment()));
        mList.add(new pagerInfo(stringArray[2],new RecommendFragment()));
        mList.add(new pagerInfo(stringArray[3],new HotFragment()));
        mList.add(new pagerInfo(stringArray[4],new SubjectFragment()));
    }

    private void initView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mPagerSlidingTab = (PagerSlidingTab) findViewById(R.id.pager_slidingTab);
        mViewpager = (ViewPager) findViewById(R.id.view_pager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // 点击打开 点击关闭
        mActionBarDrawerToggle.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }
}
