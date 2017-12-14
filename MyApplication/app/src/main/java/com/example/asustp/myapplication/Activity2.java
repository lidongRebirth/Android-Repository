package com.example.asustp.myapplication;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {
    RadioGroup select;
    RadioButton btn1,btn2,btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        select=(RadioGroup)findViewById(R.id.main_tab);
        btn1=(RadioButton)findViewById(R.id.radio_button0);
        btn2=(RadioButton)findViewById(R.id.radio_button1);
        btn3=(RadioButton)findViewById(R.id.radio_button2);
        //碎片填充容器
        replaceFragment(new Fragment1());//刚上来就位于广场，所以先填充
        select.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(btn1.getId()==i){
                    Toast.makeText(getApplicationContext(),"主页面",Toast.LENGTH_SHORT).show();
                    //碎片填充容器
                    replaceFragment(new Fragment1());
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
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentcontain,fragment);
        transaction.commit();
    }
}
