package com.jikexueyuan.com.mycoursetable;

/**
 * Created by asus tp on 2018/2/12.
 */

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by wan on 2016/10/13.
 * gridView的适配器
 */
public class MyAdapter extends BaseAdapter {

    private Context mContext;
    //保存内容的内部数组
    private List<String> content;

    public MyAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.content = list;
    }

    public int getCount() {
        return content.size();
    }

    public Object getItem(int position) {
        return content.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public View getView(int position, View convertView, ViewGroup parent) {//通过缓存convertView,这种利用缓存contentView的方式可以判断如果缓存中不存在View才创建View，如果已经存在可以利用缓存中的View，提升了性能
        if( convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.two_grib_item, null);
        }
        TextView textView = (TextView)convertView.findViewById(R.id.text);
        //如果有课,那么添加数据
        if( !getItem(position).equals("")) {
            textView.setText((String)getItem(position));
            textView.setTextColor(Color.WHITE);
            //变换颜色
            int rand = position % 7;
            switch( rand ) {
                case 0:
                    textView.setBackground(mContext.getResources().getDrawable(R.drawable.two_grid_item_bg));//后面放的为背景颜色的设置
                    break;
                case 1:
                    textView.setBackground(mContext.getResources().getDrawable(R.drawable.two_bg_12));
                    break;
                case 2:
                    textView.setBackground(mContext.getResources().getDrawable(R.drawable.two_bg_13));
                    break;
                case 3:
                    textView.setBackground(mContext.getResources().getDrawable(R.drawable.two_bg_14));
                    break;
                case 4:
                    textView.setBackground(mContext.getResources().getDrawable(R.drawable.two_bg_15));
                    break;
                case 5:
                    textView.setBackground(mContext.getResources().getDrawable(R.drawable.two_bg_16));
                    break;
                case 6:
                    textView.setBackground(mContext.getResources().getDrawable(R.drawable.two_bg_17));
                    break;
                case 7:
                    textView.setBackground(mContext.getResources().getDrawable(R.drawable.two_bg_18));
                    break;
            }
        }
        return convertView;
    }

}