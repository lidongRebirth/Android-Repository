package com.jikexueyuan.com.usingsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by asus tp on 2018/4/25.
 */

public class Db extends SQLiteOpenHelper{
    public static final String  CREATE_BOOK="create table Book("
            +"id integer primary key autoincrement,"
            +"author text,"
            +"price real,"
            +"pages integer,"
            +"name  text)";
    public static final String CREATE_CATEGORY="create table Category("
            +"id integer primary key autoincrement,"
            +"author text,"
            +"price real,"
            +"pages integer,"
            +"name text)";
    private Context mcontext;
    //name :数据库名        CursorFactory：数据库查询结果对象，用来指定创建的工厂   version:存储数据库的版本与onUpgrade相关
    public Db(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context,name,factory,version);
        mcontext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {//创建数据库内部结构
        sqLiteDatabase.execSQL(CREATE_BOOK);//执行SQL语句    官网sqlite.org在这里查如何写SQL语句
        sqLiteDatabase.execSQL(CREATE_CATEGORY);
        Toast.makeText(mcontext,"创建数据库成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {//对数据库进行操作
        sqLiteDatabase.execSQL("drop table if exists Book");
        sqLiteDatabase.execSQL("drop table if exists Category");//DROP语句，若数据库已存在该表，则将其删掉
        onCreate(sqLiteDatabase);//重新建立表
    }

    @Override
    public SQLiteDatabase getWritableDatabase() {//升级数据库
        return super.getWritableDatabase();
    }

    @Override
    public SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }
}
