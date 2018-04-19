package com.fangzhi.dafangzhi.activity.materiallist;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.fangzhi.dafangzhi.R;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by smacr on 2017/4/6.
 */

public class ShowImageActivity extends AppCompatActivity {
    Context context = ShowImageActivity.this;
    PhotoView iv_photo1;
    RelativeLayout MainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_image);
        findId();
        init();
    }

    private void findId() {
        iv_photo1 = (PhotoView) findViewById(R.id.iv_photo1);
        MainView = (RelativeLayout) findViewById(R.id.MainView);
    }

    private void init() {
        String url = getIntent().getStringExtra("url");
        Glide.with(context)
                .load(url)
                .placeholder(R.mipmap.zanwei)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(iv_photo1);
        iv_photo1.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {
                finish();
                overridePendingTransition(R.anim.activity_anim_in, R.anim.activity_anim_out);
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            iv_photo1.setVisibility(View.VISIBLE);
            finish();
            overridePendingTransition(R.anim.activity_anim_in, R.anim.activity_anim_out);
        }
        return true;
    }

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