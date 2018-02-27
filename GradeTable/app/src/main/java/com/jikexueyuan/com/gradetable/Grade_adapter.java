package com.jikexueyuan.com.gradetable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by asus tp on 2018/2/27.
 */

public class Grade_adapter extends ArrayAdapter<select_course> {
    private int resourceId;

    public Grade_adapter(Context context, int textViewResourceId, List<select_course> objects) {//构造函数
        super(context,textViewResourceId, objects);
        resourceId=textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){//在每个子项被滚到屏幕内的时候被调用
        select_course course=getItem(position);//获取当前的menu实例
        View view;//其实就是一个布局
        ViewHolder viewHolder;
        if(convertView==null){
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder=new  ViewHolder();
            viewHolder.courseName=(TextView)view.findViewById(R.id.name);
            viewHolder.grade=(TextView)view.findViewById(R.id.grade);
            view.setTag(viewHolder);//将ViewHolder存储在View

        }else {//convertView 为已缓存的布局，若其不为空，则直接使用
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();//重新获取V
        }
        viewHolder.courseName.setText(course.getCourse_name());
        viewHolder.grade.setText(course.getGrade().toString());//此处没有toString导致出错，还找不到原因很长时间
        return  view;
    }
    class  ViewHolder{
        TextView courseName;
        TextView grade;
    }
}
