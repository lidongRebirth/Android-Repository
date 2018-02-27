package com.jikexueyuan.com.mycoursetable;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SQLQueryListener;

public class MainActivity extends AppCompatActivity {

    private GridView detailCource;  //默认是从左往右进行填充数据
    private MyAdapter adapter;      //适配器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this, "12a244a082bdc4511edeaf7f98a79c56");//Bmob初始化
        detailCource = (GridView)findViewById(R.id.courceDetail);
//        SimpleDateFormat   formatter   =   new   SimpleDateFormat   ("yyyy-MM-dd HH:mm:ss");//记得导入import java.text.SimpleDateFormat;
//        SimpleDateFormat   formatter   =   new   SimpleDateFormat   ("yyyy-MM-dd 08:00:00");//记得导入import java.text.SimpleDateFormat;
//        Date curDate =  new Date(System.currentTimeMillis());
        //获取当前时间
//        String   str   =   formatter.format(curDate);
//        Toast.makeText(getApplication(),"当前时间为："+str,Toast.LENGTH_SHORT).show();
//        String dateStr = "2015-09-27 12:15:31";
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        try {
//            // 用parse方法，可能会异常，所以要try-catch
//            Date date = format.parse(dateStr);
//            // 获取日期实例
//            Calendar calendar = Calendar.getInstance();
//            // 将日历设置为指定的时间
//            calendar.setTime(date);
//            // 获取年
//            int year = calendar.get(Calendar.YEAR);
//            Toast.makeText(getApplication(),"年："+year,Toast.LENGTH_SHORT).show();
//            // 这里要注意，月份是从0开始。
//            int month = calendar.get(Calendar.MONTH)+1;
//            Toast.makeText(getApplication(),"月"+month,Toast.LENGTH_SHORT).show();
//
//            // 获取天
//            int day = calendar.get(Calendar.DAY_OF_MONTH);
//            Toast.makeText(getApplication(),"天"+day,Toast.LENGTH_SHORT).show();
//
//            int hour=calendar.get(calendar.HOUR_OF_DAY);//时
//            Toast.makeText(getApplication(),"hour"+hour,Toast.LENGTH_SHORT).show();
//
//            int minute=calendar.get(calendar.MINUTE);//分
//            Toast.makeText(getApplication(),"hour"+minute,Toast.LENGTH_SHORT).show();
//
//            int milli=calendar.get(calendar.MILLISECOND);//秒  不对
//            Toast.makeText(getApplication(),"毫秒"+milli,Toast.LENGTH_SHORT).show();
//
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//

//        Toast.makeText(getApplication(),transtime("2018-02-25 21:35:00"),Toast.LENGTH_SHORT).show();
//        dataList = init();
//        dataList = my_init();
//
//        adapter = new MyAdapter(this, dataList);
//        detailCource.setAdapter(adapter);
//start_time<=date('transtime') and end_time>=date('2018-02-25 08:00:00') and   course_week='周一'
//2018-02-25 08:00:00       2018-02-25 09:40:00  select * from GameScore where createdAt < date('2015-05-20 00:00:00')
//select * from Course where start_time=date('2018-02-25 08:00:00')
/// /String bql ="select * from Course where course_name='数学'"  成功




//        new BmobQuery<Course>().doSQLQuery(bql,new SQLQueryListener<Course>(){
//            @Override
//            public void done(BmobQueryResult<Course> result, BmobException e) {
//                if(e ==null){
//                    List<Course> list = (List<Course>) result.getResults();
//                    //Toast.makeText(getApplication(),list.get(0).getCourse_id().toString(),Toast.LENGTH_SHORT).show();
//                    for(int i=0;i<list.size();i++){
////                        Toast.makeText(getApplication(),list.get(i).getCourse_name(),Toast.LENGTH_SHORT).show();
////                        Toast.makeText(getApplication(),list.get(i).getStart_time().getDate(),Toast.LENGTH_SHORT).show();
//                        fill_array(list.get(i),monday);
//                    }
//// 测试能否获得数据
////            for(int j=0;j<monday.length;j++){
////                        Toast.makeText(getApplication(),monday[j],Toast.LENGTH_SHORT).show();
////                    }
//                }else{
//                    Toast.makeText(getApplication(),"错误码："+e.getErrorCode(),Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        //                String bql ="select * from Found where phone='1'";//查询所有记录
//                new BmobQuery<Found>().doSQLQuery(bql,new SQLQueryListener<Found>(){
//                    @Override
//                    public void done(BmobQueryResult<Found> result, BmobException e) {
//                        if(e ==null){
//                            List<Found> list = (List<Found>) result.getResults();
//                            Toast.makeText(getApplication(),list.get(0).getDescribe(),Toast.LENGTH_SHORT).show();
//                        }else{
//                            Toast.makeText(getApplication(),"错误码："+e.getErrorCode(),Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });

//        for(int i=0;i<11;i++){
//            dataList.add(monday[i]);
//            dataList.add(tuesday[i]);
//            dataList.add(wedensday[i]);
//            dataList.add(thursday[i]);
//            dataList.add(friday[i]);
//            dataList.add(saturday[i]);
//            dataList.add(sunday[i]);
//        }
//        if(judge==true){
//            Toast.makeText(getApplicationContext(),"获取完成",Toast.LENGTH_SHORT).show();
//        }
//        while(judge){
//            for(int i=0;i<11;i++){
//            dataList.add(monday[i]);
//            dataList.add(tuesday[i]);
//            dataList.add(wedensday[i]);
//            dataList.add(thursday[i]);
//            dataList.add(friday[i]);
//            dataList.add(saturday[i]);
//            dataList.add(sunday[i]);
//            }
//            adapter = new MyAdapter(this, dataList);
//            detailCource.setAdapter(adapter);
//        }

//        dataList = init();
//        adapter = new MyAdapter(this, dataList);
//        detailCource.setAdapter(adapter);
//      new DownTask().execute();

        my_init();
     /**
     * 《测试1》  不能在此处进行赋值，handler中的消息不能对成员变量赋值，取到的为空
     */
//        while(true){
//            if(w1==true&&w2==true&&w3==true&&w4==true&&w5==true&&w6==true&&w7==true){
//                Toast.makeText(getApplicationContext(),"获取完成",Toast.LENGTH_SHORT).show();
//                for(int i=0;i<11;i++){
//                    dataList.add(my_monday[i]);
//                    dataList.add(my_tuesday[i]);
//                    dataList.add(my_wedensday[i]);
//                    dataList.add(my_thursday[i]);
//                    dataList.add(my_friday[i]);
//                    dataList.add(my_saturday[i]);
//                    dataList.add(my_sunday[i]);
//                }
//                adapter = new MyAdapter(getApplicationContext(), dataList);
//                detailCource.setAdapter(adapter);
//                break;
//            }
//        }
    }
    /**
     * 《测试2》  准备数据
     */
    private List<String> init() {
        List<String> list = new ArrayList<String>();
        list.add("现代测试技术B211");
        list.add("数据结构与算法B211");
        list.add("微机原理及应用E203");
        list.add("面向对象程序设计A309");
        list.add("数据结构与算法B207");
        list.add("");
        list.add("");
        list.add("微机原理及应用E203");
        list.add("");
        list.add("电磁场理论A212");
        list.add("传感器电子测量A\nC309");
        list.add("微机原理及应用E203");
        list.add("");
        list.add("");
        list.add("电磁场理论A212");
        list.add("面向对象程序设计A309");
        list.add("现代测试技术B211");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("传感器电子测量A\nC309");
        list.add("面向对象程序设计A309");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        return list;
    }

