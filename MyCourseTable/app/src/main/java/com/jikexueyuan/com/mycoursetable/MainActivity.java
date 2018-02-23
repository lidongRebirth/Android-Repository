package com.jikexueyuan.com.mycoursetable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GridView detailCource;

    private MyAdapter adapter;
    private List<String> dataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        detailCource = (GridView)findViewById(R.id.courceDetail);

        dataList = init();
        adapter = new MyAdapter(this, dataList);
        detailCource.setAdapter(adapter);
    }
    /**
     * 准备数据
     */
    private List<String> init() {
        List<String> list = new ArrayList<String>();
        list.add("现代测试技术B211");
        list.add("数据结构与算法B211");
        list.add("微机原理及应用E203");
        list.add("面向对象程序设计A309");
        list.add("数据结构与算法B207");
        list.add("");
        list.add("");
        list.add("微机原理及应用E203");
        list.add("");
        list.add("电磁场理论A212");
        list.add("传感器电子测量A\nC309");
        list.add("微机原理及应用E203");
        list.add("");
        list.add("");
        list.add("电磁场理论A212");
        list.add("面向对象程序设计A309");
        list.add("现代测试技术B211");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("传感器电子测量A\nC309");
        list.add("面向对象程序设计A309");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        return list;
    }
}
