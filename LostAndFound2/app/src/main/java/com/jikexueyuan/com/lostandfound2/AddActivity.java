package com.jikexueyuan.com.lostandfound2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class AddActivity extends AppCompatActivity {
    Button btn_submit,btn_back;
    EditText edit_title,edit_phone,edit_describe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        btn_submit=(Button)findViewById(R.id.btn_submit);
        btn_back=(Button)findViewById(R.id.btn_back2);
        edit_title=(EditText)findViewById(R.id.edit_title);
        edit_phone=(EditText)findViewById(R.id.edit_phone);
        edit_describe=(EditText)findViewById(R.id.edit_describe);


        btn_submit.setOnClickListener(new View.OnClickListener() {//提交数据到服务端
            @Override
            public void onClick(View view) {
                Found f =new Found();
                f.setDescribe(edit_describe.getText().toString());
                f.setTitle(edit_title.getText().toString());
                f.setPhone(edit_phone.getText().toString());
                f.save(new  SaveListener<String>() {
                 @Override
                public void done(String objectId, BmobException e) {
                if(e==null){
                    Toast.makeText(getApplication(),"提交成功",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplication(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
                finish();
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {//返回键
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}
