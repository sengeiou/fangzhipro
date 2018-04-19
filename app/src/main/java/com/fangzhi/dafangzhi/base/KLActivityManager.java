package com.fangzhi.dafangzhi.base;

import android.app.Activity;

import com.fangzhi.dafangzhi.utils.DLog;

import java.util.ArrayList;
import java.util.List;

/**
 * activity管理类
 */
public class KLActivityManager {
	private static final String TAG = "KLActivityManager";
	private static KLActivityManager instance;

	/**
	 * 单例模式
	 * @return
	 */
	public static KLActivityManager instance() {
		if (instance==null) {
			instance=new KLActivityManager();
		}
		return instance;
	}
	
	// 整个应用的activity历史链表
	public List<Activity> liveActivityList = new ArrayList<Activity>();
	
	
	/**
	 * 往activity链表里增加一个activity
	 * 
	 * @param activity
	 */
	public void addActivity(Activity activity) {
		liveActivityList.add(activity);
		DLog.v(TAG, "addActivity:" + activity.getClass().toString());
	}

	/**
	 * 从activity历史链表里删除一个activity
	 * 
	 * @param activity
	 */
	public void removeActivity(Activity activity) {
		liveActivityList.remove(activity);
		DLog.v(TAG, "removeActivity:" + activity.getClass().toString());
	}

	/**
	 * 将activity历史链表里的所有activity结束掉
	 */
	public void finishAllActivity() {
		DLog.v(TAG, "finishAllActivity");
		for (int i = 0; i < liveActivityList.size(); i++) {
			liveActivityList.get(i).finish();
		}
		liveActivityList.clear();
	}

	/**
	 * 关闭所有后台运行的activity
	 */
	public void finishAllPauseActivity() {
		DLog.v(TAG, "finishAllPauseActivity");
		while (liveActivityList.size() > 1) {
			liveActivityList.get(0).finish();
			liveActivityList.remove(0);
		}
	}

	public int activityNum() {
		return liveActivityList.size();
	}


	/**
	 * 退出登录
	 */
	public void closs(){
		for (int i=1;i<liveActivityList.size();i++){
			liveActivityList.get(i).finish();
		}
	}
	/**
	 * 关闭界面
	 */
	public void finishactivity(int k){
			liveActivityList.get(k).finish();
	}
}
