package com.fangzhi.dafangzhi.base;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.fangzhi.dafangzhi.utils.DLog;
import com.fangzhi.dafangzhi.utils.StringHelp;


public class KLBaseFragmentActivity extends FragmentActivity {
	private static final String TAG="KLBaseFragmentActivity";
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode== KeyEvent.KEYCODE_BACK)
		{
			this.finish();
			return true;
		}
		else
		{
			return super.onKeyDown(keyCode, event);
		}
	}
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		DLog.v(TAG, this.getClass().toString()+" onStart");
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		DLog.v(TAG, this.getClass().toString()+" onRestart");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		DLog.v(TAG, this.getClass().toString()+" onResume");
	}
	
     protected void onNewIntent(Intent intent){
    	 super.onNewIntent(intent);
    	 DLog.v(TAG, this.getClass().toString()+" onNewIntent");
     }
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		DLog.v(TAG, this.getClass().toString()+" onPause");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		DLog.v(TAG, this.getClass().toString()+" onStop");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//往activity历史链表里增加一个activity
		KLActivityManager.instance().addActivity(this);
		DLog.v(TAG, this.getClass().toString()+" onCreate");
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();		
		//从activity历史链表里删除一个activity
		KLActivityManager.instance().removeActivity(this);
		DLog.v(TAG, this.getClass().toString()+" onDestroy");
	}

	public String  getEdtext(EditText editText){
		String src="";
		if (StringHelp.checkNull(editText.getText().toString().trim())){
			src=editText.getText().toString().trim();
		}else {

		}
		return src;
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		//    super.onWindowFocusChanged(hasFocus);
		if (hasFocus && Build.VERSION.SDK_INT >= 19) {
			View decorView = getWindow().getDecorView();
			decorView.setSystemUiVisibility(
					View.SYSTEM_UI_FLAG_LAYOUT_STABLE
							| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
							| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
							| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
							| View.SYSTEM_UI_FLAG_FULLSCREEN
							| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
		}
	}
}
