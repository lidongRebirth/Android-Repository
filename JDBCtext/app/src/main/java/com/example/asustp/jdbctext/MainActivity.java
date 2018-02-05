package com.example.asustp.jdbctext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView responseText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendRequest = (Button) findViewById(R.id.send_request);
        responseText = (TextView) findViewById(R.id.response_text);
        sendRequest.setOnClickListener(this);
    }
///////////        异步操作成功  要注意主机ip是内网
//        findViewById(R.id.send_request).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {//读取互联网数据是比较耗时的io操作，所以使用异步操作来完成
//                Toast.makeText(getApplicationContext(),"进入异步",Toast.LENGTH_SHORT).show();
//                new AsyncTask<String,Void,Void>(){
//                    @Override
//                    protected Void doInBackground(String... strings) {
//                        try {
//                            URL url=new URL(strings[0]);//strings中的第一个元素
//                            try {
//                                URLConnection connection=url.openConnection();//获取互联网的连接
//                                InputStream is=connection.getInputStream();//获取数据  返回值为inputstream类型
//                                InputStreamReader isr=new InputStreamReader(is,"utf-8");//字节到字符集的转化//将InputStream封装成InputStreamReader
//                                BufferedReader br=new BufferedReader(isr);//通过BufferedReader可以直接读取数据的一行字符串
//                                String line;
//                                while((line=br.readLine())!=null){//读取到的一行数据不为空
////                                    Toast.makeText(getApplicationContext(),line,Toast.LENGTH_SHORT).show();
//                                    System.out.print(line);
//                                    showResponse(line);
//                                }
//                                br.close();
//                                isr.close();
//                                is.close();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//
//
//                        } catch (MalformedURLException e) {
//                            e.printStackTrace();
//                        }
//                        return null;
//                    }
//                }.execute("http://192.168.1.111:8080/demo/demo");//AsyncTask的结束
//
//            }
//        });
//    }
//    private void showResponse(final String response) {
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                //在这里进行UI操作，将结果显示到界面上
//                responseText.setText(response);
//            }
//        });
//    }
//    //////////异步操作完成










//        String strUrl="http://localhost:8080/demo/demo";
//       try{
//           URL url=null;
//           url=new URL(strUrl);
//           HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
//           InputStreamReader in =new InputStreamReader(urlConnection.getInputStream());
//           BufferedReader bufferedReader=new BufferedReader(in);
//           String result="";
//           String readline=null;
//           while ((readline=bufferedReader.readLine())!=null){
//               result+=readline;
//           }
//           in.close();
//           urlConnection.disconnect();//关闭连接
//           TextView textView=(TextView)this.findViewById(R.id.te);
//           textView.setText(result);
//       }catch (Exception e){
//           e.printStackTrace();
//       }

    @Override
    public  void onClick(View view){
        if(view.getId()==R.id.send_request){
            //sendRequestWithHttpURLConnection();
            String sql="SELECT FoodName from menu WHERE StoreID='1'";
            sendRequestWithHttpURLConnection2(sql);
            Toast.makeText(getApplicationContext(),"按钮触发",Toast.LENGTH_SHORT).show();
        }
    }
    private void sendRequestWithHttpURLConnection() {
        //开启线程发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection=null;
                BufferedReader reader=null;
                try {
                    URL url = new URL("http://192.168.1.105:8080/JSPStudy/servlet");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    //下面对获取到的输入流进行读取
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuffer response = new StringBuffer();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    showResponse(response.toString());


                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if(reader!=null){
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if(connection!=null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //在这里进行UI操作，将结果显示到界面上
                responseText.setText(response);
            }
        });
    }
    //给服务器发送请求
    private void sendRequestWithHttpURLConnection2(final String str) {
        //开启线程发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection=null;
                try {
//                    String str="lidong";
//                    String str1=URLEncoder.encode(str, "UTF-8");  不用写
//                    String password="123456";
//                    String password2=URLEncoder.encode(password, "UTF-8");
//                    String data="username="+str+"&password="+password;
                    String sqld=str;
                    String data="sql="+sqld;

                    URL url = new URL("http://192.168.1.111:8080/JSPStudy/servlet");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                    out.writeBytes(data);
                    out.flush();
                    out.close();

                    ////
                    BufferedReader reader=null;
                    //下面对获取到的输入流进行读取
                    InputStream in=connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuffer response = new StringBuffer();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
//                    showResponse(response.toString());//在UI中显示
                    JSONArray jsonArray=new JSONArray(response.toString());
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
//                        String StoreID=jsonObject.getString("StoreID");
//                        String price=jsonObject.getString("price");
                        String FoodName=jsonObject.getString("FoodName");
//                        Log.d("MainActivity","StoreID is"+StoreID);
//                        Log.d("MainActivity","price is"+price);
                        showResponse(FoodName);
                        Log.d("MainActivity","FoodName is"+FoodName);
                    }



                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if(connection!=null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
}
