package com.jikexueyuan.com.usingsqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Data information";
    private Db dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper=new Db(this,"BookStore.db",null,2);
        Button createDatabase= (Button) findViewById(R.id.createBtn);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.getWritableDatabase();//创建数据库
                Toast.makeText(getApplication(),"进入点击事件",Toast.LENGTH_SHORT).show();
            }
        });

//添加数据操作
        Button addData= (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                ContentValues values =new ContentValues();
                values.put("name","The Da Vinci Code");
                values.put("author","Dan Brown");
                values.put("pages",454);
                values.put("price",16.96);
                db.insert("Book",null,values);
                values.clear();
                //开始组装第二条数据
                db.execSQL("insert into Book(name,author,pages,price) values(?,?,?,?)",new String[]{"The Lost Symbol","Dan Brown","510","19.95"});


            }
        });
//更新操作
        Button updateData= (Button) findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put("price",10.99);
                db.update("Book",values,"name=?",new String []{"The Lost Symbol"});//3参数相当于where4参数用来为第三个参数每个占位符指定相应的内容

                //SQL方式可以实现
//                db.execSQL("update Book set price=? where name=?",new String[]{"10.99","the Da Vinci Code"});

            }
        });
//查询数据
        Button queryData= (Button) findViewById(R.id.query_data);
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                //查询Book表中所有数据
                Cursor cursor=db.query("Book",null,null,null,null,null,null);
                if(cursor.moveToFirst()){
                    do{
                        //遍历Cursor对象，取出数据并打印
                        String name=cursor.getString(cursor.getColumnIndex("name"));
                        String author=cursor.getString(cursor.getColumnIndex("author"));
                        int pages=cursor.getInt(cursor.getColumnIndex("pages"));
                        double price=cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d(TAG, "book name is"+name);
                        Log.d(TAG, "author is"+author);
                        Log.d(TAG, "book pages is"+pages);
                        Log.d(TAG, "book price is"+price);
                    }while (cursor.moveToNext());
                }
                cursor.close();
//执行SQL语句
//                Cursor cursor2=db.rawQuery("select * from Book",null);
//                if(cursor2.moveToFirst()){
//                    do{
//                        //遍历Cursor对象，取出数据并打印
//                        String name=cursor2.getString(cursor.getColumnIndex("name"));
//                        String author=cursor2.getString(cursor.getColumnIndex("author"));
//                        int pages=cursor2.getInt(cursor.getColumnIndex("pages"));
//                        double price=cursor2.getDouble(cursor.getColumnIndex("price"));
//                        Log.d(TAG, "book name is"+name);
//                        Log.d(TAG, "author is"+author);
//                        Log.d(TAG, "book pages is"+pages);
//                        Log.d(TAG, "book price is"+price);
//                    }while (cursor.moveToNext());
//                }
//                cursor2.close();
            }
        });


    }
}
