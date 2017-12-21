package com.example.asustp.menulistviewtext;

/**
 * Created by asus tp on 2017/12/21.
 */
//主页的商店类
public class Store {
    private String name;
    private String address;
    public Store(String name,String address){
        this.name=name;
        this.address=address;
    }
    public String getName(){
        return name;
    }
    public String getAddress(){
        return address;
    }


}
