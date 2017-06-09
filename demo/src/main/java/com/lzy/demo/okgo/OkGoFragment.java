package com.lzy.demo.okgo;

import android.content.Intent;

import com.lzy.demo.base.MainFragment;
import com.lzy.demo.model.ItemModel;
import com.lzy.demo.supercache.CacheDemoActivity;

import java.util.List;

/**
 * ================================================
 * 作    者：jeasonlzy（廖子尧）Github地址：https://github.com/jeasonlzy
 * 版    本：1.0
 * 创建日期：2017/6/9
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class OkGoFragment extends MainFragment {

    @Override
    public void fillData(List<ItemModel> items) {
        items.add(new ItemModel("基本功能(OkGo)",//
                                "1.GET，HEAD，OPTIONS，POST，PUT，DELETE 请求方法演示\n" +//
                                "2.请求服务器返回bitmap对象\n" +//
                                "3.支持https请求\n" +//
                                "4.支持同步请求\n" +//
                                "5.支持301重定向"));

        items.add(new ItemModel("自动解析Json对象",//
                                "1.自动解析JavaBean对象\n" + //
                                "2.自动解析List<JavaBean>集合对象"));

        items.add(new ItemModel("文件下载",//
                                "1.支持大文件或小文件下载，无论多大文件都不会发生OOM\n" +//
                                "2.支持监听下载进度和下载网速\n" +//
                                "3.支持自定义下载目录和下载文件名"));

        items.add(new ItemModel("文件上传",//
                                "1.支持上传单个文件\n" +//
                                "2.支持同时上传多个文件\n" +//
                                "3.支持多个文件多个参数同时上传\n" +//
                                "4.支持大文件上传,无论多大都不会发生OOM\n" +//
                                "5.支持监听上传进度和上传网速"));

        items.add(new ItemModel("强大的缓存示例 -- 先联网获取数据,然后断开网络再进试试",//
                                "1.OkGo的强大的缓存功能,让你代码无需关心数据来源,专注于业务逻辑的实现\n" +//
                                "2.共有五种缓存模式满足你各种使用场景\n" +//
                                "3.支持自定义缓存过期时间"));
    }

    @Override
    public void onItemClick(int position) {
        if (position == 0) startActivity(new Intent(context, OkHttpActivity.class));
        if (position == 1) startActivity(new Intent(context, JsonRequestActivity.class));
        if (position == 2) startActivity(new Intent(context, FileDownloadActivity.class));
        if (position == 3) startActivity(new Intent(context, FormUploadActivity.class));
        if (position == 4) startActivity(new Intent(context, CacheDemoActivity.class));
    }
}
