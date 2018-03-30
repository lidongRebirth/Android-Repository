package com.example.lostandfound;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asustp.myapplication.R;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class mydetailInfo extends AppCompatActivity {

    TextView tv_title;
    TextView tv_time;
    TextView tv_phone;
    TextView tv_describe;
    Button deleteBtn;
    String objectID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_my_detail_info);
        getSupportActionBar().setTitle("我的帖子");
        Bmob.initialize(this, "12a244a082bdc4511edeaf7f98a79c56");//Bmob初始化




        tv_title=(TextView)findViewById(R.id.tv_title3);
        tv_time=(TextView)findViewById(R.id.tv_time3);
        tv_phone=(TextView)findViewById(R.id.tv_phone3);
        tv_describe=(TextView)findViewById(R.id.tv_describe3);
        deleteBtn= (Button) findViewById(R.id.delete_Button);

        Bundle b=getIntent().getExtras();
        //获取Bundle的信息
        tv_title.setText(b.getString("title"));
//        String info=b.getString("fromWhere");
        tv_time.setText(b.getString("time"));
        tv_phone.setText(b.getString("phone"));
        tv_describe.setText(b.getString("describe"));
        objectID=b.getString("objectID");
        deleteBtn.setOnClickListener(new View.OnClickListener() {//删除数据只能通过objectId来删除，目前不提供查询条件方式的删除方法。
            @Override
            public void onClick(View view) {
                Found found = new Found();
                found.setObjectId(objectID);
                found.delete(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if(e==null){
                            Toast.makeText(getApplication(),"删除成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(getApplication(),"删除失败"+e.getMessage()+e.getErrorCode(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}
