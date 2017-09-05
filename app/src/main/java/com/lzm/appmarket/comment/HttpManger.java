package com.lzm.appmarket.comment;

import android.content.Context;
import android.os.Handler;

import com.lzm.appmarket.util.GsonUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpManger implements Callback{

    private Handler mHandler;
    private Context mContext;
    private OkHttpClient mOkHttpClient;
    private Class mResponseClass;
    private int mRequestType;
    public final static int CONNECT_TIMEOUT = 60;
    public final static int READ_TIMEOUT = 100;
    public final static int WRITE_TIMEOUT = 60;


    public HttpManger(Context context, Handler mHandler) {
        this.mContext = context;
        this.mHandler = mHandler;
        mOkHttpClient = new OkHttpClient.Builder()
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }


    public synchronized void httpRequest(final int requestType, Map<String, String> map,Class responseClass) {

            this.mRequestType = requestType;
            this.mResponseClass = responseClass;
//        String TOKEN = SharedUtils.getSharedUtils().getData(context, "token");
//        String ID = SharedUtils.getSharedUtils().getData(context, "uid");
        try {
            String url = Constants.getURL();
            Request.Builder builder = new Request.Builder().url(url);
//            builder.addHeader("token", TOKEN);  //将请求头以键值对形式添加，可添加多个请求头
//            builder.addHeader("uid", ID);
            Request request;
            if (map == null) {
                request = builder.build();
            } else {
                FormBody.Builder requestBody = new FormBody.Builder();
                for (Map.Entry<String, String> ele : map.entrySet()) {
                    requestBody.add(ele.getKey(), ele.getValue().toString());
                }
                request = builder.post(requestBody.build()).build();
            }
            mOkHttpClient.newCall(request).enqueue(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public synchronized void httpRequestPost(final int requestType, Map<String, String> map,Class responseClass) {


    }

    @Override
    public void onFailure(Call call, IOException e) {
        // LogUtils.d();
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        String result = UnicodeUtils.decode(response.body().string());
        if (result != null && !result.equals("") && !result.isEmpty() ) {
            int mStatus = 0;
            try {
                JSONObject jsonObject = new JSONObject(result);
                String status = jsonObject.optString("status");
                mStatus = Integer.parseInt(status);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Object responseBean = GsonUtil.parseJsonToBean(result, mResponseClass);

            mHandler.obtainMessage(mRequestType,mStatus,0,responseBean);
        }
    }
}
