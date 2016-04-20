# FlowLayout
##简介
&emsp;&emsp;&nbsp;	这个工程主要实现了一个文字瀑布流的布局效果,根据文字长度自适应摆放位置,自分配占据的宽度;
	工程中最主要的类是FlowLayout.java;
	该类是自定义一个ViewGroup,往里面添加TextView,它会自动摆放为瀑布流的效果;
	另一个类是UIUtil.java该类提供的方法createRandomBackgroundSelector()可以生成一个随机颜色的状态选择器;
	如果在往FlowLayout里添加TextView时利用UIUtil的中方法设背景为随机颜色的状态选择器
	会使得瀑布流效果更加绚丽;
##使用帮助
&emsp;&emsp;&nbsp;	
1.在布局文件中引用FlowLayout:

 	<com.gpl.flowlayout.view.FlowLayout
        android:padding="6dp"
        android:id="@+id/flow_layout"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" >

    </com.gpl.flowlayout.view.FlowLayout>
&emsp;&emsp;&nbsp;
2.初始化View时在代码中生成TextView,并添加到FlowLayout容器中

	FlowLayout flow_layout= (FlowLayout) findViewById(R.id.flow_layout);      
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
##实现效果
![Alt text](https://github.com/gplcn/FlowLayout/raw/master/Screenshots/Screenshot01.png)</br>
![Alt text](https://github.com/gplcn/FlowLayout/raw/master/Screenshots/Screenshot02.png)