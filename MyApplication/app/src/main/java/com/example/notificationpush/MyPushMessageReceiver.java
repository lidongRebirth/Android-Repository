package com.example.notificationpush;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.asustp.myapplication.R;

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

                Intent intent1=new Intent(context,NotificationActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("text",content);
                intent1.putExtras(bundle);
                PendingIntent pi=PendingIntent.getActivity(context,0, intent1,PendingIntent.FLAG_UPDATE_CURRENT);//第四个参数写为这才能传过去值
                NotificationManager manager=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                Notification notification=new NotificationCompat.Builder(context)
                        .setContentTitle(content)
//                        .setContentText(content)
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.drawable.home)
                        .setContentIntent(pi)
                        .setVibrate(new long[]{0,1000,1000,1000})//震动
                        .setLights(Color.BLUE,1000,1000)        //颜色 1秒亮1秒暗
                       // .setStyle(new NotificationCompat.BigTextStyle().bigText(content))//手机不同可能不显示
//                        .setPriority(NotificationCompat.PRIORITY_MAX)         //无权操作该应用
                        .setAutoCancel(true)//点击后即取消
                        .build();
                manager.notify(100,notification);
            } catch (JSONException e) {
                e.printStackTrace();
            }







        }
    }
}
