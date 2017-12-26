package com.example.asustp.menulistviewtext;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.asustp.menulistviewtext.R.id.parent;

public class MainActivity extends AppCompatActivity {

    private List<Store> storeList=new ArrayList<>();//建立动态数组，元素类型为Store类

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initStore();//初始化数据     将数据填充到数组中

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
                    //intent.putExtra("key", (Bundle) parent.getAdapter().getItem(position));//



                    startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity.this,store.getName(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initStore() {
        for(int i=0;i<10;i++){
            Store store1=new Store("面食馆","河北大学");
            storeList.add(store1);
            Store store2=new Store("饺子馆","北街");
            storeList.add(store2);

        }

    }
}

