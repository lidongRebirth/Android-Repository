package com.jikexueyuan.com.myvolley;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.ImageLoader.ImageCache;

import org.json.JSONObject;

/**
 * Volley介绍：
 * Volley是Android平台的网络通讯库：更快、更简单、更健壮    适合频繁但数据量小的请求
 * volley提供的功能：
 * 1、JSON、图片（异步）
 * 2、网络请求的排序
 * 3、网络请求的优先级排序
 * 4、缓存
 * 5、多级别的取消请求
 * 6、与Activity的生命周期联动
 *
 * 获取Volley库
 *Git clone https://android.googlesource.com/platform/frameworks/volley
 * 大数据、流媒体不适合用Volley
 */
public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private NetworkImageView netImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView= (ImageView) findViewById(R.id.image);
        netImageView= (NetworkImageView) findViewById(R.id.network_image);
//        getJSONVolley();
        loadImageVolley();
        NetWorkImageViewVolley();
    }
    //获取Json字符串
    public void getJSONVolley(){
        RequestQueue requestQueue= Volley.newRequestQueue(this);//获取Volley请求对象
        String JSONDateUrl="http://www.wwtliu.com/jsondata.html";//请求地址
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,JSONDateUrl,null,//请求方式、请求地址、请求的内容（？）、监听事件、错误的监听事件
                                new Response.Listener<JSONObject>(){
                                    @Override
                                    public void onResponse(JSONObject jsonObject) {//请求成功获取到jsonObject对象
                                        System.out.println("response"+jsonObject);
                                    }
                                },
                                new Response.ErrorListener(){//请求失败的监听事件
                                    @Override
                                    public void onErrorResponse(VolleyError volleyError) {
                                        System.out.println("对不起有问题");
                                    }
                                }
        );
        requestQueue.add(jsonObjectRequest);
    }


    //Volley异步加载图片
    //图片地址：http://bmob-cdn-18408.b0.upaiyun.com/2018/04/24/1b76268a4065bf4480c7c8ad60b6fa9e.jpg
    public void loadImageVolley(){
        String imageurl="http://bmob-cdn-18408.b0.upaiyun.com/2018/04/24/1b76268a4065bf4480c7c8ad60b6fa9e.jpg";
        RequestQueue requestQueue=Volley.newRequestQueue(this);//获取Volley请求对象

        final LruCache<String ,Bitmap> lurCache=new LruCache<String ,Bitmap>(20);//缓存操作
        ImageCache imageCache=new ImageCache() {
            @Override
            public Bitmap getBitmap(String key) {
                return lurCache.get(key);           //从LruCache中获取图片
            }
            @Override
            public void putBitmap(String key, Bitmap bitmap) {
                lurCache.put(key,bitmap);           //将图片资源放到LruCache中
            }
        };
        ImageLoader mimageLoader=new ImageLoader(requestQueue,imageCache);//缓存的操作 返回指定为图片bitmap
        ImageListener mimagelistener=mimageLoader.getImageListener(imageView,R.drawable.ic_launch,R.drawable.error);//监听对象    控件、默认图片、错误的默认图片
        mimageLoader.get(imageurl,mimagelistener);
    }

    //NetWorkImageView  替代传统的ImageView
    //被父类控件dispach时，会自动取消网络请求，不用担心生命周期问题
    public void NetWorkImageViewVolley(){
        String imageUrl="http://bmob-cdn-18408.b0.upaiyun.com/2018/04/24/1b76268a4065bf4480c7c8ad60b6fa9e.jpg";
        RequestQueue requestQueue= Volley.newRequestQueue(this);//创建请求对象
        final LruCache<String ,Bitmap> lruCache=new LruCache<String ,Bitmap>(20);
        ImageCache imageCache=new ImageCache() {
            @Override
            public Bitmap getBitmap(String key) {
                return lruCache.get(key);
            }
            @Override
            public void putBitmap(String key, Bitmap bitmap) {
                    lruCache.put(key,bitmap);
            }
        };
        ImageLoader imageLoader=new ImageLoader(requestQueue,imageCache);
        netImageView.setTag("url");//listview中使用接触过很多，在那里面看教程
        netImageView.setImageUrl(imageUrl,imageLoader);
    }

}
