package com.fangzhi.dafangzhi.utils;

import android.util.Log;

public class DLog {
	public static boolean Debug=true;
	public static void v(String tag, String msg) {
		if (Debug) {
			Log.v(tag, msg);
		}
	}
}
