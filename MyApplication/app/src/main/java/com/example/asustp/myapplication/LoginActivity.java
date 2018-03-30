package com.example.asustp.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends Activity {

    private EditText userName,password;
    private Button login;
    private CheckBox rem_pw, auto_login;//记住密码   自动登录
    private SharedPreferences sp;       //用于在手机内存入用户的信息
    private String userNameValue,passwordValue;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //Bmob SDK的初始化
        Bmob.initialize(this, "12a244a082bdc4511edeaf7f98a79c56");//Bmob初始化

        userName=(EditText)findViewById(R.id.userId) ;
        password=(EditText)findViewById(R.id.pass);
        login=(Button)findViewById(R.id.loginBtn);
        rem_pw=(CheckBox)findViewById(R.id.checkBox1);
        auto_login=(CheckBox)findViewById(R.id.checkBox2);
        sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);//用来在手机本地存储登录信息




        //判断记住密码多选框的状态
        if(sp.getBoolean("ISCHECK", false))//刚打开是没有选中记住密码       如果为记住密码状态，直接将用户名和密码进行填充
        {
            //设置默认是记录密码状态
            rem_pw.setChecked(true);

            userNameValue=sp.getString("USER_NAME", "");    //因为是记住密码，所以先从本地获取帐号信息
            passwordValue=sp.getString("PASSWORD", "");

            userName.setText(userNameValue);
            password.setText(passwordValue);

            //判断自动登陆多选框状态
            if(sp.getBoolean("AUTO_ISCHECK", false))    //从sp中获取自动登录框是否被选择，未被选择则选用第二个参数true作为他的值
            {
                //设置默认是自动登录状态               为true直接登录，不需要通过点击按钮登录
                auto_login.setChecked(true);

                BmobUser bu2 = new BmobUser();
                bu2.setUsername(userNameValue);
                bu2.setPassword(passwordValue);
                bu2.login(new SaveListener<BmobUser>() {
                    @Override
                    public void done(BmobUser bmobUser, BmobException e) {
                        if(e==null){
                            Toast.makeText(getApplicationContext(),"登录成功",Toast.LENGTH_SHORT).show();
                            //跳转界面
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            intent.putExtra("ID",userNameValue);
                            intent.putExtra("objectID",bmobUser.getObjectId());
                            startActivity(intent);

                        }else{
                            Toast.makeText(getApplicationContext(),"登录失败:"+e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
        login.setOnClickListener(new  OnClickListener() {
            @Override
            public void onClick(View view) {        //登录按钮点击事件

//Bmob提供了一个专门的用户类——BmobUser来自动处理用户账户管理所需的功能。
//                Bmob提供了一个专门的用户类——BmobUser来自动处理用户账户管理所需的功能。

                userNameValue=userName.getText().toString();    //不是自动登录 则从文本框获取数据
                passwordValue=password.getText().toString();

                BmobUser bu2 = new BmobUser();
                bu2.setUsername(userNameValue);
                bu2.setPassword(passwordValue);
                bu2.login(new SaveListener<BmobUser>() {
                    @Override
                    public void done(BmobUser bmobUser, BmobException e) {
                        if(e==null){
                            Toast.makeText(getApplicationContext(),"登录成功",Toast.LENGTH_SHORT).show();
                            //跳转界面
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            intent.putExtra("ID",userNameValue);
                            intent.putExtra("objectID",bmobUser.getObjectId());
                            startActivity(intent);
                            //登录成功和记住密码框为选中状态才保存用户信息
                            if (rem_pw.isChecked()) {
                                //记住用户名、密码、
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString("USER_NAME", userNameValue);
                                editor.putString("PASSWORD", passwordValue);
                                editor.commit();
                            }
                        }else{
                            Toast.makeText(getApplicationContext(),"登录失败:"+e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


        //监听记住密码多选框按钮事件
        rem_pw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (rem_pw.isChecked()) {
//                    Toast.makeText(getApplicationContext(),"已选中记住用户名密码",Toast.LENGTH_SHORT).show();       //测试成功
//                    Log.d("text_button", "记住密码已选中: ");
                    sp.edit().putBoolean("ISCHECK", true).commit();

                }else {
//                    Toast.makeText(getApplicationContext(),"未选中记住用户名密码",Toast.LENGTH_SHORT).show();       //测试成功
//                    Log.d("text_button", "记住密码没有选中: ");
                    sp.edit().putBoolean("ISCHECK", false).commit();

                }

            }
        });

        //监听自动登录多选框事件
        auto_login.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (auto_login.isChecked()) {
//                    Toast.makeText(getApplicationContext(),"自动登录已选中",Toast.LENGTH_SHORT).show();
//                    System.out.println("自动登录已选中");
                    sp.edit().putBoolean("AUTO_ISCHECK", true).commit();

                } else {
//                    Toast.makeText(getApplicationContext(),"自动登录没有选中",Toast.LENGTH_SHORT).show();
//                    System.out.println("自动登录没有选中");
                    sp.edit().putBoolean("AUTO_ISCHECK", false).commit();
                }
            }
        });

    }
}
