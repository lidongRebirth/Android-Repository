package com.example.bannerview;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by lucky on 2017/3/20.
 */
public class BannerAdapter extends PagerAdapter {

    private List<View> viewList;
    private int size;   //共有几个view页面
    private final int cacheCount = 3;
    /*
    PagerAdapter一样，只会缓存当前的Fragment以及左边一个，右边 一个，即总共会缓存3个Fragment而已，假如有1，2，3，4四个页面：
    处于1页面：缓存1，2
    处于2页面：缓存1，2，3
    处于3页面：销毁1页面，缓存2，3，4
    处于4页面：销毁2页面，缓存3，4
    更多页面的情况，依次类推
    */

    public BannerAdapter(List<View> viewList) {
        this.viewList = viewList;
        size = viewList.size();
    }
    /*
    * 除一个给定位置的页面。适配器有责任从容器中删除这个视图。 这是为了确保在finishUpdate(viewGroup)返回时视图能够被移除
    *
    * */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (viewList.size() > cacheCount){
            container.removeView(viewList.get(position%size));//？？？是为了循环显示？？？
        }
    }


    /*
    * ①将给定位置的view添加到ViewGroup(容器)中,创建并显示出来
    * ②返回一个代表新增页面的Object(key),通常都是直接返回view本身就可以了,当然你也可以 自定义自己的key,但是key和每个view要一一对应的关系
    * */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        /*
        * 避免一个视图多个父控件的解决方案：获取该视图的父控件，判断该视图的父控件是否为空，
        * 若不为空说明添加视图时会出现一个视图多个父控件异常，所以在这里我们要用父控件remove该视图，然后再添加到viewpager
        * */
        //获取需要加载视图的父控件
        ViewGroup parent = (ViewGroup) viewList.get(position%size).getParent();
        //判断父控件是否为空，若为空说明已被ViewPager加载，那么我们要移除该视图，这样就能保证一个视图只有一个父控件
        if (parent != null) {
            parent.removeView(viewList.get(position%size));
        }
        container.addView(viewList.get(position%size));
        return viewList.get(position%size);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;           //设置长度为整型最大值
    }

    /*
    * 判断instantiateItem(ViewGroup, int)函数所返回来的Key与一个页面视图是否是代表的同一个视图(即它俩是否是对应的，对应的表示同一个View),通常我们直接写 return view == object!
    *
    * */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
