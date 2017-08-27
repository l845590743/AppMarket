package com.lzm.appmarket.fragment;

import android.view.View;
import android.widget.TextView;

/**
 * Created by lxj on 2016/5/23.
 */
public class HomeFragment extends BaseFragment {
    private TextView textView;

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        //将fragment的界面加载交给LoadingPage管理
//        LoadingPage loadingPage = new LoadingPage(getActivity()) {
//            @Override
//            protected Object onLoad() {
//                //去加载数据
//                //解析数据返回bean对象
//                return new String("sss");
//            }
//            @Override
//            protected View createSuccessView() {
//                TextView textView = new TextView(getActivity());
//                textView.setText("一碗牛肉面");
//                textView.setTextSize(30);
//                return textView;
//            }
//        };
//        return loadingPage;
//    }

    @Override
    protected View getSuccessView() {
        textView = new TextView(getActivity());
        textView.setText(this.getClass().getSimpleName());
        return textView;
    }

    @Override
    protected Object onLoad() {
//        final String result = HttpLoader.getInstance().executeGet("http://127.0.0.1:8090/home?index=0");




        //加载数据
        //通常写法：封装Retrofit请求网络数据
        //本地数据缓存：
        //网络数据和本地数据的调度：优先加载网络数据，如果网络数据为空，那么则加载本地数据；
        //如果网络数据不为空，要将网络数据缓存到本地;
        return "hehe";
    }
}
