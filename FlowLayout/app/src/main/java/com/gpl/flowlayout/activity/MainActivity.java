package com.gpl.flowlayout.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

import com.gpl.flowlayout.view.FlowLayout;
import com.gpl.flowlayout.util.UIUtil;
import com.nf.gpl.app.R;

import java.util.ArrayList;

public class MainActivity extends Activity {

	private FlowLayout flow_layout;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flow_layout= (FlowLayout) findViewById(R.id.flow_layout);
       
       for (String  str : list) {
		
    	   TextView tv=new TextView(getApplicationContext());
    	   tv.setTextColor(Color.WHITE);
    	   tv.setText(str);
    	   tv.setGravity(Gravity.CENTER);
    	   int padding=6;
		tv.setPadding(padding, padding, padding, padding);
		//设置背景颜色随机的状态选择器
    	   tv.setBackgroundDrawable(UIUtil.createRandomBackgroundSelector());
    	   flow_layout.addView(tv);
	}
    }


   
    
    
    public static ArrayList<String> list = new ArrayList<String>();
    static {
    	list.add("主要看气质");
    	list.add("也是蛮拼的");
    	list.add("人挺不错");
    	list.add("只想安静地做个美男子");
    	list.add("互联网+");
    	list.add("你家里人知道吗?");
    	list.add("也是醉了!");
    	list.add("且行且珍惜");
    	list.add("No zuo no die");
    	list.add("心灵若相知，更要珍惜");
    	list.add("咬文嚼字");
    	list.add("脑洞大开");
    	list.add("三年之后又三年");
    	list.add("创客");
    	list.add("剁手党");
    	list.add("侧耳听风，倾身闻语");
    	list.add("承包鱼塘体");
    	list.add("有钱就这么任性");
    	list.add("人生能相遇，已是不易");
    	list.add("行万里路，不如种一棵树");
    	list.add("带我装X带我飞");
    	list.add("宝宝");
    	list.add("都是小事");
    	list.add("我想做个好人");
    	list.add("任性");
    	list.add("从来只有事情改变人");
    	list.add("多做善事");
    	list.add("网红");
    	list.add("挖掘机哪家强 中国山东找蓝翔");
    	list.add("你懂与不懂");
    	list.add("来来往往");
    }

}
