package com.jikexueyuan.com.usingasynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.crypto.Mac;

public class MainActivity extends AppCompatActivity {
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text=(TextView)findViewById(R.id.textView1);
        findViewById(R.id.read).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReadURL("https://www.baidu.com");
            }
        });
    }
    public void ReadURL(String url){
        //new 创建一个AsyncTask实例
        //new AsyncTask<Params,Progress,Result>()  Paeams  传递入的数据类型  progress 中间处理过程返回值的类型 Result 返回结果的数据类型
        new AsyncTask<String, Float, String>() {
            //注意，在doInBackground中不能涉及更改UI或和主线程互动的操作，这些操作应该在以下的回调函数中执行
            @Override
            protected String doInBackground(String... arg0) {//此处arg0是String类型的数组
                //此处执行后台耗时的操作
                try {
                    URL url=new URL(arg0[0]);
                    URLConnection connection=url.openConnection();//获取当前的网络连接


                    long total=connection.getContentLength();//获取当前读取对象的全部长度
                    InputStream iStream=connection.getInputStream();//获取当前连接的InputStream
                    InputStreamReader isr=new InputStreamReader(iStream);//将数据流一层一层包裹
                    BufferedReader br=new BufferedReader(isr);//继续包裹  包装完成


                    //上面包装完成后就可以读取网络数据了
                    String line;
                    StringBuilder builder=new StringBuilder();//
                    while ((line=br.readLine())!=null){
                        builder.append(line);//网页所有内容都被读取到StringBuilder中了
                        publishProgress((float)builder.toString().length()/total);//可以得到当前百分比
                    }//关闭所有的流
                    br.close();
                    iStream.close();
                    return builder.toString();  //代表doInBackground这个已经结束  AsyncTask会继续调用其他回调方法完成其他工作
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    System.err.println("此处错误1");
                } catch (IOException e) {
                    e.printStackTrace();
                    System.err.println("此处错误2");
                }
                return null;
            }
            //以下为AsyncTask本有的方法
            @Override
            protected void onPreExecute() {//当前的AsyncTask执行之前会自动的回调这个方法
                Toast.makeText(MainActivity.this,"开始读取",Toast.LENGTH_SHORT).show();
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {//当前的AsyncTask执行完成后后自动的回调这个方法
                text.setText(s);
                super.onPostExecute(s);//传来的s其实就是doInBackground（）返回的值
            }

            @Override
            protected void onProgressUpdate(Float... values) {//用于执行过程中对外发布执行的进度
                System.err.println(values[0]);
                super.onProgressUpdate(values);//传入的类型与 new AsyncTask<String, Void, String>中间的类型一直致
            }
        }.execute(url);
    }


}
