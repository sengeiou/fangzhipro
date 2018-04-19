package com.fangzhi.dafangzhi.network;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.zhy.http.okhttp.OkHttpUtils;

import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhangyu on 2016/6/15.
 */
 public class Network {
    private static ApiService apiService;

    private static final Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static final CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();


    public static ApiService getApiService() {
        if (apiService == null) {
            apiService = getRetrofit().create(ApiService.class);
        }
        return apiService;
    }


    private static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(ApiUrl.BASE_URL)
                .callFactory(OkHttpUtils.getInstance().getOkHttpClient())
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();
    }

    /**
     * 检查网络连接状态
     *
     * @param context
     * @return true:网络可用 <br>
     * false:网络不可用
     */
    public synchronized static boolean checkNetWork(Context context) {
        boolean status = false;
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null) {
            status = networkInfo.isConnected();
        }
        return status;
    }

    /**
     * 判断是否是wifi连接
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm == null)
            return false;
        return cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;

    }

    /**
     * 打开网络设置界面
     */
    public static void openSetting(Activity activity) {
        Intent intent = null;
        /**
         * 判断手机系统的版本！如果API大于10 就是3.0+
         * 因为3.0以上的版本的设置和3.0以下的设置不一样，调用的方法不同
         */
        if (android.os.Build.VERSION.SDK_INT > 10) {
            intent = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
        } else {
            intent = new Intent();
            ComponentName component = new ComponentName(
                    "com.android.settings",
                    "com.android.settings.WirelessSettings");
            intent.setComponent(component);
            intent.setAction("android.intent.action.VIEW");
        }
        activity.startActivity(intent);
    }
}
