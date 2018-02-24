package com.jikexueyuan.com.mycoursetable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SQLQueryListener;

public class MainActivity extends AppCompatActivity {

    private GridView detailCource;

    private MyAdapter adapter;
    private List<String> dataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this, "12a244a082bdc4511edeaf7f98a79c56");//Bmob初始化
        detailCource = (GridView)findViewById(R.id.courceDetail);

        dataList = init();
        adapter = new MyAdapter(this, dataList);
        detailCource.setAdapter(adapter);



        String bql ="select * from Course where course_name='数学'";//查询所有记录
        new BmobQuery<Course>().doSQLQuery(bql,new SQLQueryListener<Course>(){
            @Override
            public void done(BmobQueryResult<Course> result, BmobException e) {
                if(e ==null){
                    List<Course> list = (List<Course>) result.getResults();
                    Toast.makeText(getApplication(),list.get(0).getCourse_id().toString(),Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplication(),"错误码："+e.getErrorCode(),Toast.LENGTH_SHORT).show();
                }
            }
        });

        //                String bql ="select * from Found where phone='1'";//查询所有记录
//                new BmobQuery<Found>().doSQLQuery(bql,new SQLQueryListener<Found>(){
//                    @Override
//                    public void done(BmobQueryResult<Found> result, BmobException e) {
//                        if(e ==null){
//                            List<Found> list = (List<Found>) result.getResults();
//                            Toast.makeText(getApplication(),list.get(0).getDescribe(),Toast.LENGTH_SHORT).show();
//                        }else{
//                            Toast.makeText(getApplication(),"错误码："+e.getErrorCode(),Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });



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
