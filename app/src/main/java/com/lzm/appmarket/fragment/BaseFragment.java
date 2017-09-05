package com.lzm.appmarket.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lzm.appmarket.comment.ResHeadAndBody;
import com.lzm.appmarket.view.LoadingPage;


public abstract class BaseFragment extends Fragment {

    private LoadingPage mLoadingPage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mLoadingPage == null) {
            mLoadingPage = new LoadingPage(getActivity()) {
                @Override
                protected void getData() {
                    OnLoad();
                }

                @Override
                protected void reGetData() {
                    OnResetLoad();
                }

                @Override
                protected View getSuccessView() {
                    return CreateSuccessView();
                }
            };
        }
        return mLoadingPage;
    }

    protected Handler bHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {

            int status = msg.arg1;

            if (mLoadingPage != null) {
                mLoadingPage.checkData(msg.obj,status);
                mLoadingPage.showPage();
            }
            if (null != msg.obj) {
                onHttpResponse(msg.what, msg.obj,status);
            } else {
                onHttpResponse(msg.what, null,status);
            }
        }
    };


    protected void onHttpResponse(int requestType, Object response,int responseStatus) {

    }

    /**
     * 获取成功的View。由子类去实现
     * @return
     */
    protected abstract View CreateSuccessView();

    /**
     * 获取数据，由子类去实现
     * @return
     */
    protected  void OnLoad() {

    }

    /**
     * 获取数据，由子类去实现
     * @return
     */
    protected  void OnResetLoad() {

    }

}
