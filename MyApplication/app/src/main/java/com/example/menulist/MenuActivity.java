package com.example.menulist;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.asustp.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {
    private List<Menu> menuList=new ArrayList<>();
    private ImageView telButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.four_menu_activity);
        getSupportActionBar().setTitle("菜单");
        telButton=(ImageView)findViewById(R.id.telbutton);


        Intent intent=getIntent();
        final int storeID=intent.getIntExtra("key",100);
        final String tel=intent.getStringExtra("tel");


        //此处有问题已解决
        initMenu(storeID);
        MenuAdapter menuAdapter=new MenuAdapter(MenuActivity.this, R.layout.four_item_menu,menuList);//建立适配器  这个传入的布局是你的一个子项的布局
        ListView menulistView=(ListView)findViewById(R.id.menu_list_view);
        menulistView.setAdapter(menuAdapter);





        telButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent telIntent=new Intent(Intent.ACTION_DIAL);
                telIntent.setData(Uri.parse("tel:"+tel+""));
                startActivity(telIntent);
            }
        });
    }
    private void initMenu(int storeID){//此处从数据库获取来填充数组

        switch (storeID){
            case 1:
                Menu []menus1=new Menu[14];
                menus1[0]=new Menu("1号","名客佳大鸡排");
                menus1[1]=new Menu("招牌鸡排","13");
                menus1[2]=new Menu("爆浆鸡排","13");
                menus1[3]=new Menu("叉烧肉","15");
                menus1[4]=new Menu("肉夹馍","4");
                menus1[5]=new Menu("鸡腿堡","7");
                menus1[6]=new Menu("邪恶鸡排堡","6");
                menus1[7]=new Menu("七虾堡","9");
                menus1[8]=new Menu("牛肉堡","6");
                menus1[9]=new Menu("鸡翅包饭","12");
                menus1[10]=new Menu("黄金鸡米花","8");
                menus1[11]=new Menu("雪花鸡柳","7");
                menus1[12]=new Menu("洋葱鸡肉圈","7");
                menus1[13]=new Menu("薯条","6");
                for(int i=0;i<menus1.length;i++){
                    menuList.add(menus1[i]);
                }
                break;
            case 2:
                Menu []menus2=new Menu[21];
                menus2[0]=new Menu("2号","金三顺紫菜包饭");
                menus2[1]=new Menu("肉松沙拉紫菜包饭","9");
                menus2[2]=new Menu("泡菜紫菜包饭","9");
                menus2[3]=new Menu("蔬菜紫菜包饭","9");
                menus2[4]=new Menu("山楂紫菜包饭","10");
                menus2[5]=new Menu("芝士紫菜包饭","10");
                menus2[6]=new Menu("蟹肉棒紫菜包饭","10");
                menus2[7]=new Menu("传统石锅拌饭","12");
                menus2[8]=new Menu("辣白菜石锅拌饭","14");
                menus2[9]=new Menu("鸡柳石锅拌饭","14");
                menus2[10]=new Menu("金针菇石锅拌饭","14");
                menus2[11]=new Menu("培根石锅拌饭","14");
                menus2[12]=new Menu("金枪鱼石锅拌饭","17");
                menus2[13]=new Menu("传统炒年糕","5");
                menus2[14]=new Menu("辣白菜年糕","6");
                menus2[15]=new Menu("培根炒年糕","10");
                menus2[16]=new Menu("海鲜炒年糕","10");
                menus2[17]=new Menu("传统辛拉面","11");
                menus2[18]=new Menu("辣白菜辛拉面","13");
                menus2[19]=new Menu("年糕辛拉面","15");
                menus2[20]=new Menu("海鲜辛拉面","17");
                for(int i=0;i<menus2.length;i++){
                    menuList.add(menus2[i]);
                }
                break;
            case 3:
                Menu menu3=new Menu("3号","10元");
                menuList.add(menu3);
                break;
            case 4:
                Menu menu4=new Menu("4号","10元");
                menuList.add(menu4);
                break;
            case 5:
                Menu menu5=new Menu("5号","10元");
                menuList.add(menu5);
                break;
            case 6:
                Menu menu6=new Menu("6号","10元");
                menuList.add(menu6);
                break;
            case 7:
                Menu menu7=new Menu("7号","10元");
                menuList.add(menu7);
                break;
            case 8:
                Menu menu8=new Menu("8号","10元");
                menuList.add(menu8);
                break;
            case 9:
                Menu menu9=new Menu("9号","10元");
                menuList.add(menu9);
                break;
            case 10:
                Menu menu10=new Menu("10号","10元");
                menuList.add(menu10);
                break;
            default:
                break;

        }



//        for(int i=0;i<20;i++){
//            Menu menu1=new Menu("面条","10元");
//            menuList.add(menu1);
//            Menu menu2=new Menu("饺子","12元");
//            menuList.add(menu2);
//        }
    }
}
