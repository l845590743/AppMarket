package com.lzm.appmarket.fragment;

import android.view.View;
import android.widget.TextView;


public class HotFragment extends BaseFragment {

    @Override
    protected View CreateSuccessView() {
        TextView textView = new TextView(getActivity());
        textView.setText(this.getClass().getSimpleName());
        return textView;
    }

}
