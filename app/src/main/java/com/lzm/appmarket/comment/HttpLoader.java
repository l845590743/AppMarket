package com.lzm.appmarket.comment;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 负责加载网络数据，相当于http client，提供get，post请求等方法
 * http解决方案：
 * 系统提供：HttpURLConnection , HttpClient(android4.4之后被废弃了),
 * 第三方：XUtil, Retrofit, OKHttp, Volley, AsyncHttpClent, ion等
 * OKHttp:
 * a. 支持spdy协议，(后来国际互联网标准组织提出了http2.0)
 * b. 内部采用同一个socket链接来实现请求，并实现的连接池来达到链接的
 * c. 重用，这样可以减少网络的延时;
 * d. 透明的实现的gzip压缩
 * e. 支持响应缓存
 *
 * 此处：使用OKHttp来实现网络数据的加载
 */
public class HttpLoader{
    private OkHttpClient okHttpClient;
    private HttpLoader(){
        okHttpClient = new OkHttpClient();
    }
    private static HttpLoader mInstance = new HttpLoader();
    public static HttpLoader getInstance(){
        return mInstance;
    }

    /**
     * 同步执行get请求
     * @param url
     */
    public String executeGet(String url) {
//        LogUtil.e(this,"请求的url: "+url);
        //Request是请求数据的封装体
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);//创建http请求对象
        Response response = null;
        ResponseBody body = null;
        String result = null;
        try {
            response = call.execute();//执行请求,获取响应对象，包括响应头，响应体
            //判断请求是否成功，其实就是判断响应吗是否等于200
            if(response.isSuccessful()){
                body = response.body();//获取响应体对象
                result = body.string();//将body转为字符串
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 异步执行get请求
     */
    public void executeGet(String url,Callback callback){
        //Request是请求数据的封装体
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);//创建http请求对象
        //执行异步请求
        call.enqueue(callback);
    }
}
