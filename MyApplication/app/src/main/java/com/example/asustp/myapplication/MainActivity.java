package com.example.asustp.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.coursetable.CourseActivity;
import com.example.gradetable.GradeActivity;
import com.example.lostandfound.LostActivity;

/*
* 主页面，fragment1的页面，默认选择
*
* */

public class MainActivity extends AppCompatActivity {
    private RadioGroup select;
    private RadioButton btn1,btn2,btn3;
    private String ID="20144138169";

    //六个功能按钮
    private ImageButton recruitBtn; //招聘按钮
    private ImageButton lostBtn;    //失物招领按钮
    private ImageButton courseBtn;  //课表按钮
    private ImageButton gradeBtn;  //课表按钮



    private LinearLayout layout1;//测试
    private LinearLayout layout2;//测试
    private LinearLayout layout3;//测试

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//去掉标题栏
       // setContentView(R.layout.text);
        setContentView(R.layout.activity_main);//测试



        select=(RadioGroup)findViewById(R.id.main_tab);
        btn1=(RadioButton)findViewById(R.id.radio_button0);
        btn2=(RadioButton)findViewById(R.id.radio_button1);
        btn3=(RadioButton)findViewById(R.id.radio_button2);
        layout1= (LinearLayout) findViewById(R.id.fragment1);
        layout2= (LinearLayout) findViewById(R.id.fragment2);
        layout3= (LinearLayout) findViewById(R.id.fragment3);

//功能按钮的注册
        recruitBtn= (ImageButton) findViewById(R.id.recruitBtn);
        lostBtn= (ImageButton) findViewById(R.id.lostandfound);
        courseBtn= (ImageButton) findViewById(R.id.course_table);
        gradeBtn= (ImageButton) findViewById(R.id.grade_table);

        Intent intent=getIntent();          //获取从LoginActivity中传来的用户ID
        ID=intent.getStringExtra("ID");
        Toast.makeText(getApplicationContext(),ID,Toast.LENGTH_SHORT).show();



        //碎片填充容器
      //  replaceFragment(new Fragment1(ID));//刚上来就位于广场，所以先填充
        select.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(btn1.getId()==i){
                    Toast.makeText(getApplicationContext(),"主页面",Toast.LENGTH_SHORT).show();
                    //碎片填充容器
                    //replaceFragment(new Fragment1(ID));
                    layout1.setVisibility(View.VISIBLE);
                    layout2.setVisibility(View.GONE);
                    layout3.setVisibility(View.GONE);

                }
                if(btn2.getId()==i){
                    Toast.makeText(getApplicationContext(),"我的资料",Toast.LENGTH_SHORT).show();
                    //replaceFragment(new Fragment2());
                    layout1.setVisibility(View.GONE);
                    layout2.setVisibility(View.VISIBLE);
                    layout3.setVisibility(View.GONE);
                }
                if(btn3.getId()==i){
                    Toast.makeText(getApplicationContext(),"更多",Toast.LENGTH_SHORT).show();
                    //replaceFragment(new Fragment3());
                    layout1.setVisibility(View.GONE);
                    layout2.setVisibility(View.GONE);
                    layout3.setVisibility(View.VISIBLE);
                }
            }
        });



        //功能按钮
        recruitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {            //招聘功能
                Intent intent=new Intent(getApplication(),Recruit.class);
                startActivity(intent);
            }
        });
        lostBtn.setOnClickListener(new View.OnClickListener() {//失物招领功能
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplication(), LostActivity.class);
                startActivity(intent);
            }
        });
        courseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplication(), CourseActivity.class);
                startActivity(intent);
            }
        });
        gradeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplication(), GradeActivity.class);
                startActivity(intent);
            }
        });


    }


    //因为碎片化的生命周期问题，所以这个不成立，弃用
    private void replaceFragment(Fragment fragment){    //P149页内容
        FragmentManager fragmentManager=getSupportFragmentManager();                //获取FragmentManager实例
        FragmentTransaction transaction=fragmentManager.beginTransaction();         //开启一个事务
        transaction.replace(R.id.fragmentcontain,fragment);                         //向容器内添加或替换碎片
        transaction.commit();
    }


}
