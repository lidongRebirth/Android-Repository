# Android-Repository校园生活助手
## 总程序概要介绍：
- 登陆界面(LoginActivity)和主界面(MainActivity)为两个Activity，登陆界面验证密码后启动主界面；
- 主界面(activity_main.xml)底部由RadioGroup包含三个RadioButton,其上包含一个FrameLayout布局，通过三个RadioButton的切换，分别将相应的已编写好的三个- - fragment1.xml,fragment2.xml,fragment2.xml进行切换，利用编写的replaceFragment（）函数填入其内容；
- fragment1.xml为广场的布局，其内包含TableLayout布局，布局内包含多个按钮，多个按钮设立监听器，触发时出现新的Activity，各个Activity内完成相应的功能；
- fragment2.xml为我的布局，其内主要包含修改个人信息的功能，其他信息随后添加
- fragment3.xml为更多的布局，....
# `校园助手程序部分在MyApplication文件夹下`
## 软件界面截图：
### 主界面：
![][http://bmob-cdn-19122.b0.upaiyun.com/2018/05/15/dc295f18401ea32e803dcdb8c408154a.gif]
### 我的信息界面：
### 更多界面：
