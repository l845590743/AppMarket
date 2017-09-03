package com.lzm.appmarket.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import com.lzm.appmarket.comment.AppMarket;

import java.security.MessageDigest;

/**
 * 常用工具方法,如获取各种资源的方法(字符串，颜色，dimens资源)
 */
public class Utils {
    /**
     * 在主线程执行任务
     */
    public static void runOnUIThread(Runnable runnable){
        AppMarket.mainHandler.post(runnable);
    }

    public static String getString(int resId){
        return AppMarket.context.getResources().getString(resId);
    }

    public static String[] getStringArray(int resId){
        return AppMarket.context.getResources().getStringArray(resId);
    }

    public static int getColor(int resId){
        return AppMarket.context.getResources().getColor(resId);
    }

    public static Drawable getDrawable(int resId){
        return AppMarket.context.getResources().getDrawable(resId);
    }

    /**
     * 获取定义的dp资源,并且会自动将dp值转为像素
     * @param resId
     * @return
     */
    public static int getDimens(int resId){
        return AppMarket.context.getResources().getDimensionPixelSize(resId);
    }

    public final static String MD5(String str) {
        char hexDigits[] = { // 用来将字节转换成 16 进制表示的字符
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
                'e', 'f' };
        try {
            byte[] strTemp = str.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte tmp[] = mdTemp.digest(); // MD5 的计算结果是一个 128 位的长整数，
            // 用字节表示就是 16 个字节
            char strs[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
            // 所以表示成 16 进制需要 32 个字符
            int k = 0; // 表示转换结果中对应的字符位置
            for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
                // 转换成 16 进制字符的转换
                byte byte0 = tmp[i]; // 取第 i 个字节
                strs[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
                // >>> 为逻辑右移，将符号位一起右移
                strs[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
            }
            return new String(strs).toUpperCase(); // 换后的结果转换为字符串
        } catch (Exception e) {
            return null;
        }
    }

    public static String getVersionName(Context context){
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            String  version = packageInfo.versionName;
            return version;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
