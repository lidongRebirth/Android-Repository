package com.example.lostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.asustp.myapplication.R;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
/**
 * 添加新的失物招领
 *
 */

public class AddActivity extends AppCompatActivity {
    private String ID;
    private Button btn_submit,btn_back;
    private EditText edit_title,edit_phone,edit_describe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_activity_add);
        Intent intent = getIntent();          //获取从LostActivity中传来的用户ID
        ID = intent.getStringExtra("ID");



        btn_submit=(Button)findViewById(R.id.btn_submit);
        btn_back=(Button)findViewById(R.id.btn_back2);
        edit_title=(EditText)findViewById(R.id.edit_title);
        edit_phone=(EditText)findViewById(R.id.edit_phone);
        edit_describe=(EditText)findViewById(R.id.edit_describe);


        btn_submit.setOnClickListener(new View.OnClickListener() {//提交数据到服务端
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(edit_describe.getText())||TextUtils.isEmpty(edit_title.getText())||TextUtils.isEmpty(edit_phone.getText()))
                {
                    Toast.makeText(getApplicationContext(),"请填写完整信息",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Found f =new Found();
                    //加一个ID
                    f.setID(ID);
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
