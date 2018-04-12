package com.example.lostandfound;

import cn.bmob.v3.BmobObject;

/**
 * Created by asus tp on 2018/2/19.
 * 招领表
 */

public class Found extends BmobObject{


    private String objectID;//因为update数据只能根据objectID来进行修改，所以此处加上是为了以后删除时使用
    private String time;
    private String ID;
    private String title;//标题
    private String describe;//描述
    private String phone;//联系手机
    public Found(){}

    public Found(String ID,String title,String describe,String phone){
        this.ID=ID;
        this.title=title;
        this.describe=describe;
        this.phone=phone;
    }
    public Found(String ID,String title,String describe,String phone,String time1,String objectID1){
        this.ID=ID;
        this.title=title;
        this.describe=describe;
        this.phone=phone;
        this.time=time1;
        this.objectID=objectID1;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String gettheObjectId() {
        return objectID;
    }
}
