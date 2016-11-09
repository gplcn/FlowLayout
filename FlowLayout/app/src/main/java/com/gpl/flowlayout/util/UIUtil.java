package com.gpl.flowlayout.util;

import java.util.Random;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

/**
 * 该工具类主要提供随机颜色的背景图片(圆角矩形),随机颜色的背景选择器;
 * @author GPL
 *
 */
public class UIUtil {


	/**
	 * 返回一个随机的颜色值
	 * @return
	 */
	public static int createRandomColor() {
		Random random=new Random();

		int red =  random.nextInt(200); 	// 0 ~ 199的随机颜色值
		int green = random.nextInt(200);  	// 0 ~ 199的随机颜色值
		int blue = random.nextInt(200); 	// 0 ~ 199的随机颜色值
		return Color.rgb(red, green, blue);
	}
	/**
	 * 生成一个随机颜色的状态选择器
	 * @return
	 */
	public static Drawable createRandomBackgroundSelector() {
		StateListDrawable stateListDrawable=new StateListDrawable();
		//按下状态
		int[] pressedStateSet=new int[]{android.R.attr.state_enabled, android.R.attr.state_pressed};
		//正常状态
		int[] normalStateSet=new int[]{};
		stateListDrawable.addState(pressedStateSet, createRandomColorBackground());
		stateListDrawable.addState(normalStateSet, createRandomColorBackground());
		return stateListDrawable;
	}
	/**
	 * 生成一个随机颜色背景图片 边框为圆角矩形
	 * @return
	 */
	public static Drawable createRandomColorBackground() {
		 GradientDrawable gradientDrawable = new GradientDrawable();
		 gradientDrawable.setCornerRadius(5);
		 gradientDrawable.setShape(GradientDrawable.RECTANGLE);
		gradientDrawable.setColor(UIUtil.createRandomColor());
		return gradientDrawable;
	}
}

	