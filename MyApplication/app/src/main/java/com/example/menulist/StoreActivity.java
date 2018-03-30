package com.example.menulist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.asustp.myapplication.R;

import java.util.ArrayList;
import java.util.List;


public class StoreActivity extends AppCompatActivity {

    private List<Store> storeList=new ArrayList<>();//建立动态数组，元素类型为Store类
//    private List<Store> store = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.four_store_activity_main);
        getSupportActionBar().setTitle("电话外卖");
        ListView listView = (ListView) findViewById(R.id.list_view);


        initStore();//初始化数据     将数据填充到数组中
        StoreAdapter adapter = new StoreAdapter(StoreActivity.this, R.layout.four_item_home, storeList);//建立适配器

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Store store=storeList.get(position);
//                String jk=Integer.toString(store.getStoreID());//测试是否获得了listview点击位置的值
//                Toast.makeText(getApplication(),jk,Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(StoreActivity.this,MenuActivity.class);//写MainActivity.this/getApplicationContext()都行
                intent.putExtra("key",store.getStoreID());
                intent.putExtra("tel",store.getTel());
                startActivity(intent);
//                if(store.getName().equals("面食馆")){
//                    String Menuname=store.getName();
//
//                    Intent intent=new Intent(StoreActivity.this,MenuActivity.class);//写MainActivity.this/getApplicationContext()都行
//                    intent.putExtra("key",Menuname);
//
//                    startActivity(intent);
//                }
//                else {
//                    Toast.makeText(StoreActivity.this,store.getName(),Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }

    private void initStore() {

        Store store[]=new Store[10];
        store[0]=new Store("名客佳大鸡排","西街美食","15832203284",1);
        store[1]=new Store("金三顺紫菜包饭","西街美食","15369224647",2);
        store[2]=new Store("烩面馆","北街","15100323581",3);
        store[3]=new Store("香河肉饼","北街","15075232521",4);
        store[4]=new Store("名客佳大鸡排","北街","15832203284",5);
        store[5]=new Store("金三顺紫菜包饭","北街","15369224647",6);
        store[6]=new Store("重庆小面","北街","18333202777",7);
        store[7]=new Store("烩面馆","北街","12365412632",8);
        store[8]=new Store("烩面馆","北街","12365412632",9);
        store[9]=new Store("烩面馆","北街","12365412632",10);
        for(int i=0;i<store.length;i++){
            storeList.add(store[i]);
        }
    }





}

