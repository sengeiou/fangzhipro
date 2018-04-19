package com.fangzhi.dafangzhi.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.WindowManager;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHelp {
	/**
	 * 判断字符串是否为空或者为""
	 * @param str
	 * @return 为空返回false,否则返回true
	 */
	public static boolean checkNull(String str) {

		if (str == null || str.trim().equals("") || str.length() == 0
				|| str.equals("[]") || str.trim().equals("null")) {
			return false;
		}
		return true;
	}


	public static boolean isEmpty(String s) {
		if (null == s)
			return true;
		if (s.length() == 0)
			return true;
		if (s.trim().length() == 0)
			return true;
		return false;
	}

	// 得到本机Mac地址
	public static String getLocalMac(Context context)
	{
		String mac = "";
		// 获取wifi管理器
		WifiManager wifiMng = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiInfor = wifiMng.getConnectionInfo();
		mac = wifiInfor.getMacAddress();
		Log.i("TT", "本机的mac地址是：" + mac);
		return mac;
	}

	/**
	 * 得到本机Mac地址
	 * 没有wifi也可以不
	 * @return
	 */
	public static String getWifiMac() {
		String macSerial = "";
		String str = "";
		LineNumberReader lineNumberReader = null;
		try {
			InputStreamReader is = new InputStreamReader(new FileInputStream(new File("/sys/class/net/wlan0/address")));
			lineNumberReader = new LineNumberReader(is);

			while (str != null) {
				str = lineNumberReader.readLine();
				if (str != null) {
					macSerial = str.trim();
					break;
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (lineNumberReader != null)
				try {
					lineNumberReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return macSerial;
	}

	/** 
	 * BASE64 加密 
	 * @param str 
	 * @return 
	 */  
	public static String encryptBASE64(String str) {
		if (str == null || str.length() == 0) {  
			return null;  
		}  
		try {  
			byte[] encode = str.getBytes("UTF-8");  
			// base64 加密  
			return new String(Base64.encode(encode, 0, encode.length, Base64.NO_WRAP), "UTF-8");

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();  
		}  

		return null;  
	}  

	// MD5加密，32位
	public static String MD5(String str) {
		MessageDigest md5 =null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch(Exception e) {
			e.printStackTrace();
			return "";
		}

		char[] charArray = str.toCharArray();
		byte[] byteArray =new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++) {
			byteArray[i] = (byte) charArray[i];
		}
		byte[] md5Bytes = md5.digest(byteArray);

		StringBuffer hexValue =new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) &0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	// 可逆的加密算法
	public static String encryptmd5(String str) {
		char[] a = str.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^'l');
		}
		String s = new String(a);
		return s;
	}

	//文字适配
	public static int getFontSize(Context context, int textSize) {
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		windowManager.getDefaultDisplay().getMetrics(dm);
		int screenHeight= dm.heightPixels;
		// screenWidth = screenWidth > screenHeight ?screenWidth :
			// screenHeight;
			int rate = (int) (textSize * (float) screenHeight / 1280);
		return rate;
	}
	
	//文字适配
	public static int getDPSize(Context context, int pad) {
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		windowManager.getDefaultDisplay().getMetrics(dm);
		int screenHeight= dm.heightPixels;
		int screenwidth = dm.widthPixels;
		//int screenWidth = screenWidth > screenHeight ?screenWidth :screenHeight;
		int padding =4;
		padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, pad,context.getResources().getDisplayMetrics());
		int rate = (int) (pad * (float) screenHeight / 1280);
		return rate;
	}

	/**
	 * 判断字符串是否为空或者为""
	 * @param text
	 * @return 为空返回false,否则返回true
	 */
	public static boolean getEdtext(EditText text) {
		if (StringHelp.checkNull(text.getText().toString().trim())){
			return true;
		}
		return false;
	}

	/**
	 * 验证手机号
	 */
	public static boolean checkPhone(String phone) {
		if (phone.matches("[1][34578]\\d{9}")) {
			return true;
		} else {
			return false;
		}
	}
	/** 验证用户名 */
	public static boolean userNameValidation(String username) {
		String str = "^(?:\\w|\\-|[\u4e00-\u9fa5])+$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(username);
		return m.matches();
	}
	/** 验证中文名 */
	public static boolean chinesename(String username) {
		String str = "^[\u4e00-\u9fa5]{2,50}$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(username);
		return m.matches();
	}
	/**
	 * 判断身份证
	 */
	public static boolean personIdValidation(String text) {
		String regx = "[0-9]{17}x";
		String reg1 = "[0-9]{15}";
		String regex = "[0-9]{18}";
		boolean flag = text.matches(regx) || text.matches(reg1) || text.matches(regex);
		return flag;
	}

}
