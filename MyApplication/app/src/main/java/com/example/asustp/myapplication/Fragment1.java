package com.example.asustp.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Created by asus tp on 2017/12/14.
 */

public class Fragment1 extends Fragment {


    private ImageButton recruitBtn;//招聘按钮
    private ImageButton lostBtn;






    private String ID="未变";         //从MainActivity传过来学号


    public Fragment1( String  strID) {          //目的是从MainActivity中获取到数据
        super();
        ID=strID;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle saveInstanceState){
        //View view=inflater.inflate(R.layout.fragment_text,container,false);
        View view=inflater.inflate(R.layout.fragment1,container,false);//测试新界面成功   加载布局

        recruitBtn=view.findViewById(R.id.recruitBtn);
        lostBtn=view.findViewById(R.id.lostandfound);





        recruitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {            //招聘功能
                Intent intent=new Intent(getActivity(),Recruit.class);
                startActivity(intent);
            }
        });
//        Toast.makeText(getContext(),"1:"+ID,Toast.LENGTH_SHORT).show();//测试是否从Activity中传来了数据

        lostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        return view;
    }
}
