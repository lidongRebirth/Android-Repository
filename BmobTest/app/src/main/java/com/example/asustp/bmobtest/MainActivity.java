package com.example.asustp.bmobtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.bmob.v3.Bmob;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Bmob SDK的初始化
        Bmob.initialize(this,"38466a53c086c6644e6c4eac9f55247e");



    }
}
