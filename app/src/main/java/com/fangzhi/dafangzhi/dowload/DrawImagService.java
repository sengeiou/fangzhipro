package com.fangzhi.dafangzhi.dowload;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;
import com.fangzhi.dafangzhi.utils.ScreenUtils;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by smacr on 2016/9/29.
 * 下载一张图片
 */
public class DrawImagService {
    /**
     * 单线程列队执行
     */
    private static ExecutorService singleExecutor = null;

    /**
     * 执行单线程列队执行
     */
    public void runOnQueueSingle(Runnable runnable) {
        if (singleExecutor == null) {
            singleExecutor = Executors.newSingleThreadExecutor();

        }
        singleExecutor.submit(runnable);
    }

    private String mapUrl;
    private Handler handler = new Handler();
    private Bitmap bitmap;
    private OnDrawListener listener;
    private int width;
    private int height;
    private boolean isHigh;
    Context context;
    String Url;

    public DrawImagService(Context context, String Url, OnDrawListener listener, boolean isHigh) {
        this.context = context;
        this.Url = Url;
        this.isHigh = isHigh;
        width = ScreenUtils.getScreenWidth(context);
        height = ScreenUtils.getScreenHeight(context);
        this.listener = listener;
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        runOnQueueSingle(new DownLoadThread(Url));
    }

    public void startDraw(String mapUrl) {
        this.mapUrl = mapUrl;
        runOnQueueSingle(new DownLoadThread(mapUrl));
    }

    public interface OnDrawListener {
        void onDrawSucceed(Bitmap bitmap);
    }

    /**
     * 下载线程
     */
    private class DownLoadThread implements Runnable {
        private String url;
        private int index;

        public DownLoadThread(String url) {
            this.url = url;
        }

        @Override
        public void run() {
            SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
            String date = sDateFormat.format(new java.util.Date());
            try {
                if (!isHigh) {
                    bitmap = Glide.with(context)
                            .load(url)
                            .asBitmap()
                            .signature(new StringSignature(date))
                            .into(1280, 720).get();
                } else {
                    bitmap = Picasso.with(context)
                            .load(url)
                            .get();
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onDrawSucceed(bitmap);
                    }
                });

                //waitForComplete(index, bitmap);
            }
        }
    }
}
