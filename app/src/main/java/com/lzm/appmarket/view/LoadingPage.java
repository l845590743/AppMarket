package com.lzm.appmarket.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.lzm.appmarket.R;
import com.lzm.appmarket.util.NetworkUtils;
import com.lzm.appmarket.util.Utils;

import java.util.List;

/**
 * Created by lzm on 2017/9/1.
 */
public abstract class LoadingPage extends FrameLayout {

    public LoadingPage(Context context) {
        this(context,null);
    }

    public LoadingPage(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadingPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLoadingPage();
    }

    //界面三种状态
    public enum PageState {
        STATE_LOADING,
        STATE_SUCCESS,
        STATE_ERROR
    }

    private static PageState mState = PageState.STATE_LOADING; //默认是加载中的状态

    private View loadingView;//加载中的View
    private View successView;//加载数据成功的View
    private View errorView;//加载失败的View
    private volatile LoadThread mLoadThread;


    private void initLoadingPage() {
        if (loadingView == null) {
            loadingView = View.inflate(getContext(), R.layout.page_loading, null);
        }
        addView(loadingView);

        if (errorView == null) {
            errorView = View.inflate(getContext(), R.layout.page_error, null);
            Button btn_reload = (Button) errorView.findViewById(R.id.btn_reload);
            btn_reload.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (NetworkUtils.isNetworkAvailable(getContext())) {
                        //1.先显示正在加载
                        mState = PageState.STATE_LOADING;
                        showPage();
                        //2.重新加载
                        reGetData();
                    } else {
                        Toast.makeText(getContext(),"请打开网络再试",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        addView(errorView);

        if (successView == null) {
            successView = getSuccessView();
        }

        if (successView != null) {
            addView(successView);
        } else {
            //如果successView是空，那么就没有必要往下执行，则抛出容易理解的异常
            throw new IllegalArgumentException("The successView can not be null!");
        }

        //当前界面默认是显示loadingView的
        showPage();

        //显示完默认界面，就去加载数据了
        getData();
    }

    /**
     * 加载数据，然后根据加载回来的数据，更新界面
     */
    private void loadData() {
//        if (mLoadThread == null) {
//            mLoadThread = new LoadThread();
//        }
//        mLoadThread.start();
    }

    private class LoadThread extends Thread {

        private LoadThread() {
        }

        public void stopLoadThread() {
            LoadThread tmpThread = mLoadThread;
            mLoadThread = null;
            if (tmpThread != null) {
                tmpThread.interrupt();
            }
        }

        @Override
        public void run() {
            if (mLoadThread == null) {
                return;
            }
//            Object object = getData();
//            checkData(object);
            Utils.runOnUIThread(new Runnable() {
                @Override
                public void run() {
                    showPage();
                }
            });
        }
    }

    /**
     * 根据返回的对象 判断其对应的界面
     */
    public PageState checkData(Object data,int mStatus) {
        if (data == null) {
            return PageState.STATE_ERROR;
        } else {
            if (data instanceof List) {
                List list = (List) data;
                if (list.size() == 0) {
                    return PageState.STATE_ERROR;
                }
            }

            if (mStatus != 1) {
                return PageState.STATE_ERROR;
            }
            return PageState.STATE_SUCCESS;
        }
    }


    /**
     * 根据不同的状态去显示不同的View
     */
    public void showPage() {
        //先隐藏所有滴的View
        loadingView.setVisibility(View.INVISIBLE);
        successView.setVisibility(View.INVISIBLE);
        errorView.setVisibility(View.INVISIBLE);

        switch (mState){
            case STATE_LOADING:
                loadingView.setVisibility(View.VISIBLE);
                break;
            case STATE_SUCCESS:
                successView.setVisibility(View.VISIBLE);
                break;
            case STATE_ERROR:
                errorView.setVisibility(View.VISIBLE);
                break;
        }
    }

    public static void setLoadingPage(boolean isShow) {
        if (isShow) {
            mState = PageState.STATE_SUCCESS;
        }
    }

    /**
     * 由子类去获取每个界面需要得数据
     * @return
     */
    protected abstract void getData();


    /**
     * 数据返回失败 重新去获取
     * @return
     */
    protected abstract void reGetData();

    /**
     * 获取successView，每个界面的successView都不一样，需要由每个子类
     * 自己去实现
     * @return
     */
    protected abstract View getSuccessView();

}
