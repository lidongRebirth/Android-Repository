package com.example.gradetable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asustp.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

//修改部分 id是通过startactivity  intent传过来

public class GradeActivity extends AppCompatActivity {

    private String id="20144138169";    //此处到时候通过startactivity  intent传过来
    private List<select_course> mGradeList=new ArrayList<>();
    private Grade_adapter adapter;
    private ListView listview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.three_grade_activity_main);
        Bmob.initialize(this, "12a244a082bdc4511edeaf7f98a79c56");//Bmob初始化
        listview = (ListView) findViewById(R.id.grade_list_view);
        initGrade(id);

    }
    private void initGrade(String str){
        BmobQuery<select_course> query = new BmobQuery<>();
        query.addWhereEqualTo("ID",str);
        query.findObjects(new FindListener<select_course>() {
            @Override
            public void done(List<select_course> list, BmobException e) {
                if(e==null){//没有错误
                    Toast.makeText(getApplicationContext(), "获取成功", Toast.LENGTH_SHORT).show();
                    for(int i=0;i<list.size();i++){
                        select_course course=new select_course(list.get(i).getID(),list.get(i).getCourse_name(),list.get(i).getGrade());
                        mGradeList.add(course);
                    }
//                    Toast.makeText(getApplicationContext(), mGradeList.get(0).getCourse_name(), Toast.LENGTH_SHORT).show();//测试是否获得到了数据
                    adapter = new Grade_adapter(GradeActivity.this, R.layout.three_grade_item_list, mGradeList);//建立适配器
                    listview.setAdapter(adapter);
//                    text=mGradeList.get(0).getCourse_name(); 测试 此处还是不能直接进行给成员变量赋值，因为在UI线程中用成员变量的时候可能此值还未传过去
                }
                else {
                    Toast.makeText(getApplicationContext(), "获取失败"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
//        String id=c_id;
//        String bql="select * from select_course where ID='20144138169'";
//        new BmobQuery<select_course>().doSQLQuery(bql,new SQLQueryListener<select_course>(){
//            @Override
//            public void done(BmobQueryResult<select_course> result, BmobException e) {
//                if(e ==null){
//                    Toast.makeText(getApplicationContext(), "获取成功", Toast.LENGTH_SHORT).show();
//                    List<select_course> list = (List<select_course>) result.getResults();//一天的课
////                    Toast.makeText(getApplicationContext(),list.get(0).getCourse_name(), Toast.LENGTH_SHORT).show();
////                    mGradeList= (List<select_course>) result.getResults();//一天的课
//                    adapter = new Grade_adapter(GradeActivity.this, R.layout.three_grade_item_list, list);//建立适配器
//                    listview.setAdapter(adapter);
//
//                }
//                else{
//                    Toast.makeText(getApplicationContext(), "获取失败"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        BmobQuery<select_course> query=new BmobQuery<select_course>();
//        //设置查询的SQL语句
//        query.setSQL(bql);
//        query.doSQLQuery(new SQLQueryListener<select_course>(){
//
//            @Override
//            public void done(BmobQueryResult<select_course> result, BmobException e) {
//                if(e ==null){
//                    List<select_course> list = (List<select_course>) result.getResults();
//                    if(list!=null && list.size()>0){
//                        adapter = new Grade_adapter(GradeActivity.this, R.layout.three_grade_item_list, list);//建立适配器
//                        listview.setAdapter(adapter);
//                    }else{
//                        Toast.makeText(getApplicationContext(), "查询成功，无数据返回", Toast.LENGTH_SHORT).show();
//                    }
//                }else{
//                    Toast.makeText(getApplicationContext(), "错误码："+e.getErrorCode()+"，错误描述："+e.getMessage(), Toast.LENGTH_SHORT).show();
//
//                }
//            }
//        });



    }

}
