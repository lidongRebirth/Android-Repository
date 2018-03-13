package com.example.asustp.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/*
* 主页面，fragment1的页面，默认选择
*
* */

public class MainActivity extends AppCompatActivity {
    private RadioGroup select;
    private RadioButton btn1,btn2,btn3;
    private String ID="20144138169";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//去掉标题栏
        setContentView(R.layout.activity_main);

        select=(RadioGroup)findViewById(R.id.main_tab);
        btn1=(RadioButton)findViewById(R.id.radio_button0);
        btn2=(RadioButton)findViewById(R.id.radio_button1);
        btn3=(RadioButton)findViewById(R.id.radio_button2);

        Intent intent=getIntent();          //获取从LoginActivity中传来的用户ID
        ID=intent.getStringExtra("ID");
        Toast.makeText(getApplicationContext(),ID,Toast.LENGTH_SHORT).show();



        //碎片填充容器
        replaceFragment(new Fragment1(ID));//刚上来就位于广场，所以先填充
        select.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(btn1.getId()==i){
                    Toast.makeText(getApplicationContext(),"主页面",Toast.LENGTH_SHORT).show();
                    //碎片填充容器
                    replaceFragment(new Fragment1(ID));
                }
                if(btn2.getId()==i){
                    Toast.makeText(getApplicationContext(),"我的资料",Toast.LENGTH_SHORT).show();
                    replaceFragment(new Fragment2());
                }
                if(btn3.getId()==i){
                    Toast.makeText(getApplicationContext(),"更多",Toast.LENGTH_SHORT).show();
                    replaceFragment(new Fragment3());
                }
            }
        });

    }
    private void replaceFragment(Fragment fragment){    //P149页内容
        FragmentManager fragmentManager=getSupportFragmentManager();                //获取FragmentManager实例
        FragmentTransaction transaction=fragmentManager.beginTransaction();         //开启一个事务
        transaction.replace(R.id.fragmentcontain,fragment);                         //向容器内添加或替换碎片
        transaction.commit();
    }


}
