package com.example.baidulbs;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.busline.BusLineResult;
import com.baidu.mapapi.search.busline.BusLineSearch;
import com.baidu.mapapi.search.busline.BusLineSearchOption;
import com.baidu.mapapi.search.busline.OnGetBusLineSearchResultListener;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.example.asustp.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class LBSActivity extends AppCompatActivity implements OnGetPoiSearchResultListener, OnGetBusLineSearchResultListener {

    public  LocationClient mLocationClient;     //定位服务的客户端
    private TextView positionText;
    private MapView mapView;
    private BaiduMap baiduMap;                  //地图总控制器
    private boolean isFirstLocate=true;
    private Button markBtn,cmarkBtn;

    //以下为公交线路的规划
    private Button mBtnPre = null; // 上一个节点
    private Button mBtnNext = null; // 下一个节点
    private int nodeIndex = -2; // 节点索引,供浏览节点时使用
    private BusLineResult route = null; // 保存驾车/步行路线数据的变量，供浏览节点时使用
    private List<String> busLineIDList = null;
    private int busLineIndex = 0;
    // 搜索相关
    private PoiSearch mSearch = null; // 搜索模块，也可去掉地图模块独立使用
    private BusLineSearch mBusLineSearch = null;
    BusLineOverlay overlay; // 公交路线绘制对象
    /////////////




    //创建OverlayOptions的集合
    List<OverlayOptions> options = new ArrayList<OverlayOptions>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocationClient=new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());//注册定位监听器
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.five_lbstext);
        Bmob.initialize(this,"12a244a082bdc4511edeaf7f98a79c56");
        getSupportActionBar().setTitle("校园地图");
        positionText= (TextView) findViewById(R.id.position_text_view);
        mapView= (MapView) findViewById(R.id.bmapView);
        baiduMap=mapView.getMap();//获取到BaiduMap实例
        baiduMap.setMyLocationEnabled(true);//设备位置在地图上显示


        //////////公交线路相关
        mBtnPre = (Button) findViewById(R.id.pre);
        mBtnNext = (Button) findViewById(R.id.next);
        mBtnPre.setVisibility(View.INVISIBLE);
        mBtnNext.setVisibility(View.INVISIBLE);
        mSearch = PoiSearch.newInstance();                  //创建POI检索实例
        mSearch.setOnGetPoiSearchResultListener(this);      //设置POI检索监听者
        mBusLineSearch = BusLineSearch.newInstance();
        mBusLineSearch.setOnGetBusLineSearchResultListener(this);
        busLineIDList = new ArrayList<String>();
        overlay = new BusLineOverlay(baiduMap);

        List<String> permissionList=new ArrayList<>();//进行运行时权限 三个权限的申请
        if(ContextCompat.checkSelfPermission(LBSActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if(ContextCompat.checkSelfPermission(LBSActivity.this, android.Manifest.permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(android.Manifest.permission.READ_PHONE_STATE);
        }
        if(ContextCompat.checkSelfPermission(LBSActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if(!permissionList.isEmpty()){
            String []permissions=permissionList.toArray(new String[permissionList.size()]);//将List集合转换为数组，再调用requestPermissions方法一次性申请权限
            ActivityCompat.requestPermissions(LBSActivity.this,permissions,1);//一次性申请list中添加的所有权限
        }else{
            requestLocation();//自定义方法   开始地理定位
        }




        baiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
               baiduMap.hideInfoWindow();//用于隐藏infoWindow窗体
            }
            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
              return false;

            }
        });
        baiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                    LayoutInflater inflater = getLayoutInflater();
                    View view = inflater.inflate(R.layout.text_infowindow, null);
                    //定义用于显示该InfoWindow的坐标点
                    final TextView agent_name,agent_content,agent_call,agent_location;

                    LinearLayout layout= view.findViewById(R.id.call_LL);
                    agent_name= view.findViewById(R.id.agent_name);
                    agent_content=view.findViewById(R.id.agent_content);
                    agent_location=view.findViewById(R.id.agent_location);
                    agent_call=view.findViewById(R.id.agent_call);
                    agent_name.setText(marker.getExtraInfo().getString("title"));
                    agent_content.setText(marker.getExtraInfo().getString("content"));
                    agent_location.setText(marker.getExtraInfo().getString("location"));
                    agent_call.setText(marker.getExtraInfo().getString("tel"));
                    layout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent telIntent=new Intent(Intent.ACTION_DIAL);
                            telIntent.setData(Uri.parse("tel:"+agent_call.getText()+""));
                            startActivity(telIntent);
                        }
                    });
                    LatLng pt = new LatLng(marker.getPosition().latitude,marker.getPosition().longitude);
                    //创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
                    InfoWindow mInfoWindow = new InfoWindow(view, pt, -47);
                    //显示InfoWindow
                    baiduMap.showInfoWindow(mInfoWindow);
                    return false;
            }

        });
        markBtn= (Button) findViewById(R.id.lbs_mark_btn);      //添加校园信息标记
        markBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMark();
            }
        });
        cmarkBtn= (Button) findViewById(R.id.lbs_cmark_btn);    //清除标记
        cmarkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baiduMap.clear();
            }
        });
    }

    private void requestLocation(){
        initLocation();
        mLocationClient.start();//定位结果会回调到监听器中
    }
    //将地图移动自己的位置上来
    private void navigateTo(BDLocation location){
        if(isFirstLocate){
            LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());       //存放经纬度
            //以下部分地理信息的存储和地图的更新是和课本不一样的地方
            MapStatus.Builder builder=new MapStatus.Builder();      //建立地图状态构造器


            builder.target(latLng).zoom(16f);
            baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            isFirstLocate=false;
        }
        MyLocationData.Builder locationBuilder=new MyLocationData.Builder();
        locationBuilder.latitude(location.getLatitude());
        locationBuilder.longitude(location.getLongitude());
        MyLocationData locationData=locationBuilder.build();//build方法用来生成一个MyLocationData实例
        baiduMap.setMyLocationData(locationData);
    }

    private void initLocation(){         //动态定位，每隔5秒刷新定位
        LocationClientOption option=new LocationClientOption();
        option.setScanSpan(5000);
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选 默认高精度 设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");      //设置此才会在自己的位置中来，才会准确
        option.setIsNeedAddress(true);      //获取详细位置信息
//        option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);//设置使用GPS定位      未成功，可能是因为在室内的缘故
        mLocationClient.setLocOption(option);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();//停止定位，防止后台一直定位消耗电量
        mapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);
