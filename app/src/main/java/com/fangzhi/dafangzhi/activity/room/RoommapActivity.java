package com.fangzhi.dafangzhi.activity.room;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.fangzhi.dafangzhi.R;
import com.fangzhi.dafangzhi.dowload.DrawImagService;
import com.fangzhi.dafangzhi.views.loading.AVLoadingIndicatorView;
import com.fangzhi.dafangzhi.views.loading.BallSpinFadeLoaderIndicator;

import java.text.SimpleDateFormat;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by smacr on 2017/3/10.
 */

public class RoommapActivity extends AppCompatActivity {

    Context mContext = RoommapActivity.this;
    //@Bind(R.id.iv_image)
    PhotoView photoView;
    //@Bind(R.id.avi)
    DrawImagService drawImagService;
    ImageView iv_close;

    AVLoadingIndicatorView avLoadingIndicatorView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roommap);
        photoView = (PhotoView) findViewById(R.id.iv_photo1);
        iv_close = (ImageView) findViewById(R.id.iv_close);
        avLoadingIndicatorView = (AVLoadingIndicatorView) findViewById(R.id.avi);
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),R.mipmap.zanwei);
        photoView.setImageBitmap(bitmap);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String Url = bundle.getString("url");
        BallSpinFadeLoaderIndicator indicator = new BallSpinFadeLoaderIndicator();
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String date = sDateFormat.format(new java.util.Date());
        avLoadingIndicatorView.setVisibility(View.VISIBLE);
        drawImagService = new DrawImagService(mContext,Url, new DrawImagService.OnDrawListener() {
            @Override
            public void onDrawSucceed(Bitmap bitmap) {
                photoView.setImageBitmap(bitmap);
                avLoadingIndicatorView.setVisibility(View.INVISIBLE);
            }
        }, true);

/*        Glide.with(RoommapActivity.this)
                .load(Url)
                .signature(new StringSignature(date))
                .into(scaleImageView);*/

        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
