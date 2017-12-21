package com.example.asustp.menurecycleviewtext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Store> storeList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initStore();

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        StoreAdapter adapter=new StoreAdapter(storeList);
        recyclerView.setAdapter(adapter);


    }
    private void initStore(){
        for(int i=0;i<10;i++){
            Store store1=new Store("小面馆","食堂");
            storeList.add(store1);
            Store store2=new Store("饺子","北街");
            storeList.add(store2);

        }
    }
}
