package com.lzm.appmarket.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lzm.appmarket.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContentFragment extends Fragment {
    @BindView(R.id.main_content_viewpager)
    ViewPager   mMainContentViewpager;
    @BindView(R.id.main_content_rb_home)
    RadioButton mMainContentRbHome;
    @BindView(R.id.main_content_rb_newscenter)
    RadioButton mMainContentRbNewscenter;
    @BindView(R.id.main_content_rg)
    RadioGroup  mMainContentRg;
    private String[] mContentTitleArr;
    private int      mCurItemIndex;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = View.inflate(getContext(), R.layout.fragment_main_content, null);
        initData();
        initListener();
        ButterKnife.bind(rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    public void initData() {
        //默认让首页的RidioButton有选中效果
        mMainContentRg.check(R.id.main_content_rb_home);

        //dataSets
        //        模拟数据集
        mContentTitleArr = new String[]{"首页", "新闻中心"};
        //view
        //data+view
        mMainContentViewpager.setAdapter(new ContentPagerAdapter());

    }

    class ContentPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            if (mContentTitleArr != null) {
                return mContentTitleArr.length;
            }
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //view-->假设就是一个普通的textView
            TextView tv = new TextView(container.getContext());
            tv.setGravity(Gravity.CENTER);
            tv.setTextColor(Color.RED);

            //data
            String data = mContentTitleArr[position];
            //data+view
            tv.setText(data);
            //加入容器
            container.addView(tv);

            return tv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    public void initListener() {
        mMainContentRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.main_content_rb_home://选中了 首页
                        mCurItemIndex = 0;

                        break;
                    case R.id.main_content_rb_newscenter://选中了 新闻中心
                        mCurItemIndex = 1;
                        break;
                    default:
                        break;
                }
                //完成viewpager的切换 去除切换动画
                mMainContentViewpager.setCurrentItem(mCurItemIndex,false);
            }
        });
    }
}
