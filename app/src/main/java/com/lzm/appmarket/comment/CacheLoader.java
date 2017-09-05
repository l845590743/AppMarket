//package com.lzm.appmarket.comment;
//
//import android.os.Environment;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//
///**
// * 负责缓存数据的加载与保存，
// * 1.定义数据缓存目录
// * 2.实现获取缓存数据的方法
// * 3.实现保存数据到本地的方法
// * Created by lxj on 2016/5/23.
// */
//public class CacheLoader {
//    //定义缓存文件的目录
//    //最终拼接的目录为：/mnt/sdcard/shell/emulated/0/包名/cache
//    private static String CACHE_DIR = Environment.getExternalStorageDirectory()
//            + File.separator + GooglePlayApp.context.getPackageName()
//            + File.separator + "cache";
//
//    private CacheLoader(){
//        //初始化缓存目录
//        File cacheDir = new File(CACHE_DIR);
//        if(!cacheDir.exists()){
//            //创建目录结构
//            cacheDir.mkdirs();
//        }
//    }
//    private static CacheLoader mInstance = new CacheLoader();
//    public static CacheLoader getInstance(){
//        return mInstance;
//    }
//
//    /**
//     * 根据url读取本地缓存数据
//     * @param url
//     * @return
//     */
//    public String getCacheData(String url) {
//        //1.构建要读取的缓存文件
//        File file = buildCacheFile(url);
//        //2.开始读取文件
//        StringBuilder builder = new StringBuilder();
//        if(file.exists()){
//            try {
//                BufferedReader reader = new BufferedReader(new FileReader(file));
//                String line = null;
//                while((line=reader.readLine())!=null){
//                    builder.append(line);
//                }
//                //关流
//                reader.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return builder.toString();
//    }
//
//    /**
//     * 缓存数据到本地文件
//     * @param url
//     * @param result
//     */
//    public void saveCacheData(String url, String result) {
//        //1.构建缓存文件,缓存文件的文件名称需要生层
//        File file = buildCacheFile(url);
//        //2.将result数据存入file中
//        try {
//            if(!file.exists()){
//                file.createNewFile();
//            }
//            FileWriter writer = new FileWriter(file);
//            writer.write(result);
//            //刷一下
//            writer.flush();
//            writer.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 构建缓存文件,生成唯一的缓存文件名称
//     * @return
//     */
//    private File buildCacheFile(String url) {
//        return new File(CACHE_DIR, MD5Util.digest(url));
//    }
//}
