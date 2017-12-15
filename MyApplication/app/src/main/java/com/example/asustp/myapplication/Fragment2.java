package com.example.asustp.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.HideReturnsTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle saveInstanceState){
        View view=inflater.inflate(R.layout.fragment2,container,false);

        sp = getActivity().getSharedPreferences("userInfo", getActivity().MODE_PRIVATE);//获取到sharePreferences

        xuehao=view.findViewById(R.id.xuehao);
        password=view.findViewById(R.id.password);
        passbtn=view.findViewById(R.id.changepass);
        idbtn=view.findViewById(R.id.changeid);

        xuehao.setText(sp.getString("USER_NAME",""));//从SharePerference中获取学号和密码
        password.setText(sp.getString("PASSWORD",""));

        password.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {//长按监听事件来显示密码
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                return false;
            }
        });


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
