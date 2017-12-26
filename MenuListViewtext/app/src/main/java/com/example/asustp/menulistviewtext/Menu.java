package com.example.asustp.menulistviewtext;

/**
 * Created by asus tp on 2017/12/25.
 */

public class Menu {
    private String menuName;
    private String price;
    public Menu(String name,String price){
        this.menuName=name;
        this.price=price;
    }
    public String getMenuName(){
        return  menuName;
    }
    public String getPrice(){
        return price;
    }


}