    /**
     * 函数 将2018-02-25 21:35:00字符串转化为值有  21：35：00的函数
     */
    private String  transtime(String dateStr){
        String returntime;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 获取日期实例
        Calendar calendar = Calendar.getInstance();
        // 将日历设置为指定的时间
        calendar.setTime(date);
        int hour=calendar.get(calendar.HOUR_OF_DAY);//时
        int minute=calendar.get(calendar.MINUTE);//分
        returntime=String.valueOf(hour)+String.valueOf(minute)+"00";
        return returntime;
    }
    /**
     * 将一天的课程加入到数组中
     */
    private void  fill_array(Course course,String weekarray[]){//将周一的课全部填充到一个数组中
        String start_time=course.getStart_time().getDate();
        String end_time=course.getEnd_time().getDate();
//        String start_time="2018-02-25 08:00:00";
//        String end_time="2018-02-25 09:40:00";

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//此处是为了将日期中的时分提取出来
        Date date_start = null;
        Date date_end = null;
        try {
            date_start = format.parse(start_time);
            date_end = format.parse(end_time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 获取日期实例
        Calendar calendar_start = Calendar.getInstance();
        Calendar calendar_end = Calendar.getInstance();

        // 将日历设置为指定的时间
        calendar_start.setTime(date_start);
        calendar_end.setTime(date_end);

        int hour_start=calendar_start.get(calendar_start.HOUR_OF_DAY);//时
        int hour_end=calendar_end.get(calendar_end.HOUR_OF_DAY);//时


        if(hour_start==8){//从第一节开始上
            if(hour_end==9){//连上两节
                weekarray[0]=course.getCourse_name()+"\n"+course.getCourse_site();
                weekarray[1]=course.getCourse_name()+"\n"+course.getCourse_site();
            }
            if(hour_end==11){//连上四节
                weekarray[0]=course.getCourse_name()+"\n"+course.getCourse_site();
                weekarray[1]=course.getCourse_name()+"\n"+course.getCourse_site();
                weekarray[2]=course.getCourse_name()+"\n"+course.getCourse_site();
                weekarray[3]=course.getCourse_name()+"\n"+course.getCourse_site();
            }
        }
        if(hour_start==10){//从第三节开始上
            weekarray[2]=course.getCourse_name()+"\n"+course.getCourse_site();
            weekarray[3]=course.getCourse_name()+"\n"+course.getCourse_site();
        }
        if(hour_start==14){//从第五节开始上
            if(hour_end==16){//连上两节
                weekarray[4]=course.getCourse_name()+"\n"+course.getCourse_site();
                weekarray[5]=course.getCourse_name()+"\n"+course.getCourse_site();
            }
            if(hour_end==18){//连上四节
                weekarray[4]=course.getCourse_name()+"\n"+course.getCourse_site();
                weekarray[5]=course.getCourse_name()+"\n"+course.getCourse_site();
                weekarray[6]=course.getCourse_name()+"\n"+course.getCourse_site();
                weekarray[7]=course.getCourse_name()+"\n"+course.getCourse_site();
            }
        }
        if (hour_start==16){//从第七节开始上
            weekarray[6]=course.getCourse_name()+"\n"+course.getCourse_site();
            weekarray[7]=course.getCourse_name()+"\n"+course.getCourse_site();
        }
        if(hour_start==19){//从第九节开始上
            if(hour_end==20){//连上两节
                weekarray[8]=course.getCourse_name()+"\n"+course.getCourse_site();
                weekarray[9]=course.getCourse_name()+"\n"+course.getCourse_site();
            }
            if(hour_end==21){//连上三节
                weekarray[8]=course.getCourse_name()+"\n"+course.getCourse_site();
                weekarray[9]=course.getCourse_name()+"\n"+course.getCourse_site();
                weekarray[11]=course.getCourse_name()+"\n"+course.getCourse_site();
            }
        }

    }
    /**
     * 查询一周的课程
     */
    private void  query_course(String bql, final int identify ){
        new BmobQuery<Course>().doSQLQuery(bql,new SQLQueryListener<Course>(){
            @Override
            public void done(BmobQueryResult<Course> result, BmobException e) {
                if(e ==null){
//                  String weekarry[]=new String[11];//为空的不能给datalist赋值导致出现莫名的错误
                    String weekarry[]={"","","","","","","","","","",""};
                    List<Course> list = (List<Course>) result.getResults();//一天的课
//                  Toast.makeText(getApplication(),list.get(0).getCourse_id().toString(),Toast.LENGTH_SHORT).show();
                    for(int i=0;i<list.size();i++){
                       fill_array(list.get(i),weekarry);//一天的可课排列起来
                    }
//                  Toast.makeText(getApplicationContext(),weekarry[0],Toast.LENGTH_SHORT).show();
                    Message message=new Message();
                    message.what=identify;
                    Bundle b=new Bundle();
                    b.putStringArray("arry",weekarry);
                    message.setData(b);
                    handler.sendMessage(message);
//                   Toast.makeText(getApplication(),"获取完成",Toast.LENGTH_SHORT).show();
//                    for(int j=0;j<weekarry.length;j++){
//                        Toast.makeText(getApplication(),monday[j],Toast.LENGTH_SHORT).show();
//                    }
//                    for(int i=0;i<11;i++){
//                        dataList.add(monday[i]);
//                    }
                }else{
                    Toast.makeText(getApplication(),"错误码："+e.getErrorCode(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    /**
     * 准备数据!!!!此处还有问题
     */
    private void  my_init() {

        SimpleDateFormat   formatter1   =   new   SimpleDateFormat   ("yyyy-MM-dd 21:35:00");//记得导入import java.text.SimpleDateFormat;
        SimpleDateFormat   formatter2   =   new   SimpleDateFormat   ("yyyy-MM-dd 08:00:00");//记得导入import java.text.SimpleDateFormat;
        Date curDate =  new Date(System.currentTimeMillis());
        String str1=formatter1.format(curDate);//2018-02-25 21:35:00    用于查询当天所在周的课程
        String str2=formatter2.format(curDate);//2018-02-25 08:00:00

        //Bmonb不支持连接查询  想办法解决 还未解决如何体现是谁选得课
       // String bql1_text ="select Course.* from Course,select_course where select_course.ID=20144138169 and select_course.course_id= Course.course_id and start_time<=date('"+str1+"') and end_time>=date('"+str2+"') and  course_week='周一'";
        String bql1 ="select * from Course where start_time<=date('"+str1+"') and end_time>=date('"+str2+"') and  course_week='周一'";//查询所有当天这一周的周一的课  此处的年月日为当前的年月日

        //"select * from Course where start_time<=date('2018-02-25 21:35:00') and end_time>=date('2018-02-25 08:00:00')这样是为了筛选出2-25号这一天的课
//        String bql1 ="select * from Course where start_time<=date('2018-02-25 21:35:00') and end_time>=date('2018-02-25 08:00:00') and  course_week='周一'";//查询所有当天这一周的周一的课  此处的年月日为当前的年月日
        query_course(bql1,1);
        String bql2 ="select * from Course where start_time<=date('"+str1+"') and end_time>=date('"+str2+"') and  course_week='周二'";
        query_course(bql2,2);
        String bql3 ="select * from Course where start_time<=date('"+str1+"') and end_time>=date('"+str2+"') and  course_week='周三'";
        query_course(bql3,3);
        String bql4 ="select * from Course where start_time<=date('"+str1+"') and end_time>=date('"+str2+"') and  course_week='周四'";
        query_course(bql4,4);
        String bql5 ="select * from Course where start_time<=date('"+str1+"') and end_time>=date('"+str2+"') and  course_week='周五'";
        query_course(bql5,5);
        String bql6 ="select * from Course where start_time<=date('"+str1+"') and end_time>=date('"+str2+"') and  course_week='周六'";
        query_course(bql6,6);
        String bql7 ="select * from Course where start_time<=date('"+str1+"') and end_time>=date('"+str2+"') and  course_week='周日'";
        query_course(bql7,7);

        /**
         * 以下测试未成功，因为此函数中的获取数据是异步查询到的数据，可能还未查询到数据就进行以下的赋值操作
         */
//        Toast.makeText(getApplicationContext(),"加载了完成",Toast.LENGTH_SHORT).show();
//        for(int i=0;i<11;i++){
//            list.add(monday[i]);
//            list.add(tuesday[i]);
//            list.add(wedensday[i]);
//            list.add(thursday[i]);
//            list.add(friday[i]);
//            list.add(saturday[i]);
//            list.add(sunday[i]);
//        }
//        for(int j=0;j<monday.length;j++){
//            Toast.makeText(getApplication(),monday[j],Toast.LENGTH_SHORT).show();
//        }
//        for(int j=0;j<tuesday.length;j++){
//            Toast.makeText(getApplication(),tuesday[j],Toast.LENGTH_SHORT).show();
//        }
    }
    /**
     * 异步任务类    测试未成功因为查询数据是异步查询，想在doInBackground里进行耗时操作，在操作完成后才在onPostExecute里执行UI操作
     */
    public class DownTask extends AsyncTask<Void, Integer, Boolean > {
        @Override
        protected Boolean  doInBackground(Void... strings) {
            try{
                my_init();
//                Toast.makeText(getApplicationContext(),"网络查询完",Toast.LENGTH_SHORT).show();    //此处不能Toast，因为此处为在线程中进行的，不能进行UI操作
            }catch (Exception e){
                return false;
            }
            return true ;
        }

        @Override
        protected void onPostExecute(Boolean result) {

            if(result){
//            Toast.makeText(getApplicationContext(),"线程完成",Toast.LENGTH_SHORT).show();
//            Toast.makeText(getApplicationContext(),"看成员变量是否变了："+monday[3],Toast.LENGTH_SHORT).show();//并未成功，虽然查询已经传出了消息，成员变量进行了赋值，但此处却显示为空
//            if(result){
//                List<String> dataList1= new ArrayList<String>();
//                for(int i=0;i<11;i++){
//                    dataList1.add(my_monday[i]);
//                    dataList1.add(my_tuesday[i]);
//                    dataList1.add(my_wedensday[i]);
//                    dataList1.add(my_thursday[i]);
//                    dataList1.add(my_friday[i]);
//                    dataList1.add(my_saturday[i]);
//                    dataList1.add(my_sunday[i]);
//                }
//                adapter = new MyAdapter(getApplicationContext(), dataList1);
//                detailCource.setAdapter(adapter);
//            }

            }
        }
    }
    /**
     * 用来接受Bombquery异步查询返回来的数组
     */
    private Handler handler=new Handler() {
        Boolean w1=false;
        Boolean w2=false;
        Boolean w3=false;
        Boolean w4=false;
        Boolean w5=false;
        Boolean w6=false;
        Boolean w7=false;
        List<String> dataList1= new ArrayList<String>();
        String my_monday[]={"","","","","","","","","","",""};//  String monday[]=new String[11];不这样写是因为这样的值为空，而为list赋值时，为空的需要写为""才能作为适配器为课表填充
        String my_tuesday[]={"","","","","","","","","","",""};
        String my_wedensday[]={"","","","","","","","","","",""};
        String my_thursday[]={"","","","","","","","","","",""};
        String my_friday[]={"","","","","","","","","","",""};
        String my_saturday[]={"","","","","","","","","","",""};
        String my_sunday[]={"","","","","","","","","","",""};
        public void  handleMessage(Message msg){
            String arry[]=msg.getData().getStringArray("arry");
            switch (msg.what){//注意：case和if不同，每个case最好加上break,不然，会一个一个条件的进行判断
                case 1:
//                    monday=arry;  //此处在测试是否能直接对成员变量monday数组赋值
//                    text=arry[0]; //!!!!为什么不能为成员变量赋值  日后了解一下如何在Handler 方法中对成员变量赋值
//                   Toast.makeText(getApplicationContext(),"handler中"+monday[0],Toast.LENGTH_SHORT).show();//为空显示不出内容
//                   Toast.makeText(getApplicationContext(),"handler中"+arry[0],Toast.LENGTH_SHORT).show();//可以显示内容，证明从异步查询Handler
                    my_monday=arry;
                    w1=true;
                    break;
                case 2:
                    my_tuesday=arry;
                    w2=true;
                    break;
                case 3:
                    my_wedensday=arry;
                    w3=true;
                    break;
                case 4:
                    my_thursday=arry;
                    w4=true;
                    break;
                case 5:
                    my_friday=arry;
                    w5=true;
                    break;
                case 6:
                    my_saturday=arry;
                    w6=true;
                    break;
                case 7:
                    my_sunday=arry;
                    w7=true;
                    break;
                default://是在以上的case情况都不满足的情况下才会执行的
                    break;
            }
            if(w1==true&&w2==true&&w3==true&&w4==true&&w5==true&&w6==true&&w7==true){
                for(int i=0;i<11;i++){
                        dataList1.add(my_monday[i]);
                        dataList1.add(my_tuesday[i]);
                        dataList1.add(my_wedensday[i]);
                        dataList1.add(my_thursday[i]);
                        dataList1.add(my_friday[i]);
                        dataList1.add(my_saturday[i]);
                        dataList1.add(my_sunday[i]);
                    }
                    adapter = new MyAdapter(getApplicationContext(), dataList1);
                    detailCource.setAdapter(adapter);
            }






        }


    };

    /**
     * 时间的测试  测试8:0:00和08:00:00查询的是否一致
     */
    private void query_text(){
        String bql="select * from Course where start_time==date('2018-02-25 8:0:00') course_week='周一'";
        new BmobQuery<Course>().doSQLQuery(bql,new SQLQueryListener<Course>(){
            @Override
            public void done(BmobQueryResult<Course> bmobQueryResult, BmobException e) {
                List<Course> list = (List<Course>) bmobQueryResult.getResults();//一天的课
                Toast.makeText(getApplicationContext(),"时间测试:"+list.get(0).getCourse_name(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    /**
     * 时间字符串互相转化的测试
     */
    private void trans_time(){
//        SimpleDateFormat   formatter   =   new   SimpleDateFormat   ("yyyy-MM-dd HH:mm:ss");//记得导入import java.text.SimpleDateFormat;
//        //获取当前时间
//        Date curDate =  new Date(System.currentTimeMillis());
//        // 获取日期实例
//        Calendar calendar = Calendar.getInstance();
//        // 将日历设置为指定的时间
//        calendar.setTime(curDate);
//        int hour=calendar.get(calendar.HOUR_OF_DAY);//时
//        int minute=calendar.get(calendar.MINUTE);//分
//        Toast.makeText(getApplicationContext(),"时："+hour+"分："+minute,Toast.LENGTH_SHORT).show();
        SimpleDateFormat   formatter1   =   new   SimpleDateFormat   ("yyyy-MM-dd 21:35:00");//记得导入import java.text.SimpleDateFormat;
        SimpleDateFormat   formatter2   =   new   SimpleDateFormat   ("yyyy-MM-dd 08:00:00");//记得导入import java.text.SimpleDateFormat;

        Date curDate =  new Date(System.currentTimeMillis());
        //获取当前时间
        String   str1   =   formatter1.format(curDate);
        Toast.makeText(getApplication(),"1当前时间为："+str1,Toast.LENGTH_SHORT).show();
        String   str2   =   formatter2.format(curDate);
        Toast.makeText(getApplication(),"2当前时间为："+str2,Toast.LENGTH_SHORT).show();


    }



}
