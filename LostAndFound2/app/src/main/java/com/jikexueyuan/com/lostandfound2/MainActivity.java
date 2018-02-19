package com.jikexueyuan.com.lostandfound2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class MainActivity extends AppCompatActivity {

    Button btn_add,btn_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this, "12a244a082bdc4511edeaf7f98a79c56");//Bmob初始化

        btn_add= (Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });

///*
//填充数据
//* */
//        Found f =new Found();
//        f.setDescribe("iphone8手机一部");
//        f.setTitle("丢失东西");
//        f.setPhone("15232253160");
//        f.save(new  SaveListener<String>() {
//
//            @Override
//            public void done(String objectId, BmobException e) {
//                if(e==null){
//                    Toast.makeText(MainActivity.this,"success",Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
//                }
//            }
//        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch ( resultCode ) {
            case RESULT_OK :
                Toast.makeText(MainActivity.this,data.getExtras().getString( "result" ),Toast.LENGTH_SHORT).show();
                break;
            default :
                break;
        }
    }
}
