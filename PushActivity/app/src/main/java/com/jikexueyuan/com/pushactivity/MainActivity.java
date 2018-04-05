package com.jikexueyuan.com.pushactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;
import cn.bmob.push.BmobPush;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.BmobInstallationManager;
import cn.bmob.v3.InstallationListener;
import cn.bmob.v3.exception.BmobException;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //12a244a082bdc4511edeaf7f98a79c56
        //TODO 集成：1.4、初始化数据服务SDK、初始化设备信息并启动推送服务
// 初始化BmobSDK
        Bmob.initialize(this, "12a244a082bdc4511edeaf7f98a79c56");
// 使用推送服务时的初始化操作
        BmobInstallationManager.getInstance().initialize(new InstallationListener<BmobInstallation>() {
            @Override
            public void done(BmobInstallation bmobInstallation, BmobException e) {
                if (e == null) {
                    Log.i("打印:",bmobInstallation.getObjectId() + "-" + bmobInstallation.getInstallationId());
                    Toast.makeText(getApplication(),"ObjectId"+bmobInstallation.getObjectId() + "InstallationId" + bmobInstallation.getInstallationId(),Toast.LENGTH_SHORT).show();

                } else {
                    Log.e("打印:",e.getMessage());
                    Toast.makeText(getApplication(),"错误"+e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
// 启动推送服务
        BmobPush.startWork(this);
    }
}
