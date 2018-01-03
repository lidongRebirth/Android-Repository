package com.example.asustp.menulistviewtext;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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


public class MainActivity extends AppCompatActivity {

    private List<Store> storeList=new ArrayList<>();//建立动态数组，元素类型为Store类
    private List<Store> store = new ArrayList<Store>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initStore();//初始化数据     将数据填充到数组中
        Log.d("testINthread", "sendRequestWithHttpURLConnection2:");
        StoreAdapter adapter = new StoreAdapter(MainActivity.this, R.layout.item_home, storeList);//建立适配器
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Store store=storeList.get(position);

                if(store.getName().equals("面食馆")){
                    String Menuname=store.getName();

                    Intent intent=new Intent(MainActivity.this,MenuActivity.class);//写MainActivity.this/getApplicationContext()都行
                    intent.putExtra("key",Menuname);


                    startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity.this,store.getName(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initStore() {
        List<Store> num=new ArrayList<>();
        Toast.makeText(getApplicationContext(),"开始连接",Toast.LENGTH_SHORT).show();

        int j=JDBC.sendRequestWithHttpURLConnection2("SELECT NAME, address, tel from shopstore").size();

        final String str="SELECT NAME, address, tel from shopstore";
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
                    int j=store.size();
                    Toast.makeText(getApplicationContext(),Integer.toString(j),Toast.LENGTH_SHORT).show();
                    for(int i=0;i<j;i++){
                        storeList.add(store.get(i));
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


//        int j=store.size();
//        for(int i=0;i<j;i++){//向控件中填充数据
//            storeList.add(store.get(i));
//            Store store2=new Store("饺子馆","北街"," 电话");
//            storeList.add(store2);


//        int j=JDBC.sendRequestWithHttpURLConnection2("SELECT NAME, address, tel from shopstore").size();
//        Toast.makeText(getApplicationContext(),Integer.toString(j),Toast.LENGTH_SHORT).show();
//        for(int i=0;i<j;i++){
//             storeList.add(JDBC.sendRequestWithHttpURLConnection2("SELECT NAME, address, tel from shopstore").get(i));
//        }
    }





}

