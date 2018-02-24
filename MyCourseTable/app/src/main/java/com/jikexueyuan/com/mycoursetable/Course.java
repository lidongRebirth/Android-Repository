package com.jikexueyuan.com.mycoursetable;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobDate;

/**
 * Created by asus tp on 2018/2/24.
 */

public class Course extends BmobObject {
    private Integer course_id;
    private BmobDate start_time;
    private BmobDate end_time;
    private String course_name;

    public Integer getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Integer course_id) {
        this.course_id = course_id;
    }

    public BmobDate getStart_time() {
        return start_time;
    }

    public void setStart_time(BmobDate start_time) {
        this.start_time = start_time;
    }

    public BmobDate getEnd_time() {
        return end_time;
    }

    public void setEnd_time(BmobDate end_time) {
        this.end_time = end_time;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }






}
