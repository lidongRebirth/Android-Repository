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
 * Created by asus tp on 2017/12/21.
 */

public class StoreAdapter extends ArrayAdapter <Store>{
    private int resourceId;
    public StoreAdapter( Context context,  int textViewResourceId,  List <Store>objects) {//构造函数
        super(context,textViewResourceId, objects);
        resourceId=textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){//在每个子项被滚到屏幕内的时候被调用
        Store store=getItem(position);//获取当前的store实例
        View view;//其实就是一个布局
        ViewHolder viewHolder;
        if(convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.Name=(TextView)view.findViewById(R.id.storename);
            viewHolder.Address=(TextView)view.findViewById(R.id.storeaddress);
            viewHolder.Tel=(TextView)view.findViewById(R.id.storetel);
            viewHolder.storeid=store.getStoreID();
            view.setTag(viewHolder);//将ViewHolder存储在View

        }else {//convertView 为已缓存的布局，若其不为空，则直接使用
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();//重新获取V
        }
        viewHolder.Name.setText(store.getName());
        viewHolder.Address.setText(store.getAddress());
        viewHolder.Tel.setText(store.getTel());
        viewHolder.storeid=store.getStoreID();

        return  view;
    }
    class  ViewHolder{
        TextView Name;
        TextView Address;
        TextView Tel;
        int storeid;
    }
}
