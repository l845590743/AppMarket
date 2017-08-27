package com.lzm.appmarket.fragment;

import android.view.View;
import android.widget.TextView;

/**
 * Created by lxj on 2016/5/23.
 */
public class CategoryFragment extends BaseFragment {
    @Override
    protected View getSuccessView() {
        TextView textView = new TextView(getActivity());
        textView.setText(this.getClass().getSimpleName());
        return textView;
    }

    @Override
    protected Object onLoad() {
        return null;
    }
}
