package com.example.asustp.menulistviewtext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus tp on 2018/1/2.
 */

public class test {
    public List<Store> send(){
        final List<Store> store = new ArrayList<Store>();
        new Thread(new Runnable() {
            @Override
            public void run(){
                Store ss = new Store();
                store.add(ss);
            }
        }).start();
        return store;
    }
}
