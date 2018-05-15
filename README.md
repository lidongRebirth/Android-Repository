# Android-Repository校园生活助手
## 总程序概要介绍：
- 登陆界面(LoginActivity)和主界面(MainActivity)为两个Activity，登陆界面验证密码后启动主界面；
- 主界面(activity_main.xml)底部由RadioGroup包含三个RadioButton,其上包含一个FrameLayout布局，通过三个RadioButton的切换，分别将相应的已编写好的三个- - fragment1.xml,fragment2.xml,fragment2.xml进行切换，利用编写的replaceFragment（）函数填入其内容；
- fragment1.xml为广场的布局，其内包含TableLayout布局，布局内包含多个按钮，多个按钮设立监听器，触发时出现新的Activity，各个Activity内完成相应的功能；
- fragment2.xml为我的布局，其内主要包含修改个人信息的功能，其他信息随后添加
- fragment3.xml为更多的布局，....
# `校园助手程序部分在MyApplication文件夹下`
## 软件界面截图：
### 1、主界面：
界面布局上方采用bannerView框架来显示滚动图片，下方采用RadioButton来制作导航栏，中间部分用ImageView的点击事件来进入各个功能界面
<div align=center><img width="200" height="350" src="http://bmob-cdn-19122.b0.upaiyun.com/2018/05/15/dc295f18401ea32e803dcdb8c408154a.gif"/></div>
校园招聘：主要是用WebView控件来加载校园网站，并设置网页自适应大小，利用ProgressBar来显示加载进度  
<div align=center><img width="200" height="350" src="http://bmob-cdn-19122.b0.upaiyun.com/2018/05/15/c15004fb405ef4278010e4fc0ded842b.gif"/></div>  
<br>
失物招领：采用ListView控件来加载从Bmob云端获取到的数据（不显示请点击链接：http://bmob-cdn-19122.b0.upaiyun.com/2018/05/15/eed2d7ba40c97a17806c7be9c21e358e.gif ）
<br>
<div align=center><img width="200" height="350" src="http://bmob-cdn-19122.b0.upaiyun.com/2018/05/15/eed2d7ba40c97a17806c7be9c21e358e.gif"/></div>
<br>
我的课表：采用GridView控件来显示课表，布局上方用自定义的WeekTitle视图来绘制星期标题
<div align=center><img width="200" height="350" src="http://bmob-cdn-19122.b0.upaiyun.com/2018/05/15/86a31166405b48d1802e3c8dfeac0fba.png"/></div> 
<br>
电话外卖：（不显示请点击链接：http://bmob-cdn-19122.b0.upaiyun.com/2018/05/15/100f21ef40371a3280b7f5377c7a4808.gif ）<br>
<div align=center><img width="200" height="350" src="http://bmob-cdn-19122.b0.upaiyun.com/2018/05/15/100f21ef40371a3280b7f5377c7a4808.gif"/></div>  
<br>
百度地图：采用MapView控件来显示地图，标记点信息从Bmob后端云获取标志点信息，获取到信息后进行以下操作：<br>
1、定义Maker坐标点<br>
LatLng point = new LatLng(39.963175, 116.400244);<br>
2、构建Marker图标<br>
BitmapDescriptor bitmap = BitmapDescriptorFactory<br>
    .fromResource(R.drawable.icon_marka);<br>
3、构建MarkerOption，用于在地图上添加Marker <br>
OverlayOptions option = new MarkerOptions()<br>
    .position(point)<br>
    .icon(bitmap);<br>
（不显示请点击链接：http://bmob-cdn-19122.b0.upaiyun.com/2018/05/15/d5f86bf1401815148058c6f894dd596e.gif ）<br>
<div align=center><img width="200" height="350" src="http://bmob-cdn-19122.b0.upaiyun.com/2018/05/15/100f21ef40371a3280b7f5377c7a4808.gif"/></div>  
<br>

### 2、我的信息界面：
头像更改：采用开源框架CircleImageView用来显示圆形头像，头像首先通过BmobQuery来查询头像url,然后建立HttpURLConnection连接，通过InputStream获取到图片数据流后，通过BitmapFactory.decodeStream()方法来将数据流转化为bmp图像，将此bmp图像复制给CircleImageView控件即可显示。（不显示请点击链接：http://bmob-cdn-19122.b0.upaiyun.com/2018/05/15/f98b4eba409e9fd280fa4d5e9c423635.gif ）
<div align=center><img width="200" height="350" src="http://bmob-cdn-19122.b0.upaiyun.com/2018/05/15/f98b4eba409e9fd280fa4d5e9c423635.gif"/></div> 
<br>
### 3、更多界面：
界面截图：<br>
<div align=center><img width="200" height="350" src="http://bmob-cdn-19122.b0.upaiyun.com/2018/05/15/fa658d9140312ca1803ea30db89e9670.png"/></div>  
<br>

