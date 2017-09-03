package com.lzm.appmarket.comment;

import android.content.Context;

import com.lzm.appmarket.util.Utils;

import java.util.Map;

/**
 * Created by mm on 2016/11/21.
 */
public class Constants {

    // sp 文件名
    public static final String SHARED_PREFERENCE = "LoginAndRegister";

    public static final String USERNAME = "Username";

    public static final String PASSWORD = "Password";

    public static final String SESSID = "Sessid";

    public static final String USERID = "Userid";

    public static final String ONEKEY_REGISTER_USERID = "Onekey_Userid";

    public static final String ISLOGIN = "isLogin";

    public static final String IS_ONEKEY_REGISTER = "isOneKeyRegister";

    public static final String ISREGISTER = "isRegister";

    public static final String baseUrl = "https://passport.deepoon.com/api/";

    // 判断用户名是否存在
    public static final int CHECK_USERNAME_IS_EXIST = 100;

    // 用户注册
    public static final int USER_REGISTER = 101;

    // 用户登录
    public static final int USER_LOGIN = 102;

    // 一键注册
    public static final int ONEKEY_REGISTER = 103;

    // 获取验证码
    public static final int REQUEST_VERIFICATION = 104;

    // 刷新验证码
    public static final int REFRESH_VERIFICATION = 105;

    public static final int TEST_VERIFICATION = 106;

    // 一键注册用户修改信息
    public static final int CHANGE_USERNAME = 107;


    public static String getRequestUrl(int requestType, Map bodyRequest) {

        switch (requestType) {
            case CHECK_USERNAME_IS_EXIST:
                return baseUrl + "sdk/check_user_exist" + "?serialno=" + bodyRequest.get("serialno") + "&username=" + bodyRequest.get("username");

            case USER_REGISTER:
                return baseUrl + "sdk/sign_up" + "?serialno=" + bodyRequest.get("serialno")
                        + "&verify=" + bodyRequest.get("verify") + "&username=" + bodyRequest.get("username")
                        + "&password=" + bodyRequest.get("password") + "&sessid=" + bodyRequest.get("sessid");

            case USER_LOGIN:
                return baseUrl + "sdk/sign_in" + "?serialno=" + bodyRequest.get("serialno")
                        + "&verify=" + bodyRequest.get("verify") + "&username=" + bodyRequest.get("username")
                        + "&password=" + bodyRequest.get("password") + "&sessid=" + bodyRequest.get("sessid")+ "&haveverify=" + bodyRequest.get("haveverify");

            case ONEKEY_REGISTER:
                return baseUrl + "sdk/register" + "?serialno=" + bodyRequest.get("serialno");

            case REQUEST_VERIFICATION:
                return baseUrl + "sdk/verification" + "?serialno=" + bodyRequest.get("serialno") + "&sessid=" + bodyRequest.get("sessid");

            case REFRESH_VERIFICATION:
                return baseUrl + "sdk/verification" + "?serialno=" + bodyRequest.get("serialno") + "&ran=" + bodyRequest.get("ran");

            case TEST_VERIFICATION:
                return baseUrl + "sdk/test" + "?serialno=" + bodyRequest.get("serialno") + "&verify=" + bodyRequest.get("verify");

            case CHANGE_USERNAME:
                return baseUrl + "sdk/change_username" + "?serialno=" + bodyRequest.get("serialno")
                        + "&userid=" + bodyRequest.get("userid") + "&username=" + bodyRequest.get("username")
                        + "&password=" + bodyRequest.get("password") + "&sessid=" + bodyRequest.get("sessid");
            default:
                break;
        }
       return null;
    }


    public static String httpRequestURl(int requestType, Map bodyRequest,Context context){
        String serialno = "000000";
        String requestUrl = getRequestUrl(requestType, bodyRequest);
        String res = "";
        String token = "";
        String timeStamp = getTimeStamp();
        String s = new String(requestUrl);
        String[] split = s.split("\\?");
        String URL = split[0];
        String version = Utils.getVersionName(context);
        token =  Utils.MD5(serialno+timeStamp+version+"****#####");
        String str = "?";
        if(bodyRequest==null){
            str = "?";
        }else{
            str = "&";
        }
            res = requestUrl+ str +"timestamp="+timeStamp+"&accesstoken="+ token.toLowerCase() + "&version=" + version;

        return res;
    }

    public static String getTimeStamp()
    {
        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();
        return ts;
    }
}
