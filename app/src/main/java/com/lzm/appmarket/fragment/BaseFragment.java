package com.lzm.appmarket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lxj on 2016/5/23.
 */
public abstract class BaseFragment extends Fragment {

//    protected LoadingPage loadingPage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //将fragment的界面加载交给LoadingPage管理
//        Log.e(this,"onCreateView " + this.getClass().getSimpleName()  +" loadingPage==null: "
//                +(loadingPage==null));

//        if(loadingPage==null){
//            loadingPage = new LoadingPage(getActivity()) {
//                @Override
//                protected Object onLoad() {
//                    //方法自己调用自己：stackoverflow
//                    return BaseFragment.this.onLoad();
//                }
//                @Override
//                protected View createSuccessView() {
//                    return getSuccessView();
//                }
//            };
//        }
        return getSuccessView();
    }

    /**
     * 获取成功的View。由子类去实现
     * @return
     */
    protected abstract View getSuccessView();

    /**
     * 获取数据，由子类去实现
     * @return
     */
    protected abstract Object onLoad();

}
