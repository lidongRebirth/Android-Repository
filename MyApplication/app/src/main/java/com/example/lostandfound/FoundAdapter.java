package com.example.lostandfound;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.asustp.myapplication.R;

import java.util.List;
/**
 * Created by asus tp on 2018/2/20.
 */

public class FoundAdapter extends ArrayAdapter<Found> {


    private int resourceId;


    public FoundAdapter( Context context,  int textViewResourceId,  List <Found>objects) {//构造函数
        super(context,textViewResourceId, objects);
        resourceId=textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){//在每个子项被滚到屏幕内的时候被调用
        Found found=getItem(position);//获取当前的Found实例
        View view;//其实就是一个布局
        ViewHolder viewHolder;
        if(convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.title=(TextView)view.findViewById(R.id.tv_title);
            viewHolder.describe=(TextView)view.findViewById(R.id.tv_describe);
            viewHolder.phone=(TextView)view.findViewById(R.id.tv_phone);
            viewHolder.time=(TextView)view.findViewById(R.id.tv_time);
            view.setTag(viewHolder);//将ViewHolder存储在View

        }else {//convertView 为已缓存的布局，若其不为空，则直接使用
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();//重新获取V
        }
        viewHolder.title.setText(found.getTitle());
        viewHolder.describe.setText(found.getDescribe());
        viewHolder.phone.setText(found.getPhone());
        viewHolder.time.setText(found.getTime());//此处不能用found.getCreatedAt(),因为此时适配器传来的是一个found类型数组，并不能再次联网查询


        return  view;
    }
    class  ViewHolder{
        TextView title;
        TextView describe;
        TextView phone;
        TextView time;
    }
}
