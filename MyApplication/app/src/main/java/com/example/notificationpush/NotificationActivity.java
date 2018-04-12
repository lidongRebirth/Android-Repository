package com.example.notificationpush;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.asustp.myapplication.R;

public class NotificationActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        String data=getIntent().getExtras().getString("text");
        textView= (TextView) findViewById(R.id.notific_text);
        textView.setText(data);

    }
}
