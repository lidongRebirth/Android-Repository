package com.example.asustp.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by asus tp on 2017/12/14.
 */

public class Fragment2 extends Fragment {
    private SharedPreferences sp;
    private TextView xuehao;
    private EditText password;
    private Button passbtn,idbtn;
    private ImageButton showPassword;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle saveInstanceState){
        View view=inflater.inflate(R.layout.fragment2,container,false);

        sp = getActivity().getSharedPreferences("userInfo", getActivity().MODE_PRIVATE);//获取到sharePreferences

        xuehao=view.findViewById(R.id.xuehao);
        password=view.findViewById(R.id.password);
        passbtn=view.findViewById(R.id.changepass);
        idbtn=view.findViewById(R.id.changeid);
        showPassword=view.findViewById(R.id.showpassword);


        xuehao.setText(sp.getString("USER_NAME",""));//从SharePerference中获取学号和密码
        password.setText(sp.getString("PASSWORD",""));

//        showPassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(view.getContext(),"测试",Toast.LENGTH_SHORT).show();;
//                      password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());//EditText动态显示
//            }
//        });
        showPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                   case MotionEvent.ACTION_UP://按住事件后执行代码的区域
                   {
                       password.setTransformationMethod(PasswordTransformationMethod.getInstance());//EditText动态呈现密文

                       break;
                   }
                   case MotionEvent.ACTION_DOWN://松开事件后执行的代码
                   {
                       password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());//EditText动态显示

                       break;
                   }
                    default:
                        break;
                }
            return true;
            }
        });

//       idbtn.setOnKeyListener(new View.OnKeyListener() {
//           @Override
//           public boolean onKey(View view, int i, KeyEvent keyEvent) {
//               switch (keyEvent.getAction()){
//                   case KeyEvent.ACTION_UP://按住事件后执行代码的区域
//                   {
//                       password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());//EditText动态显示
//                   }
//                   case KeyEvent.ACTION_DOWN://松开事件后执行的代码
//                   {
//                        password.setTransformationMethod(PasswordTransformationMethod.getInstance());//EditText动态呈现密文
//                   }
//               }
//               return false;
//           }
//       });

//        password.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {//长按监听事件来显示密码
//                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//                return false;
//            }
//        });


        passbtn.setOnClickListener(new View.OnClickListener() {//修改密码按钮的点击事件
            @Override
            public void onClick(View view) {
                password.setFocusableInTouchMode(true);password.setFocusable(true);password.requestFocus();//设置EditText为可编辑状态，必须三个都要写

                Toast.makeText(getContext(),sp.getString("USER_NAME",""),Toast.LENGTH_SHORT).show();//测试时少写show()所以显示不出来
                Toast.makeText(getContext(),sp.getString("PASSWORD",""),Toast.LENGTH_SHORT).show();//测试时少写show()所以显示不出来

            }
        });
        return view;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        password.setFocusable(false);
        password.setFocusableInTouchMode(false);
    }
}
