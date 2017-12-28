package com.example.asustp.jdbctext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String strUrl="http://localhost:8080/demo/demo";


       try{
           URL url=null;
           url=new URL(strUrl);
           HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
           InputStreamReader in =new InputStreamReader(urlConnection.getInputStream());
           BufferedReader bufferedReader=new BufferedReader(in);
           String result="";
           String readline=null;
           while ((readline=bufferedReader.readLine())!=null){
               result+=readline;
           }
           in.close();
           urlConnection.disconnect();//关闭连接
           TextView textView=(TextView)this.findViewById(R.id.te);
           textView.setText(result);
       }catch (Exception e){
           e.printStackTrace();
       }



    }
}
