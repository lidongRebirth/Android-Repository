package com.example.asustp.menulistviewtext;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by asus tp on 2018/1/2.
 */

public class JDBC {

    public static List<Store> sendRequestWithHttpURLConnection2(final String str) {
        //开启线程发起网络请求

        final List<Store> store = new ArrayList<Store>();
        final List<Store> store1 = new ArrayList<Store>();
//        HttpURLConnection connection=null;
//        try {
//            String sqld=str;
//            String data="sql="+sqld;
//
//            URL url = new URL("http://192.168.43.98:8080/JSPStudy/servlet");
//            connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("POST");
//            connection.setConnectTimeout(8000);
//            connection.setReadTimeout(8000);
//            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
//            out.writeBytes(data);
//            out.flush();
//            out.close();
//
//            ////
//
//            //下面对获取到的输入流进行读取
//            BufferedReader reader=null;
//            InputStream in=connection.getInputStream();
//            reader = new BufferedReader(new InputStreamReader(in));
//            StringBuffer response = new StringBuffer();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                response.append(line);
//            }
//
//
//            JSONArray jsonArray=new JSONArray(response.toString());
//            for(int i=0;i<jsonArray.length();i++){
//                Store ss=new Store();
//                JSONObject jsonObject=jsonArray.getJSONObject(i);
//                ss.setName(jsonObject.getString("NAME"));
//                ss.setAddress(jsonObject.getString("address"));
//                ss.setTel(jsonObject.getString("tel"));
//                store.add(ss);
//            }
////                    store2.addAll(store);
//            Log.d("testINthread", "sendRequestWithHttpURLConnection2:"+store.toString());
//            System.out.println("testing ");
//            System.out.println(store.get(1));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            if(connection!=null){
//                connection.disconnect();
//            }
//        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection=null;
                try {
                    String sqld=str;
                    String data="sql="+sqld;

                    URL url = new URL("http://192.168.43.98:8080/JSPStudy/servlet");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                    out.writeBytes(data);
                    out.flush();
                    out.close();

                    ////

                    //下面对获取到的输入流进行读取
                    BufferedReader reader=null;
                    InputStream in=connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuffer response = new StringBuffer();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }


                    JSONArray jsonArray=new JSONArray(response.toString());
                    for(int i=0;i<jsonArray.length();i++){
                        Store ss=new Store();
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        ss.setName(jsonObject.getString("NAME"));
                        ss.setAddress(jsonObject.getString("address"));
                        ss.setTel(jsonObject.getString("tel"));
                        store.add(ss);
                    }
                    store1.addAll(store);
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if(connection!=null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
        return store1;
    }
}
