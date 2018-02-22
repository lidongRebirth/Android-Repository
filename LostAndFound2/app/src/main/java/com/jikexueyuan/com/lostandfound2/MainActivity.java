package com.jikexueyuan.com.lostandfound2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


public class MainActivity extends AppCompatActivity {

    private Button btn_add,btn_back;
    private List<Found> mFoundList=new ArrayList<>();
    private SwipeRefreshLayout swipeRefresh;
    private ListView listview;
    private FoundAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this, "12a244a082bdc4511edeaf7f98a79c56");//Bmob初始化



        initFound();//填充数据

        btn_add= (Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddActivity.class);//具体上线时加上传过去ID  以备删除使用，删除时可以巧妙的在我的那里进行删除
                startActivity(intent);
            }
        });
        listview = (ListView) findViewById(R.id.list_view);

        //下拉刷新
        swipeRefresh=(SwipeRefreshLayout)findViewById(R.id.swip_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear();
                BmobQuery<Found> query = new BmobQuery<>();
                query.order("-createdAt");// 按照时间降序
                query.findObjects(new FindListener<Found>() {
                    @Override
                    public void done(List<Found> list, BmobException e) {
                        if(e==null){//没有错误
                            Toast.makeText(getApplicationContext(), "获取成功", Toast.LENGTH_SHORT).show();
                            for(int i=0;i<list.size();i++){
                                Found mfound=new Found(list.get(i).getID(),list.get(i).getTitle(),list.get(i).getDescribe(),list.get(i).getPhone(), list.get(i).getCreatedAt());
                                mFoundList.add(mfound);
                            }
                            adapter = new FoundAdapter(MainActivity.this, R.layout.item_list, mFoundList);//建立适配器
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
        btn_back=(Button)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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

                Intent intent = new Intent(MainActivity.this,detailInfo.class);
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
        query.findObjects(new FindListener<Found>() {

            @Override
            public void done(List<Found> list, BmobException e) {
                if(e==null){//没有错误
                    Toast.makeText(getApplicationContext(), "获取成功", Toast.LENGTH_SHORT).show();
                    for(int i=0;i<list.size();i++){
                        Found mfound=new Found(list.get(i).getID(),list.get(i).getTitle(),list.get(i).getDescribe(),list.get(i).getPhone(), list.get(i).getCreatedAt());
                        mFoundList.add(mfound);
                    }
                    adapter = new FoundAdapter(MainActivity.this, R.layout.item_list, mFoundList);//建立适配器
                    listview.setAdapter(adapter);
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
//                Toast.makeText(MainActivity.this,data.getExtras().getString( "result" ),Toast.LENGTH_SHORT).show();
//                break;
//            default :
//                break;
//        }
//    }
}



/**/
/*
//                /*获取objectID为f333ccb579的对象信息*/
//                query.getObject("f333ccb579",new QueryListener<Found>() {
//                    @Override
//                    public void done(Found object,BmobException e) {
//                        if(e==null){
//                            Toast.makeText(MainActivity.this,object.getTitle(),Toast.LENGTH_SHORT).show();
//                        }else{
//                            Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//                query.addWhereEqualTo("title","丢东西啦");




/**/

//    BmobQuery<Found> query = new BmobQuery<>();
////query.order("-createdAt");// 按照时间降序
//
//                query.findObjects(new FindListener<Found>() {
//@Override
//public void done(List<Found> list, BmobException e) {
//        if(e==null){
////                            Toast.makeText(MainActivity.this,list.size(),Toast.LENGTH_SHORT).show();
//        Toast.makeText(MainActivity.this,list.get(0).getDescribe(),Toast.LENGTH_SHORT).show();
//        }
//        else {
//        Toast.makeText(MainActivity.this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
//        }
//        }
//        });