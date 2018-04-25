package com.jikexueyuan.com.mysocketclient;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    EditText ip,editText;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ip= (EditText) findViewById(R.id.ip_edit);
        editText= (EditText) findViewById(R.id.content_edit);
        textView= (TextView) findViewById(R.id.content_text);
        findViewById(R.id.connect_btn).setOnClickListener(new View.OnClickListener() {//连接服务器
            @Override
            public void onClick(View view) {
                connect();
            }
        });

        findViewById(R.id.send_btn).setOnClickListener(new View.OnClickListener() {//发送消息
            @Override
            public void onClick(View view) {
                send();
            }
        });
    }

    //----------------------------------------------------------------------------
    Socket socket=null;
    BufferedWriter bufferedWriter=null;
    BufferedReader bufferedReader=null;


    public void connect(){//连接
            final String str=ip.getText().toString();
            //用于从网络 Socket 中读取数据
            AsyncTask<Void,String ,Void> read=new AsyncTask<Void, String, Void>() {//中间参数为String，用于聊天的过程
                @Override
                protected Void doInBackground(Void... voids) {
                    try {
                        socket=new Socket(str,12345);
                        bufferedWriter=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                        bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        publishProgress("@success");
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplication(),"连接失败",Toast.LENGTH_SHORT).show();
                    }
                    try {
                        String line;
                        while ((line=bufferedReader.readLine())!=null){
                            publishProgress(line);
                        }
                        bufferedReader.close();
                        bufferedWriter.close();
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplication(),"获取为空",Toast.LENGTH_SHORT).show();
                    }
                    return null;
                }
                @Override
                protected void onProgressUpdate(String... values) {
                    if(values[0].equals("@success")){
                        Toast.makeText(getApplication(),"连接成功",Toast.LENGTH_SHORT).show();
                    }
                    textView.append("别人说："+values[0]);
                    super.onProgressUpdate(values);
                }
            };
            read.execute();


    }
    public void send(){

        try {
            textView.append("我说："+editText.getText().toString()+"\n");//把我说的话也显示在屏幕上
            bufferedWriter.write(editText.getText().toString()+"\n");
            bufferedWriter.flush();//只有加了"\n" 和flush()才能接收到消息
            editText.setText("");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
