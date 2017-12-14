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

public class LoginActivity extends Activity {

    private EditText userName,password;
    private Button login;
    private CheckBox rem_pw, auto_login;
    private SharedPreferences sp;
    private String userNameValue,passwordValue;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        userName=(EditText)findViewById(R.id.userId) ;
        password=(EditText)findViewById(R.id.pass);
        login=(Button)findViewById(R.id.loginBtn);
        rem_pw=(CheckBox)findViewById(R.id.checkBox1);
        auto_login=(CheckBox)findViewById(R.id.checkBox2);
        sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        //判断记住密码多选框的状态
        if(sp.getBoolean("ISCHECK", false))//刚打开是没有选中记住密码
        {
            //设置默认是记录密码状态
            rem_pw.setChecked(true);
            userName.setText(sp.getString("USER_NAME", ""));
            password.setText(sp.getString("PASSWORD", ""));
            //判断自动登陆多选框状态
            if(sp.getBoolean("AUTO_ISCHECK", false))
            {
                //设置默认是自动登录状态
                auto_login.setChecked(true);
                //跳转界面
                Intent intent = new Intent(LoginActivity.this,Activity2.class);
                startActivity(intent);

            }
        }
        login.setOnClickListener(new  OnClickListener() {
            @Override
            public void onClick(View view) {
                userNameValue = userName.getText().toString();
                passwordValue = password.getText().toString();
                if (userNameValue.equals("root") && passwordValue.equals("123")) {
                    Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();

                    //登录成功和记住密码框为选中状态才保存用户信息
                    if (rem_pw.isChecked()) {
                     //记住用户名、密码、
                         SharedPreferences.Editor editor = sp.edit();
                         editor.putString("USER_NAME", userNameValue);
                         editor.putString("PASSWORD", passwordValue);
                         editor.commit();
                     }
                    Intent intent = new Intent(LoginActivity.this, Activity2.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "登录失败", Toast.LENGTH_SHORT).show();
                }
//                if(str1.equals("root"))
//                {
//                    if(str2.equals("root"))
//                    {
//                        Toast.makeText(getApplicationContext(),"登录成功",Toast.LENGTH_SHORT).show();
//                        Intent intent=new Intent(LoginActivity.this,Activity2.class);
//                        startActivity(intent);
//                    }
//                    else {
//                        Toast.makeText(getApplicationContext(),"密码错误",Toast.LENGTH_SHORT).show();
//                    }
//                }
//                else {
//                    Toast.makeText(getApplicationContext(),"用户名不存在",Toast.LENGTH_SHORT).show();
//                }
            }
        });

        //监听记住密码多选框按钮事件
        rem_pw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (rem_pw.isChecked()) {
                    Toast.makeText(getApplicationContext(),"已选中记住用户名密码",Toast.LENGTH_SHORT);
//                    System.out.println("记住密码已选中");
                    sp.edit().putBoolean("ISCHECK", true).commit();

                }else {
                    Toast.makeText(getApplicationContext(),"未选中记住用户名密码",Toast.LENGTH_SHORT);
//                    System.out.println("记住密码没有选中");
                    sp.edit().putBoolean("ISCHECK", false).commit();

                }

            }
        });

        //监听自动登录多选框事件
        auto_login.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (auto_login.isChecked()) {
                    Toast.makeText(getApplicationContext(),"自动登录已选中",Toast.LENGTH_SHORT);
//                    System.out.println("自动登录已选中");
                    sp.edit().putBoolean("AUTO_ISCHECK", true).commit();

                } else {
                    Toast.makeText(getApplicationContext(),"自动登录没有选中",Toast.LENGTH_SHORT);
//                    System.out.println("自动登录没有选中");
                    sp.edit().putBoolean("AUTO_ISCHECK", false).commit();
                }
            }
        });

    }
}
