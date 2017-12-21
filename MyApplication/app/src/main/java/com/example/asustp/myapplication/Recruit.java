package com.example.asustp.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Recruit extends AppCompatActivity {

    private WebView recruitWeb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruit);
        getSupportActionBar().setTitle("招聘专栏");

        recruitWeb=(WebView)findViewById(R.id.recruitWeb);
        recruitWeb.loadUrl("http://jycy.hbu.cn/index.php/gggs.html");

        recruitWeb.setWebViewClient( new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });



        //设置自适应放大缩小
        WebSettings webSettings = recruitWeb.getSettings();
        webSettings.setJavaScriptEnabled(true);



// User settings

        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setUseWideViewPort(true);//关键点

        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        webSettings.setDisplayZoomControls(false);
        webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本
        webSettings.setAllowFileAccess(true); // 允许访问文件
        webSettings.setBuiltInZoomControls(true); // 设置显示缩放按钮
        webSettings.setSupportZoom(true); // 支持缩放



        webSettings.setLoadWithOverviewMode(true);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int mDensity = metrics.densityDpi;
        Log.d("maomao", "densityDpi = " + mDensity);
        if (mDensity == 240) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        } else if (mDensity == 160) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        } else if(mDensity == 120) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.CLOSE);
        }else if(mDensity == DisplayMetrics.DENSITY_XHIGH){
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        }else if (mDensity == DisplayMetrics.DENSITY_TV){
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        }else{
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        }


/**
 * 用WebView显示图片，可使用这个参数 设置网页布局类型： 1、LayoutAlgorithm.NARROW_COLUMNS ：
 * 适应内容大小 2、LayoutAlgorithm.SINGLE_COLUMN:适应屏幕，内容将自动缩放
 */
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);






    }
}
