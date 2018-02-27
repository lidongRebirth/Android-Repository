package com.jikexueyuan.com.gradetable;

import cn.bmob.v3.BmobObject;

/**
 * Created by asus tp on 2018/2/27.
 */

public class select_course extends BmobObject {
    private String ID;
    private String course_name;
    private Integer grade;

    public select_course(String id,String course_name,Integer grade){
        this.ID=id;
        this.course_name=course_name;
        this.grade=grade;
    }




    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }




}
