package com.example.asustp.menurecycleviewtext;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by asus tp on 2017/12/21.
 */

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder> {

    private List<Store> mStoreList;


    public StoreAdapter(List<Store>storeList){//构造函数    将要展示的数据源传进来，并赋值给全局变量mStoreList,后续操作都在此mStoreList基础上进行
        mStoreList=storeList;
    }

    //内部类   V
    static  class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView address;

        public ViewHolder (View view){                  //构造函数view通常为RecycleView子项的最外层布局
            super(view);
            name=(TextView)view.findViewById(R.id.storename);
            address=(TextView)view.findViewById(R.id.storeaddress);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {//此方法用于创建ViewHolder实例
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }


    //用于对RecyclerView子项的数据进行赋值    会在每个子项被滚到屏幕内的时候执行
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Store store=mStoreList.get(position);        //通过position获得当前实例
        holder.name.setText(store.getName());
        holder.address.setText(store.getAddress());
    }

    @Override
    public int getItemCount() {     //用于告诉RecycleView一共多少项
        return mStoreList.size();
    }
}
