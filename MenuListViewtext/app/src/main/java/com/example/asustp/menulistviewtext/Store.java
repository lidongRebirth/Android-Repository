package com.example.asustp.menulistviewtext;

/**
 * Created by asus tp on 2017/12/21.
 */
//主页的商店类
public class Store {
    private String name;
    private String address;
    private  String tel;
    public Store(){

    }

    public Store(String name,String address ,String tel){
        this.name=name;
        this.address=address;
        this.tel=tel;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setAddress(String address){
        this.address=address;
    }
    public void setTel(String tel){
        this.tel=tel;
    }




    public String getName(){
        return name;
    }
    public String getAddress(){
        return address;
    }
    public String getTel(){
        return tel;
    }



}
