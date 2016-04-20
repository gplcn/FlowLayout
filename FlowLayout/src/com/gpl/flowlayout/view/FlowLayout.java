package com.gpl.flowlayout.view;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;
/**
 * 该类通过对子view的自定义测量和摆放实现文字瀑布流的效果
 * 想要更改子view水平和垂直间距大小
 * 只需更改horizotalSpacing和verticalSpacing两个成员变量的值大小即可
 * @author GPL
 */
public class FlowLayout extends ViewGroup {

	/** 保存一行的TextView */
	private ArrayList<TextView> oneLine ;
	/** 保存所有行TextView*/
	private ArrayList<ArrayList<TextView>> allLines=new ArrayList<ArrayList<TextView>>();
	/**父容器的宽度*/
	private int convertViewWidth;
	/**水平间距*/
	private int horizotalSpacing = 6;
	/**垂直间距*/
	private int verticalSpacing = 6;

	public FlowLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public FlowLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public FlowLayout(Context context) {
		this(context, null, 0);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		allLines.clear();
		//获取父容器宽度
		convertViewWidth = MeasureSpec.getSize(widthMeasureSpec);

		for (int i = 0; i <getChildCount(); i++) {
			getChildAt(i).measure(0, 0);
		}
		
		// 遍历所有的子View 将所有子View分行(如果是第一个childView或者一行剩余的宽度盛不下childView时则另起一行)
		for (int i = 0; i < getChildCount(); i++) {

			int usableWidth = getUseableWidth(oneLine);
			
			int childWidth = getChildAt(i).getMeasuredWidth();
			if(i==0||childWidth > usableWidth){
				oneLine= new ArrayList<TextView>();
				allLines.add(oneLine);
			}
			oneLine.add((TextView) getChildAt(i));
			
		}
		
		int convertViewHeight = getAllLinesHeight();
		// 设置FlowLayout的宽和高，宽就用父容器传的宽，高用子View的高(这个设置非常重要)
		setMeasuredDimension(convertViewWidth, convertViewHeight);
		
	}

	/**获取所有行的总高度*/
	public int getAllLinesHeight() {
		return allLines.size()*getChildAt(0).getMeasuredHeight()
				+getPaddingBottom()+getPaddingTop()
				+(allLines.size()-1)*verticalSpacing;
	}

	/**获得一行的可用宽度*/
	public int getUseableWidth(ArrayList<TextView> oneLine) {
		
		// 可用剩余宽度
		int usableWidth = convertViewWidth - getPaddingLeft() - getPaddingRight()-getOneLineWidth(oneLine);
		return usableWidth;
		
	}
	
	/**获得一行的已用宽度*/
	private int getOneLineWidth(ArrayList<TextView> oneLine) {

		if(oneLine==null)
			return 0;
		int  usedWidth=0;
		for (TextView textView : oneLine) {
			usedWidth+=textView.getMeasuredWidth();
		}
		return usedWidth+(oneLine.size()-1)*horizotalSpacing;
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		
		if (allLines.isEmpty()) {
			return;
		}
		System.out.println("allLines.size()=="+allLines.size());

		// 下一个View的layout的top
		int nextTop = getPaddingTop()+t;
		
		// 遍历每一行 给每一个子View布局
		for (int i = 0; i < allLines.size(); i++) {

			// 获得第i行
			ArrayList<TextView> mOneLine = allLines.get(i);
			//计算剩余宽度
			int unUsedlineWidth = getUseableWidth(mOneLine);
			//计算平均分配的剩余宽度
			int mOneLineSize = mOneLine.size();
			int averageWidth=unUsedlineWidth/mOneLineSize;
			// 下一个View的layout的left
			int nextLeft = getPaddingLeft()+l;

			for (int j = 0; j < mOneLineSize; j++) {
				
				TextView textView = mOneLine.get(j);

				// 实际布局时的四个边的位置
				//如果不是第一列则都加一个间距
				int left = j==0?nextLeft:nextLeft+horizotalSpacing;
				
				int top = nextTop;
				//计算右边框位置(并考虑最外边框对齐问题)
				int right = 	j==mOneLineSize-1
								?convertViewWidth-getPaddingRight()
								:left + textView.getMeasuredWidth()+averageWidth;
				int bottom = top + textView.getMeasuredHeight();

				// 更新下个一View的坐标
				nextLeft = right;

				// 布局
				textView.layout(left, top, right, bottom);
				
				//重新测量让text居中
				int widthMeasureSpec = MeasureSpec.makeMeasureSpec(right-left, MeasureSpec.EXACTLY);
				textView.measure(widthMeasureSpec, 0);
				
				nextTop = (j==mOneLineSize-1) ? bottom+verticalSpacing:top;
			}
			
		}

	}

}
