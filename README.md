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
* 校园招聘  
主要是用WebView控件来加载校园网站，并设置网页自适应大小，利用ProgressBar来显示加载进度  
<div align=center><img width="200" height="350" src="http://bmob-cdn-19122.b0.upaiyun.com/2018/05/15/c15004fb405ef4278010e4fc0ded842b.gif"/></div>  

### 2、我的信息界面：
### 3、更多界面：
