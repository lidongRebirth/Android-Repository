package com.example.asustp.menulistviewtext;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {
    private List<Menu> menuList=new ArrayList<>();
    private ImageView telButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);
        telButton=(ImageView)findViewById(R.id.telbutton);



        Intent intent=getIntent();
        String data=intent.getStringExtra("key");
        Toast.makeText(getApplicationContext(),"data",Toast.LENGTH_LONG).show();


        //此处有问题已解决
        initMenu();
        MenuAdapter menuAdapter=new MenuAdapter(MenuActivity.this, R.layout.item_menu,menuList);//建立适配器  这个传入的布局是你的一个子项的布局
        ListView menulistView=(ListView)findViewById(R.id.list_view2);
        menulistView.setAdapter(menuAdapter);





        telButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent telIntent=new Intent(Intent.ACTION_DIAL);
                telIntent.setData(Uri.parse("tel:10086"));
                startActivity(telIntent);
            }
        });
    }
    private void initMenu(){//此处从数据库获取来填充数组
        for(int i=0;i<20;i++){
            Menu menu1=new Menu("面条","10元");
            menuList.add(menu1);
            Menu menu2=new Menu("饺子","12元");
            menuList.add(menu2);
        }
    }
}
