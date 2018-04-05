package com.example.gradetable;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.asustp.myapplication.R;
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
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
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

        //变换颜色
        int rand = position % 7;
        switch( rand ) {
            case 0:
                view.setBackground(getContext().getResources().getDrawable(R.drawable.two_grid_item_bg));
                break;
            case 1:
                view.setBackground(getContext().getResources().getDrawable(R.drawable.two_bg_12));
                break;
            case 2:
                view.setBackground(getContext().getResources().getDrawable(R.drawable.two_bg_13));
                break;
            case 3:
                view.setBackground(getContext().getResources().getDrawable(R.drawable.two_bg_14));
                break;
            case 4:
                view.setBackground(getContext().getResources().getDrawable(R.drawable.two_bg_15));
                break;
            case 5:
                view.setBackground(getContext().getResources().getDrawable(R.drawable.two_bg_16));
                break;
            case 6:
                view.setBackground(getContext().getResources().getDrawable(R.drawable.two_bg_17));
                break;
            case 7:
                view.setBackground(getContext().getResources().getDrawable(R.drawable.two_bg_18));
                break;
        }




        return  view;
    }
    class  ViewHolder{
        TextView courseName;
        TextView grade;
    }
}
