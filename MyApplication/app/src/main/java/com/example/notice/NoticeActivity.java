package com.example.notice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asustp.myapplication.R;
import com.example.userinfo._User;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class NoticeActivity extends AppCompatActivity {

    private String ID;
    private String category;
    private List<Notice> mNoticeList=new ArrayList<>();
    private SwipeRefreshLayout swipeRefresh;
    private NoticeAdapter adapter;
    private ListView listview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seven_notice_activity);
        getSupportActionBar().setTitle("校园通知");

        Intent intent=new Intent();
        ID=intent.getStringExtra("ID");

        listview= (ListView) findViewById(R.id.notice_list_view);
        swipeRefresh=(SwipeRefreshLayout)findViewById(R.id.notice_swip_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear();
                BmobQuery<Notice> query = new BmobQuery<>();
                query.order("-createdAt");// 按照时间降序
                query.addWhereEqualTo("category",category);
                query.findObjects(new FindListener<Notice>() {
                     @Override
                     public void done(List<Notice> list, BmobException e) {
                         if(e==null){//没有错误
                              Toast.makeText(getApplicationContext(), "刷新成功", Toast.LENGTH_SHORT).show();
                              for(int i=0;i<list.size();i++){
                              Notice notice=new Notice(list.get(0).getTitle(),list.get(0).getContent(), list.get(i).getCreatedAt());
                              mNoticeList.add(notice);
                          }
                        adapter = new NoticeAdapter(NoticeActivity.this, R.layout.seven_notice_item, mNoticeList);//建立适配器
                        listview.setAdapter(adapter);
                        swipeRefresh.setRefreshing(false);//停止刷新
                }
                else {
                    Toast.makeText(getApplicationContext(), "获取失败"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    swipeRefresh.setRefreshing(false);//停止刷新
                    }
                 }
                });
            }
        });
        initNotice();
    }
    private void initNotice(){
        BmobQuery<_User> query = new BmobQuery<>();
        query.addWhereEqualTo("username",ID);
        query.findObjects(new FindListener<_User>() {
            @Override
            public void done(List<_User> list, BmobException e) {
                if(e==null){
                    initNotice2(list.get(0).getCategory());
                }
            }
        });



    }
    private void initNotice2(final String mcategory){
        BmobQuery<Notice> query = new BmobQuery<>();
        query.order("-createdAt");// 按照时间降序
        query.addWhereEqualTo("category",mcategory);
        query.findObjects(new FindListener<Notice>() {
            @Override
            public void done(List<Notice> list, BmobException e) {
                if(e==null){//没有错误
                    Toast.makeText(getApplicationContext(), "获取成功", Toast.LENGTH_SHORT).show();
                    for(int i=0;i<list.size();i++){
                        Notice notice=new Notice(list.get(0).getTitle(),list.get(0).getContent(), list.get(i).getCreatedAt());
                        mNoticeList.add(notice);
                    }
                    adapter = new NoticeAdapter(NoticeActivity.this, R.layout.seven_notice_item, mNoticeList);//建立适配器
                    listview.setAdapter(adapter);
                    category=mcategory;
                }
                else {
                    Toast.makeText(getApplicationContext(), "获取失败"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    swipeRefresh.setRefreshing(false);//停止刷新
                }
            }
        });

    }

}
