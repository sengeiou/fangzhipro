/**
 * Copyright (c) 2013 SanRenXing, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * SanRenXing, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with SanRenXing.
 */
package com.fangzhi.dafangzhi.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * TODO
 * <p>
 * Title: MyListView.java
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: SanRenXing
 * </p>
 * <p>
 * team: SanRenXingTeam
 * </p>
 * <p>
 *
 * @version 1.0
 * 
 */
public class MyListView extends ListView {

	public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec){  
        int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, mExpandSpec);  
    }  

}
