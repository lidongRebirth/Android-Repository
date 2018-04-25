package com.jikexueyuan.com.readwriteinternaldata;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 * 写入、读取内部存储文件数据
 */
public class MainActivity extends AppCompatActivity {
    private String filename="test";
    private EditText editText;
    private TextView show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText= (EditText) findViewById(R.id.edit_text);
        show= (TextView) findViewById(R.id.show);
        findViewById(R.id.writeBtn).setOnClickListener(new View.OnClickListener() {//写入文件
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream fileOutputStream=openFileOutput(filename, Context.MODE_PRIVATE);//参数：文件名、写入模式
                    OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream,"UTF-8");
                    outputStreamWriter.write(editText.getText().toString()); //往外写出字符串数据
                    outputStreamWriter.flush();//以下两行保证输出缓冲区中的数据
                    fileOutputStream.flush();

                    outputStreamWriter.close();
                    fileOutputStream.close();
                    Toast.makeText(getApplicationContext(),"写入完成",Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.readBtn).setOnClickListener(new View.OnClickListener() { //从内部存储文件中读取
            @Override
            public void onClick(View view) {//读取内部存储文件
                try {
                    FileInputStream fileInputStream=openFileInput(filename);//用来直接读取应用程序内部的数据
                    InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream,"UTF-8");
                    char input[]=new char[fileInputStream.available()];//字符型数组 fileInputStream.available()获取文件可用长度
                    inputStreamReader.read(input);
                    inputStreamReader.close();
                    fileInputStream.close();
                    String readed=new String(input);
                    show.setText(readed);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });


    }
}
