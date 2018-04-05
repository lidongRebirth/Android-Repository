package com.jikexueyuan.com.pushactivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import cn.bmob.push.PushConstants;

/**
 * Created by asus tp on 2018/3/19.
 */
//TODO 集成：1.3、创建自定义的推送消息接收器，并在清单文件中注册
public class MyPushMessageReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        if(intent.getAction().equals(PushConstants.ACTION_MESSAGE)){
            Log.d("bmob", "客户端收到推送内容："+intent.getStringExtra("msg"));


            String data =intent.getStringExtra("msg");
            try {
                JSONObject  a=new JSONObject(data);
                String content =a.getString("alert");
                NotificationManager manager=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                Notification notification=new NotificationCompat.Builder(context)
                        .setContentTitle(content)
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.drawable.hbu)
                        .build();
                manager.notify(1,notification);
            } catch (JSONException e) {
                e.printStackTrace();
            }







        }
    }
}
