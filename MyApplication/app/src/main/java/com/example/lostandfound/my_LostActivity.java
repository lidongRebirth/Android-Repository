package com.example.lostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asustp.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class my_LostActivity extends AppCompatActivity {

    private List<Found> mFoundList=new ArrayList<>();
    private SwipeRefreshLayout swipeRefresh;
    private ListView listview;
    private FoundAdapter adapter;
    private String ID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_my_lostactivity_main);
        getSupportActionBar().setTitle("我的帖子");
        Intent intent=getIntent();
        ID=intent.getStringExtra("ID");
        Bmob.initialize(this, "12a244a082bdc4511edeaf7f98a79c56");//Bmob初始化
        initFound();//填充数据

        listview = (ListView) findViewById(R.id.text_list_view);

        //下拉刷新
        swipeRefresh=(SwipeRefreshLayout)findViewById(R.id.text_swip_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear();
                BmobQuery<Found> query = new BmobQuery<>();
                query.order("-createdAt");// 按照时间降序
                query.addWhereEqualTo("ID",ID);
                query.findObjects(new FindListener<Found>() {
                    @Override
                    public void done(List<Found> list, BmobException e) {
                        if(e==null){//没有错误
                            Toast.makeText(getApplicationContext(), "刷新成功", Toast.LENGTH_SHORT).show();
                            for(int i=0;i<list.size();i++){
                                Found mfound=new Found(list.get(i).getID(),list.get(i).getTitle(),list.get(i).getDescribe(),list.get(i).getPhone(), list.get(i).getCreatedAt(),list.get(i).getObjectId());
                                mFoundList.add(mfound);
                            }
                            adapter = new FoundAdapter(my_LostActivity.this, R.layout.one_item_list, mFoundList);//建立适配器
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

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Found f=mFoundList.get(i);
                Bundle bundle = new Bundle();
                bundle.putString("title", f.getTitle());
                bundle.putString("describe", f.getDescribe());
                bundle.putString("phone",f.getPhone());
                bundle.putString("time",f.getTime());
                bundle.putString("objectID",f.gettheObjectId());
                Intent intent = new Intent(my_LostActivity.this,mydetailInfo.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
    /**
     * 查询全部失物信息queryLosts
     *
     * @return void
     * @throws
     */
    private void initFound(){
        BmobQuery<Found> query = new BmobQuery<>();
        query.order("-createdAt");// 按照时间降序
        query.addWhereEqualTo("ID",ID);
        query.findObjects(new FindListener<Found>() {

            @Override
            public void done(List<Found> list, BmobException e) {
                if(e==null){//没有错误
                    if(list.size()==0){
                        Toast.makeText(getApplicationContext(), "无已发帖子", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        for(int i=0;i<list.size();i++){
                            Found mfound=new Found(list.get(i).getID(),list.get(i).getTitle(),list.get(i).getDescribe(),list.get(i).getPhone(), list.get(i).getCreatedAt(),list.get(i).getObjectId());
                            mFoundList.add(mfound);
                        }
                        adapter = new FoundAdapter(my_LostActivity.this, R.layout.one_item_list, mFoundList);//建立适配器
                        listview.setAdapter(adapter);
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "获取失败"+e.getMessage(), Toast.LENGTH_SHORT).show();

                    swipeRefresh.setRefreshing(false);//停止刷新
                }
            }
        });



//
    }













    //未用到，用于从新打开的activity中传数据到此Activity中
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        switch ( resultCode ) {
//            case RESULT_OK :
//                Toast.makeText(CourseActivity.this,data.getExtras().getString( "result" ),Toast.LENGTH_SHORT).show();
//                break;
//            default :
//                break;
//        }
//    }
}
