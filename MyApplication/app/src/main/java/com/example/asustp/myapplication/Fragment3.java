package com.example.asustp.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
/**
 * Created by asus tp     已弃用
 */

public class Fragment3 extends Fragment {

    private Button btn;
    private SharedPreferences sp;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment3,container,false);
//
//
//        sp = getActivity().getSharedPreferences("userInfo", getActivity().MODE_PRIVATE);//获取到sharePreferences
//        btn=view.findViewById(R.id.back);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
////                SharedPreferences.Editor editor = sp.edit();//用于取消自动登录的选项
////                editor.putBoolean("AUTO_ISCHECK", false);
////                editor.commit();//一定要提交
////                Intent intent=new Intent(getActivity(),LoginActivity.class);
////                startActivity(intent);
//            }
//
//        });


        return view;
    }

}
