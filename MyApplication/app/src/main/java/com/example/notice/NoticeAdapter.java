package com.example.notice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.asustp.myapplication.R;

import java.util.List;

/**
 * Created by asus tp on 2018/3/29.
 */

public class NoticeAdapter extends ArrayAdapter<Notice> {
    private int resourceId;


    public NoticeAdapter(Context context, int textViewResourceId, List<Notice> objects) {//构造函数
        super(context,textViewResourceId, objects);
        resourceId=textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){//在每个子项被滚到屏幕内的时候被调用
        Notice notice=getItem(position);//获取当前的Found实例
        View view;//其实就是一个布局
        NoticeAdapter.ViewHolder viewHolder;
        if(convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder=new NoticeAdapter.ViewHolder();
            viewHolder.title=(TextView)view.findViewById(R.id.notice_title);
            viewHolder.content=(TextView)view.findViewById(R.id.notice_content);
            viewHolder.time=(TextView)view.findViewById(R.id.notice_time);

            view.setTag(viewHolder);//将ViewHolder存储在View

        }else {//convertView 为已缓存的布局，若其不为空，则直接使用
            view = convertView;
            viewHolder = (NoticeAdapter.ViewHolder) view.getTag();//重新获取V
        }
        viewHolder.title.setText(notice.getTitle());
        viewHolder.content.setText(notice.getContent());
        viewHolder.time.setText(notice.getTime());//此处不能用found.getCreatedAt(),因为此时适配器传来的是一个found类型数组，并不能再次联网查询

        return  view;
    }
    class  ViewHolder{
        TextView title;
        TextView content;
        TextView time;
    }
}
