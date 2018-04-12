package com.example.asustp.myapplication;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baidulbs.LBSActivity;
import com.example.bannerview.BannerView;
import com.example.coursetable.CourseActivity;
import com.example.gradetable.GradeActivity;
import com.example.lostandfound.LostActivity;
import com.example.lostandfound.my_LostActivity;
import com.example.menulist.StoreActivity;
import com.example.notice.NoticeActivity;
import com.example.recruit.Recruit;
import com.example.userinfo._User;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.push.BmobPush;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.BmobInstallationManager;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.InstallationListener;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;



/*
* 主页面，fragment1的页面，默认选择
*
* */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioGroup select;
    private RadioButton btn1,btn2,btn3;
    private String ID="20144138169";//底下会在getintent中进行更改
    private String objectID;
    private SharedPreferences sp;


    //滑动图片所需要的内容
    private int[] imgs = {R.drawable.banner_image2,R.drawable.banner_image1,R.drawable.banner_image3,R.drawable.hbu_back};
    private List<View> viewList;
    BannerView bannerView;


    //六个功能按钮
    private ImageButton recruitBtn; //招聘按钮
    private ImageButton lostBtn;    //失物招领按钮
    private ImageButton courseBtn;  //课表按钮
    private ImageButton gradeBtn;  //课表按钮
    private ImageButton storeBtn;   //外卖按钮
    private ImageButton mapBtn;     //地图按钮


    //更多四个按钮
    private LinearLayout layout_postbar;
    private LinearLayout layout_news;
    private LinearLayout layout_update;
    private LinearLayout layout_exit;


    //三个view的显示
    private LinearLayout view1;
    private LinearLayout view2;
    private LinearLayout view3;


    //我按钮下界面的功能
    private ImageView icon;
    private TextView changeicon;
    private TextView account;
    private EditText password,signname;
    private ImageView showPassword;
    private Button passbtn,signbtn,makechange;
    private Button takephoto,pickphoto,cancle;

    private LinearLayout btn_layout;

    //弹出照片相册
    private View inflate;
    private Dialog dialog;
    private static final int IMAGE_REQUEST_CODE = 0;
    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int RESIZE_REQUEST_CODE = 2;
    private static final String IMAGE_FILE_NAME = "header.jpg";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//去掉标题栏
        // setContentView(R.layout.text_button);
        setContentView(R.layout.activity_main);//测试
        Bmob.initialize(this,"12a244a082bdc4511edeaf7f98a79c56");
        //推送服务
        BmobInstallationManager.getInstance().initialize(new InstallationListener<BmobInstallation>() {
            @Override
            public void done(BmobInstallation bmobInstallation, BmobException e) {
                if (e == null) {
                    Log.i("打印:",bmobInstallation.getObjectId() + "-" + bmobInstallation.getInstallationId());
//                    Toast.makeText(getApplication(),"ObjectId"+bmobInstallation.getObjectId() + "InstallationId" + bmobInstallation.getInstallationId(),Toast.LENGTH_SHORT).show();

                } else {
                    Log.e("打印:",e.getMessage());
                    Toast.makeText(getApplication(),"错误"+e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
        // 启动推送服务
        BmobPush.startWork(this);


        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){//可以访问SD卡的动态权限的申请
            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},0);
            }
        }

        sp = getSharedPreferences("userInfo", this.MODE_PRIVATE);//获取到sharePreferences


        //底部导航栏
        select = (RadioGroup) findViewById(R.id.main_tab);
        btn1 = (RadioButton) findViewById(R.id.radio_button0);
        btn2 = (RadioButton) findViewById(R.id.radio_button1);
        btn3 = (RadioButton) findViewById(R.id.radio_button2);
        //主视图三个view
        view1 = (LinearLayout) findViewById(R.id.fragment1);
        view2 = (LinearLayout) findViewById(R.id.fragment2);
        view3 = (LinearLayout) findViewById(R.id.fragment3);
        //更多三个layout
        layout_postbar = (LinearLayout) findViewById(R.id.layout_postbar);
        layout_news = (LinearLayout) findViewById(R.id.layout_news);
        layout_update = (LinearLayout) findViewById(R.id.layout_update);
        layout_exit = (LinearLayout) findViewById(R.id.layout_exit);

        //我界面按钮的注册
        icon = (ImageView) findViewById(R.id.image_icon);
        changeicon = (TextView) findViewById(R.id.changeicon);
        account = (TextView) findViewById(R.id.text_account);
        password = (EditText) findViewById(R.id.edit_password);
        signname = (EditText) findViewById(R.id.edit_sign);
        showPassword = (ImageView) findViewById(R.id.image_show_password);
        passbtn = (Button) findViewById(R.id.change_passbtn);
        signbtn = (Button) findViewById(R.id.change_signbtn);
        makechange = (Button) findViewById(R.id.makesurechange);

        takephoto = (Button) findViewById(R.id.btn_take_photo);
        pickphoto = (Button) findViewById(R.id.btn_pick_photo);
        cancle = (Button) findViewById(R.id.btn_cancel);

        btn_layout= (LinearLayout) findViewById(R.id.pass_sign_btn_layout);

        //功能按钮的注册
        recruitBtn = (ImageButton) findViewById(R.id.recruitBtn);
        lostBtn = (ImageButton) findViewById(R.id.lostandfound);
        courseBtn = (ImageButton) findViewById(R.id.course_table);
        gradeBtn = (ImageButton) findViewById(R.id.grade_table);
        storeBtn = (ImageButton) findViewById(R.id.carry_out);
        mapBtn= (ImageButton) findViewById(R.id.map_btn);

        Intent intent = getIntent();          //获取从LoginActivity中传来的用户ID
        ID = intent.getStringExtra("ID");
        objectID = intent.getStringExtra("objectID");
//        Toast.makeText(getApplicationContext(),"从登录界面获取的ID为："+ID, Toast.LENGTH_SHORT).show();


        //碎片填充容器
        //  replaceFragment(new Fragment1(ID));//刚上来就位于广场，所以先填充
        imageload();//加载滑动图片    刚上来就位于广场，所以先填充
        select.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (btn1.getId() == i) {
//                    Toast.makeText(getApplicationContext(), "主页面", Toast.LENGTH_SHORT).show();
                    //碎片填充容器
                    //replaceFragment(new Fragment1(ID));
                    view1.setVisibility(View.VISIBLE);
                    view2.setVisibility(View.GONE);
                    view3.setVisibility(View.GONE);
                   // imageload();//加载滑动图片

                }
                if (btn2.getId() == i) {
//                    Toast.makeText(getApplicationContext(), "我的资料", Toast.LENGTH_SHORT).show();
                    //replaceFragment(new Fragment2());
                    view1.setVisibility(View.GONE);
                    view2.setVisibility(View.VISIBLE);
                    view3.setVisibility(View.GONE);
                    account.setText(sp.getString("USER_NAME", ""));//从SharePerference中获取学号和密码
                    password.setText(sp.getString("PASSWORD", ""));//从SharePerference中获取学号和密码
                    download();//获取后端云中头像
                    showSign();//获取后端云中的签名
                }
                if (btn3.getId() == i) {
//                    Toast.makeText(getApplicationContext(), "更多", Toast.LENGTH_SHORT).show();
                    //replaceFragment(new Fragment3());
                    view1.setVisibility(View.GONE);
                    view2.setVisibility(View.GONE);
                    view3.setVisibility(View.VISIBLE);
                }
            }
        });


        //主页面功能按钮
        recruitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {            //招聘功能
                Intent intent = new Intent(getApplication(), Recruit.class);
                startActivity(intent);
            }
        });
        lostBtn.setOnClickListener(new View.OnClickListener() {//失物招领功能
            @Override
            public void onClick(View view) {                //失物招领功能
                Intent intent = new Intent(getApplication(), LostActivity.class);
                intent.putExtra("ID",ID);
                startActivity(intent);
            }
        });
        courseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {              //课表功能
                Intent intent = new Intent(getApplication(), CourseActivity.class);
                startActivity(intent);
            }
        });
        gradeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {                //成绩表功能
                Intent intent = new Intent(getApplication(), GradeActivity.class);
                startActivity(intent);
            }
        });
        storeBtn.setOnClickListener(new View.OnClickListener() {//外卖功能
            @Override
            public void onClick(View view) {                //电话外卖功能
                Intent intent = new Intent(getApplication(), StoreActivity.class);
                startActivity(intent);
            }
        });
        mapBtn.setOnClickListener(new View.OnClickListener() {   //百度地图功能
            @Override
            public void onClick(View view) {                   //百度地图功能
                Intent intent = new Intent(getApplication(), LBSActivity.class);
                startActivity(intent);
            }
        });


        //更多按钮
        layout_postbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), my_LostActivity.class);
                intent.putExtra("ID",ID);
                startActivity(intent);
            }
        });
        layout_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplication(), NoticeActivity.class);
                intent.putExtra("ID",ID);
                startActivity(intent);
            }

        });
        layout_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplication(), "当前已是最新版本", Toast.LENGTH_SHORT).show();
            }
        });
        layout_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //我界面按钮功能
        changeicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(view);
            }
        });


        //密码可见
        showPassword.setOnTouchListener(new View.OnTouchListener() {//密码的呈现与隐藏事件
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP://按住事件后执行代码的区域
                    {
                        password.setTransformationMethod(PasswordTransformationMethod.getInstance());//EditText动态呈现密文
                        break;
                    }
                    case MotionEvent.ACTION_DOWN://松开事件后执行的代码
                    {
                        password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());//EditText动态显示
                        break;
                    }
                    default:
                        break;
                }
                return true;
            }
        });
        passbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password.setFocusableInTouchMode(true);
                password.setFocusable(true);
                password.requestFocus();//设置EditText为可编辑状态，必须三个都要写
                makechange.setVisibility(View.VISIBLE);
                btn_layout.setVisibility(View.GONE);
            }
        });
        signbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signname.setFocusableInTouchMode(true);
                signname.setFocusable(true);
                signname.requestFocus();
                makechange.setVisibility(View.VISIBLE);
                btn_layout.setVisibility(View.GONE);
            }
        });
        makechange.setOnClickListener(new View.OnClickListener() {//确定更改
            @Override
            public void onClick(View view) {//更新用户的信息
                //本地文件更新
                SharedPreferences.Editor editor = sp.edit();
                final String passwordValue, signValue;
                passwordValue = password.getText().toString();
                signValue = signname.getText().toString();
                editor.putString("PASSWORD", passwordValue);
//                editor.putString("SIGN", signValue);
                editor.commit();//一定要提交
                password.setFocusable(false);
                password.setFocusableInTouchMode(false);//密码框不可编辑
                signname.setFocusable(false);
                signname.setFocusableInTouchMode(false);//签名框不可编辑

                //后端云处更新
                BmobQuery<_User> query = new BmobQuery<_User>();
                query.addWhereEqualTo("username", ID);
                query.findObjects(new FindListener<_User>() {
                    @Override
                    public void done(List<_User> list, BmobException e) {
                        if (e == null) {
                            _User user = new _User();
                            user.setPassword(passwordValue);
                            user.setSign(signValue);
                            user.update(list.get(0).getObjectId(), new UpdateListener() {
                                @Override
                                public void done(BmobException e) {
                                    if (e == null) {
                                        Toast.makeText(getApplicationContext(), "修改成功", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "修改失败" + e.getMessage() + e.getErrorCode(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(getApplicationContext(), "查询失败" + e.getMessage() + e.getErrorCode(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                makechange.setVisibility(view.GONE);//确认按钮不可见
                btn_layout.setVisibility(View.VISIBLE);
            }
        });
    }
    public void showDialog(View view){          //弹出相机相册对话框
        dialog = new Dialog(this,R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        inflate = LayoutInflater.from(this).inflate(R.layout.six_alert_dialog, null);
        //将布局设置给Dialog


        //以下几个按钮的注册必须在此处进行，在oncreate()中写的话会报错
        takephoto = (Button) inflate.findViewById(R.id.btn_take_photo);
        pickphoto = (Button) inflate.findViewById(R.id.btn_pick_photo);
        cancle= (Button) inflate.findViewById(R.id.btn_cancel);
        takephoto.setOnClickListener(this);
        pickphoto.setOnClickListener(this);
        cancle.setOnClickListener(this);

        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity( Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = 20;//设置Dialog距离底部的距离
//       将属性设置给窗体
        dialogWindow.setAttributes(lp);
        dialog.show();//显示对话框
    }
//Dialog框中三个按钮的功能
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_pick_photo:
                dialog.dismiss();
                Toast.makeText(this,"选择照片",Toast.LENGTH_SHORT).show();
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.addCategory(Intent.CATEGORY_OPENABLE);
                galleryIntent.setType("image/*");//图片
                startActivityForResult(galleryIntent, IMAGE_REQUEST_CODE);
                break;
            case R.id.btn_take_photo:
                dialog.dismiss();
                if (isSdcardExisting()) {
                    Intent cameraIntent = new Intent("android.media.action.IMAGE_CAPTURE");//拍照
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, getImageUri());//将拍照的地址放在了自己已经选定的地址上
                    cameraIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
                } else {
                    Toast.makeText(this, "请插入sd卡", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btn_cancel:
                dialog.dismiss();
                break;
        }
    }
    //打开新活动，新活动给本活动的返回情况的处理
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        } else {
            switch (requestCode) {
                case IMAGE_REQUEST_CODE:
                    Uri originalUri=data.getData();//获取图片uri
                    resizeImage(originalUri);

                    //下面方法将获取的uri转为String类型哦！
                    String []imgs={MediaStore.Images.Media.DATA};//将图片URI转换成存储路径
                    Cursor cursor=this.managedQuery(originalUri, imgs, null, null, null);
                    int index=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();
                    String img_url=cursor.getString(index);
//                    Toast.makeText(this, img_url, Toast.LENGTH_SHORT).show();  显示照片的地址
                    uploadFile(img_url);
                    break;
                case CAMERA_REQUEST_CODE:
                    if (isSdcardExisting()) {
                        resizeImage(getImageUri());

                        String []imgs2={MediaStore.Images.Media.DATA};//将图片URI转换成存储路径
                        Cursor cursor2=this.managedQuery(getImageUri(), imgs2, null, null, null);
                        int index2=cursor2.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                        cursor2.moveToFirst();
                        String img_url2=cursor2.getString(index2);
                        Toast.makeText(this, img_url2, Toast.LENGTH_SHORT).show();
                        uploadFile(img_url2);
                    } else {
                        Toast.makeText(MainActivity.this, "未找到存储卡，无法存储照片！", Toast.LENGTH_LONG).show();
                    }
                    break;
                case RESIZE_REQUEST_CODE:
                    if (data != null) {
                        showResizeImage(data);
                    }
                    break;
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public boolean isSdcardExisting() {//判断SD卡是否存在
        final String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    public Uri getImageUri() {//获取路径          //新new一个路径来存放你选的照片或新拍的照片
        return Uri.fromFile(new File(Environment.getExternalStorageDirectory(), IMAGE_FILE_NAME));
    }

    public void resizeImage(Uri uri) {//重塑图片大小
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");// 这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("aspectX", 1);//该参数可以不设定用来规定裁剪区的宽高比
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 150);//该参数设定为你的imageView的大小
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);//是否返回bitmap对象
        startActivityForResult(intent, RESIZE_REQUEST_CODE);
    }

    private void showResizeImage(Intent data) {//显示图片
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            Drawable drawable = new BitmapDrawable(photo);
            icon.setImageDrawable(drawable);
            //Bitmap 储存的是 像素信息，Drawable 储存的是 对 Canvas 的一系列操作。而 BitmapDrawable储存的是「把 Bitmap 渲染到 Canvas 上」这个操作。
            // Bitmap和Drawable前者重数据，后者重行为，Drawable经过封装可以实现网络加载功能而Bitmap不行
        }
    }

    public void uploadFile(String str){ //后端云处更新    上传素材且更新绑定
        final BmobFile file=new BmobFile(new File(str));
        file.upload(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    Toast.makeText(MainActivity.this, "上传文件成功:" + file.getFileUrl(), Toast.LENGTH_SHORT).show();
                    _User  user=new _User(file);
                    user.update(objectID, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {//成功
                            if(e==null){
                                Toast.makeText(getApplicationContext(),"修改成功",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"修改失败"+e.getMessage()+e.getErrorCode(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(getApplication(),"上传失败"+e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    //下载图片显示
    private void download(){
        BmobQuery<_User> query = new BmobQuery<_User>();
        query.getObject(objectID, new QueryListener<_User>() {
            @Override
            public void done(final _User object, BmobException e) {
                if(e==null){
//                    Toast.makeText(getApplicationContext(),"URL:"+object.getIcon().getUrl(),Toast.LENGTH_SHORT).show();       测试获取已上传图像的URL
                    final String url=object.getIcon().getUrl();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            Bitmap bmp = getURLimage(url);
                            Message msg = new Message();
                            msg.what = 0;
                            msg.obj = bmp;
                            System.out.println("000");
                            handle.sendMessage(msg);
                        }
                    }).start();

                }else{
                    Toast.makeText(getApplicationContext(),"获取头像失败"+e.getMessage()+e.getErrorCode(),Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
    //加载图片
    public Bitmap getURLimage(String url) {
        Bitmap bmp = null;
        try {
            URL myurl = new URL(url);
            // 获得连接
            HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();
            conn.setConnectTimeout(6000);//设置超时
            conn.setDoInput(true);
            conn.setUseCaches(false);//不缓存
            conn.connect();
            InputStream is = conn.getInputStream();//获得图片的数据流
            bmp = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bmp;
    }
    //在消息队列中实现对控件的更改
    private Handler handle = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    System.out.println("111");
                    Bitmap bmp=(Bitmap)msg.obj;
                    icon.setImageBitmap(bmp);
                    break;
            }
        }
    };

    public void showSign(){
        BmobQuery<_User> query=new BmobQuery<_User>();
        query.addWhereEqualTo("username",ID);
        query.findObjects(new FindListener<_User>(){
            @Override
            public void done(List<_User> list, BmobException e) {
                if(e==null){
                    signname.setText(list.get(0).getSign());
                }
                else{
                    Toast.makeText(getApplicationContext(),"获取签名失败"+e.getMessage()+e.getErrorCode(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//    //因为碎片化的生命周期问题，所以这个不成立，弃用
//    private void replaceFragment(Fragment fragment){    //P149页内容
//        FragmentManager fragmentManager=getSupportFragmentManager();                //获取FragmentManager实例
//        FragmentTransaction transaction=fragmentManager.beginTransaction();         //开启一个事务
//        transaction.replace(R.id.fragmentcontain,fragment);                         //向容器内添加或替换碎片
//        transaction.commit();
//    }

    private void imageload (){
        viewList = new ArrayList<View>();
        for (int i = 0; i < imgs.length; i++) {
            ImageView image = new ImageView(this);
            image.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            //设置显示格式
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            image.setImageResource(imgs[i]);
            viewList.add(image);
        }
        bannerView = (BannerView) findViewById(R.id.banner);
        bannerView.startLoop(true);
        bannerView.setViewList(viewList);
    }


}
