package com.example.notice;

import cn.bmob.v3.BmobObject;

/**
 * Created by asus tp on 2018/3/29.
 */

public class Notice extends BmobObject {
    private String category;
    private String title;
    private String content;
    private String time;


    public Notice(String title,String content,String time){
        this.title=title;
        this.content=content;
        this.time=time;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
