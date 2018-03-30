package com.example.menulist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.asustp.myapplication.R;

import java.util.List;

/**
 * Created by asus tp on 2017/12/25.
 */

public class MenuAdapter extends ArrayAdapter<Menu> {
    private int resourceId;
    public MenuAdapter(Context context, int textViewResourceId, List<Menu> objects) {//构造函数
        super(context,textViewResourceId, objects);
        resourceId=textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){//在每个子项被滚到屏幕内的时候被调用
        Menu menu=getItem(position);//获取当前的menu实例
        View view;//其实就是一个布局
        ViewHolder2 viewHolder;
        if(convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder=new  ViewHolder2();
            viewHolder.menuName=(TextView)view.findViewById(R.id.menutext);
            viewHolder.price=(TextView)view.findViewById(R.id.pricetext);
            view.setTag(viewHolder);//将ViewHolder存储在View

        }else {//convertView 为已缓存的布局，若其不为空，则直接使用
            view = convertView;
            viewHolder = (ViewHolder2) view.getTag();//重新获取V
        }
        viewHolder.menuName.setText(menu.getMenuName());
        viewHolder.price.setText(menu.getPrice());


        return  view;
    }
    class  ViewHolder2{
        TextView menuName;
        TextView price;
    }




}