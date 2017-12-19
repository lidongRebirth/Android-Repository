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


    private ImageButton recruitBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle saveInstanceState){
        View view=inflater.inflate(R.layout.fragment1,container,false);

        recruitBtn=view.findViewById(R.id.recruitBtn);
        recruitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),Recruit.class);
                startActivity(intent);
            }
        });




        return view;
    }
}
