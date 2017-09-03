package com.lzm.appmarket.fragment;

import android.view.View;
import android.widget.TextView;

public class HomeFragment extends BaseFragment {

    @Override
    protected View CreateSuccessView() {
        TextView textView = new TextView(getActivity());
        textView.setText(this.getClass().getSimpleName());
        return textView;
    }

    @Override
    protected Object OnLoad() {
        return null;
    }
}
