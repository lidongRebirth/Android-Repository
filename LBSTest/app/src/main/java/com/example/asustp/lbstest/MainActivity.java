package com.example.asustp.lbstest;

import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.duration;

public class MainActivity extends AppCompatActivity {

    public  LocationClient mLocationClient;
    private TextView positionText;
    private MapView mapView;    //呈现地图
    private BaiduMap baiduMap;//地图总控制类
    private boolean isFirstLocate=true;//防止多次调用animateMapStatus()方法



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocationClient=new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());//注册定位监听器
//        //新加
//        LocationClientOption option = new LocationClientOption();
//        option.setOpenGps(true);// 打开gps
//        option.setCoorType("bd09ll"); // 设置坐标类型
//        option.setScanSpan(1000);
//        option.setAddrType("all");
//        mLocationClient.setLocOption(option);
//        mLocationClient.start();
        SDKInitializer.initialize(getApplicationContext());//初始化操作一定要在setContendView之前调用才不会出错
        setContentView(R.layout.activity_main);

        mapView=(MapView) findViewById(R.id.bmapView);
        positionText=(TextView)findViewById(R.id.position_text_view);

        baiduMap=mapView.getMap();//获取到BaiduMap实例
        baiduMap.setMyLocationEnabled(true);//获取自己的位置 让我显示在地图上

        List<String> permissionList=new ArrayList<>();//进行运行时权限 三个权限的授权
        if(ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if(ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(android.Manifest.permission.READ_PHONE_STATE);
        }
        if(ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if(!permissionList.isEmpty()){
            String []permissions=permissionList.toArray(new String[permissionList.size()]);//将List集合转换为数组，再调用requestPermissions方法一次性申请权限
            ActivityCompat.requestPermissions(MainActivity.this,permissions,1);
        }else{
            requestLocation();//自定义方法   开始地理定位
        }
    }

    //将地图移动自己的位置上来
    private void navigateTo(BDLocation location) {
        if (isFirstLocate) {//源代码此处有时会导致再次打开应用不移到此位置
//            书上的
            double mLatitude;
            double mLongtitude;
            mLatitude=location.getLatitude();
            mLongtitude=location.getLongitude();
            LatLng ll = new LatLng(mLatitude,mLongtitude);//用来存放经纬度 将BDlocation对象中的地理位置信息取出封装在LatLng中
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
            baiduMap.animateMapStatus(update);
//            baiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(16f));
//            update = MapStatusUpdateFactory.zoomTo(16f);//设置缩放级别
//            baiduMap.animateMapStatus(update);
            isFirstLocate = false;
        }

        //书中此处正确
        //让我显示在地图中  MyLocationData.Builder用来存放当前自己的位置信息
        MyLocationData.Builder locationBuilder = new MyLocationData.Builder();
        locationBuilder.latitude(location.getLatitude());
        locationBuilder.longitude(location.getLongitude());
        MyLocationData locationData = locationBuilder.build();//build方法用来生成一个MyLocationData实例
        baiduMap.setMyLocationData(locationData);//将其实例传入就可已在地图上显示自己
        //开发文档罗盘显示也正确
        // 构造定位数据
//        MyLocationData locData = new MyLocationData.Builder()
//                .accuracy(location.getRadius())
//                // 此处设置开发者获取到的方向信息，顺时针0-360
//                .direction(100).latitude(location.getLatitude())
//                .longitude(location.getLongitude()).build();
//
//        // 设置定位数据
//        baiduMap.setMyLocationData(locData);

    }

    private void requestLocation(){//开始地理位置定位
        initLocation();         //时间间隔函数
        mLocationClient.start();//定位结果会回调到监听器中
    }
    private void initLocation(){//设置更新间隔
        LocationClientOption option=new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选 默认高精度 设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");      //设置此才会在自己的位置中来，不会去北京
        option.setScanSpan(5000);       //每隔5秒更新当前位置
        option.setIsNeedAddress(true);  //需要获取当前位置具体的信息  所在省市等
        mLocationClient.setLocOption(option);
        baiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(16f));

    }
    //以下三个方法保证资源能即使释放
    @Override
    protected void onResume(){
        super.onResume();
        mapView.onResume();
    }
    @Override
    protected void onPause(){
        super.onPause();
        mapView.onResume();

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        mLocationClient.stop();         //停止定位，防止后台消耗电量
        mapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);

    }


//获取权限申请的结果  权限不够则直接退出应用
    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions, int []grantResults){
        switch (requestCode){
            case 1:
                if (grantResults.length>0){
                    for(int result:grantResults){
                        if (result!=PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(this,"必须同意所有权限才能使用本程序",Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                }else{
                    Toast.makeText(this,"发生未知错误",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }


    public class MyLocationListener implements BDLocationListener  {

        @Override
        public void onReceiveLocation( BDLocation  location) {
            if (location.getLocType() == BDLocation.TypeGpsLocation || location.getLocType() == BDLocation.TypeNetWorkLocation) {
                navigateTo(location);//当定位到当前位置时，将BDLocation对象传给navigateTo方法，用于将地图移动到此处
            }
//            StringBuffer currentPosition = new StringBuffer();
//            currentPosition.append("纬度：").append(location.getLatitude()).append("\n");
//            currentPosition.append("经线：").append(location.getLongitude()).append("\n");
//            currentPosition.append("国家：").append(location.getCountry()).append("\n");
//            currentPosition.append("省：").append(location.getProvince()).append("\n");
//            currentPosition.append("市：").append(location.getCity()).append("\n");
//            currentPosition.append("区：").append(location.getDirection()).append("\n");
//            currentPosition.append("街道：").append(location.getStreet()).append("\n");
//            currentPosition.append("定位方式：");
//            if (location.getLocType() == BDLocation.TypeGpsLocation) {
//                currentPosition.append("GPS");
//            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
//                currentPosition.append("网络");
//            }
//            positionText.setText(currentPosition);
//            runOnUiThread(new Runnable() {//书中这样写是否会出错？需要设置为final---待测试
//                @Override
//                public void run() {
//                    StringBuffer currentPosition = new StringBuffer();
//                    currentPosition.append("纬度：").append(location.getLatitude()).append("\n");
//                    currentPosition.append("经线：").append(location.getLongitude()).append("\n");
//                    currentPosition.append("国家：").append(location.getCountry()).append("\n");
//                    currentPosition.append("省：").append(location.getProvince()).append("\n");
//                    currentPosition.append("市：").append(location.getCity()).append("\n");
//                    currentPosition.append("区：").append(location.getDirection()).append("\n");
//                    currentPosition.append("街道：").append(location.getStreet()).append("\n");
//
//                    currentPosition.append("定位方式：");
//                    if (location.getLocType() == BDLocation.TypeGpsLocation) {
//                        currentPosition.append("GPS");
//                    } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
//                        currentPosition.append("网络");
//                    }
//                    positionText.setText(currentPosition);
//                }
//            });

        }
    }


}