//公交检索
        mSearch.destroy();
        mBusLineSearch.destroy();
    }

    //获取权限申请的结果  权限不够则直接退出应用
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
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


    public class MyLocationListener implements BDLocationListener{
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if(bdLocation.getLocType()==BDLocation.TypeGpsLocation||bdLocation.getLocType()==BDLocation.TypeNetWorkLocation){
                navigateTo(bdLocation);
            }
        }
    }

    private void addMark(){     //绘制标记点
//        //创建OverlayOptions的集合
//        List<OverlayOptions> options = new ArrayList<OverlayOptions>();
//        //设置坐标点
//        LatLng point1 = new LatLng(38.885802, 115.569806);  //C1-201    5074523
//        LatLng point2 = new LatLng(38.886238, 115.571225);  //B2-207    5073104
//        //构建Marker图标
//        BitmapDescriptor bitmap = BitmapDescriptorFactory
//                .fromResource(R.drawable.mark);
//
//        Bundle bundle1=new Bundle();
//        bundle1.putString("location","C1-201");
//        bundle1.putString("tel","5074523");
//        bundle1.putString("title","院长办公室");
//        bundle1.putString("content","内容测试");
//
//
//        Bundle bundle2=new Bundle();
//        bundle2.putString("location","B2-207");
//        bundle2.putString("tel","5073104");
//        bundle2.putString("title","学院办公室");
//        bundle2.putString("content","学院证明、档案查询复印");
//        //创建OverlayOptions属性
//        OverlayOptions option1 =  new MarkerOptions()
//                .position(point1)
//                .icon(bitmap)
//                .extraInfo(bundle1);
//        OverlayOptions option2 =  new MarkerOptions()
//                .position(point2)
//                .icon(bitmap)
//                .extraInfo(bundle2);
//        //将OverlayOptions添加到list
//        options.add(option1);
//        options.add(option2);
//        //在地图上批量添加
//        baiduMap.addOverlays(options);


        //构建Marker图标
        final BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.mark);
        BmobQuery<BMap> query=new BmobQuery<>();
        query.findObjects(new FindListener<BMap>() {
            @Override
            public void done(List<BMap> list, BmobException e) {
                if(e==null){
                    for(int i=0;i<list.size();i++){
                        LatLng point = new LatLng(Double.parseDouble(list.get(i).getLatitude()),Double.parseDouble(list.get(i).getLongitude()));  //C1-201    5074523
                        Bundle bundle=new Bundle();
                        bundle.putString("location",list.get(i).getLocation());
                        bundle.putString("tel",list.get(i).getTel());
                        bundle.putString("title",list.get(i).getTitle());
                        bundle.putString("content",list.get(i).getContent());
                        //创建OverlayOptions属性
                        OverlayOptions option =  new MarkerOptions()
                                .position(point)
                                .icon(bitmap)
                                .extraInfo(bundle);
                        options.add(option);
                     }
                    Message message=new Message();
                    message.what=0;
                    message.obj=options;
                    handler.sendMessage(message);
//                    baiduMap.addOverlays(options);

                }

            }
        });

    }
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    List<OverlayOptions> options2;
                    options2=(List<OverlayOptions>)msg.obj;
                    baiduMap.addOverlays(options2);
                    break;
            }
        }
    };

    ////////////////////////
    //以下为公交线路检索
    /**
     * 发起检索
     *此处是静态的onClick()的响应事件
     * @param v
     */
    public void searchButtonProcess(View v) {
        busLineIDList.clear();
        busLineIndex = 0;
        mBtnPre.setVisibility(View.INVISIBLE);
        mBtnNext.setVisibility(View.INVISIBLE);
        EditText editCity = (EditText) findViewById(R.id.city);
        EditText editSearchKey = (EditText) findViewById(R.id.searchkey);
        // 发起poi检索，从得到所有poi中找到公交线路类型的poi，再使用该poi的uid进行公交详情搜索
        mSearch.searchInCity((new PoiCitySearchOption())
                .city(editCity.getText().toString())
                .keyword(editSearchKey.getText().toString()));
    }

    public void searchNextBusline(View v) {
        if (busLineIndex >= busLineIDList.size()) {
            busLineIndex = 0;
        }
        if (busLineIndex >= 0 && busLineIndex < busLineIDList.size() && busLineIDList.size() > 0) {
            mBusLineSearch.searchBusLine((new BusLineSearchOption()
                    .city(((EditText) findViewById(R.id.city)).getText().toString())
                    .uid(busLineIDList.get(busLineIndex))));

            busLineIndex++;
        }

    }

    /**
     * 节点浏览示例
     *
     * @param v
     */
    public void nodeClick(View v) {
        if (nodeIndex < -1 || route == null || nodeIndex >= route.getStations().size()) {//没有找到线路信息
            return;
        }
        TextView popupText = new TextView(this);
        popupText.setBackgroundResource(R.drawable.popup);
        popupText.setTextColor(0xff000000);
        // 上一个节点
        if (mBtnPre.equals(v) && nodeIndex > 0) {
            // 索引减
            nodeIndex--;
        }
        // 下一个节点
        if (mBtnNext.equals(v) && nodeIndex < (route.getStations().size() - 1)) {
            // 索引加
            nodeIndex++;
        }
        if (nodeIndex >= 0) {
            // 移动到指定索引的坐标
            baiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(route
                    .getStations().get(nodeIndex).getLocation()));
            // 弹出泡泡
            popupText.setText(route.getStations().get(nodeIndex).getTitle());
            baiduMap.showInfoWindow(new InfoWindow(popupText, route.getStations()
                    .get(nodeIndex).getLocation(), 10));
        }
    }

    //以下为POI检索监听者中的三个函数
    //在POI检索结果中判断该POI类型是否为公交信息；
    @Override
    public void onGetPoiResult(PoiResult poiResult) {
        if (poiResult == null || poiResult.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(LBSActivity.this, "抱歉，未找到结果",
                    Toast.LENGTH_LONG).show();
            return;
        }
        // 遍历所有poi，找到类型为公交线路的poi
        busLineIDList.clear();
        for (PoiInfo poi : poiResult.getAllPoi()) {
            if (poi.type == PoiInfo.POITYPE.BUS_LINE
                    || poi.type == PoiInfo.POITYPE.SUBWAY_LINE) {
                busLineIDList.add(poi.uid);
            }
        }
        searchNextBusline(null);
        route = null;
    }

    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

    }

    @Override//没用到
    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

    }





    @Override
    public void onGetBusLineResult(BusLineResult busLineResult) {
        if (busLineResult == null || busLineResult.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(LBSActivity.this, "抱歉，未找到结果",
                    Toast.LENGTH_LONG).show();
            return;
        }
        baiduMap.clear();
        route = busLineResult;
        nodeIndex = -1;
        overlay.removeFromMap();//公交路线绘制对象
        overlay.setData(busLineResult);
        overlay.addToMap();
        overlay.zoomToSpan();
        mBtnPre.setVisibility(View.VISIBLE);
        mBtnNext.setVisibility(View.VISIBLE);
        Toast.makeText(LBSActivity.this, busLineResult.getBusLineName(),
                Toast.LENGTH_SHORT).show();
    }
}
