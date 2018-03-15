package com.example.lostandfound;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.asustp.myapplication.R;          //引入此就可以解决R文件不识别了

public class detailInfo extends AppCompatActivity {

    TextView tv_title;
    TextView tv_time;
    TextView tv_phone;
    TextView tv_describe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_activity_detail_info);

        tv_title=(TextView)findViewById(R.id.tv_title2);
        tv_time=(TextView)findViewById(R.id.tv_time2);
        tv_phone=(TextView)findViewById(R.id.tv_phone2);
        tv_describe=(TextView)findViewById(R.id.tv_describe2);


        Bundle b=getIntent().getExtras();
        //获取Bundle的信息
        tv_title.setText(b.getString("title"));
//        String info=b.getString("fromWhere");
        tv_time.setText(b.getString("time"));
        tv_phone.setText(b.getString("phone"));
        tv_describe.setText(b.getString("describe"));
    }
}
