package com.fangzhi.dafangzhi.activity.home;


import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.fangzhi.dafangzhi.R;
import com.fangzhi.dafangzhi.activity.main.MainFragment;
import com.fangzhi.dafangzhi.activity.main.MyTouchListener;
import com.fangzhi.dafangzhi.base.BaseFragment;
import com.fangzhi.dafangzhi.base.KLActivityManager;
import com.fangzhi.dafangzhi.base.KLBaseFragmentActivity;
import com.fangzhi.dafangzhi.fragment.collect.CollectFragment;
import com.fangzhi.dafangzhi.fragment.myself.MyselfFragment;
import com.fangzhi.dafangzhi.fragment.shoppingcart.ShoppingFragment;
import com.fangzhi.dafangzhi.views.DialogDelegate;
import com.fangzhi.dafangzhi.views.SweetAlertDialogDelegate;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;

import butterknife.ButterKnife;

import static com.fangzhi.dafangzhi.R.mipmap.selected_color_piece;

/**
 * Created by smacr on 2017/4/6.
 */

public class HomeActivity extends KLBaseFragmentActivity implements View.OnClickListener {

    AutoLinearLayout main;

    AutoLinearLayout collect;

    AutoLinearLayout shop;

    AutoLinearLayout myself;

    AutoRelativeLayout message;

    FrameLayout content_layout;

    ImageView mainimg;

    TextView mainname;

    ImageView collectimg;

    TextView collectname;

    ImageView shopimg;

    TextView shopname;

    ImageView myselfimg;

    TextView myselfname;

    ImageView messageimg;

    BaseFragment Collect, MySelf, Shoping, Main, knowFragment;

    DialogDelegate dialogDelegate;

    /*
    * 保存MyTouchListener接口的列表
    */
    private ArrayList<MyTouchListener> myTouchListeners = new ArrayList<MyTouchListener>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        ButterKnife.bind(this);
        dialogDelegate = new SweetAlertDialogDelegate(this);
        finid();
        main.setOnClickListener(this);
        collect.setOnClickListener(this);
        shop.setOnClickListener(this);
        myself.setOnClickListener(this);
        message.setOnClickListener(this);
        initTab();
    }

    /**
     * 弹窗
     *
     * @param smd
     */
    public void showProgressDialog(String smd) {
        dialogDelegate.showProgressDialog(true, smd);
    }

    /**
     * 关闭等待框
     */
    public void clearDialog() {
        dialogDelegate.clearDialog();
    }

    /**
     * 错误等待框
     */
    public void stopProgressWithFailed(String msg) {
        dialogDelegate.stopProgressWithFailed(msg, "");
    }

    /**
     * 错误等待框
     */
    public void showErrorDialog(String msg) {
        dialogDelegate.showErrorDialog(msg, "", new DialogDelegate.OnDialogListener() {
            @Override
            public void onClick() {
                dialogDelegate.clearDialog();
            }
        });
    }

    public void finid() {
        main = (AutoLinearLayout) findViewById(R.id.main);
        collect = (AutoLinearLayout) findViewById(R.id.collect);
        shop = (AutoLinearLayout) findViewById(R.id.shop);
        myself = (AutoLinearLayout) findViewById(R.id.myself);
        message = (AutoRelativeLayout) findViewById(R.id.message);
        content_layout = (FrameLayout) findViewById(R.id.content_layout);
        mainimg = (ImageView) findViewById(R.id.mainimg);
        mainname = (TextView) findViewById(R.id.mainname);
        collectimg = (ImageView) findViewById(R.id.collectimg);
        collectname = (TextView) findViewById(R.id.collectname);
        shopimg = (ImageView) findViewById(R.id.shopimg);
        shopname = (TextView) findViewById(R.id.shopname);
        myselfimg = (ImageView) findViewById(R.id.myselfimg);
        myselfname = (TextView) findViewById(R.id.myselfname);
        messageimg = (ImageView) findViewById(R.id.messageimg);
    }

    public void change(int num) {

        switch (num) {
            case 0:
                main.setBackgroundResource(R.mipmap.selected_color_piece);
                collect.setBackgroundResource(R.color.white);
                shop.setBackgroundResource(R.color.white);
                myself.setBackgroundResource(R.color.white);
                mainimg.setImageResource(R.mipmap.home_pre);
                collectimg.setImageResource(R.mipmap.collection_icon);
                shopimg.setImageResource(R.mipmap.buying_car);
                myselfimg.setImageResource(R.mipmap.user);
                mainname.setTextColor(getResources().getColor(R.color.white));
                collectname.setTextColor(getResources().getColor(R.color.text_tint));
                shopname.setTextColor(getResources().getColor(R.color.text_tint));
                myselfname.setTextColor(getResources().getColor(R.color.text_tint));
                break;
            case 1:
                main.setBackgroundResource(R.color.white);
                collect.setBackgroundResource(R.mipmap.selected_color_piece);
                shop.setBackgroundResource(R.color.white);
                myself.setBackgroundResource(R.color.white);
                mainimg.setImageResource(R.mipmap.home);
                collectimg.setImageResource(R.mipmap.baby_pre);
                shopimg.setImageResource(R.mipmap.buying_car);
                myselfimg.setImageResource(R.mipmap.user);
                mainname.setTextColor(getResources().getColor(R.color.text_tint));
                collectname.setTextColor(getResources().getColor(R.color.white));
                shopname.setTextColor(getResources().getColor(R.color.text_tint));
                myselfname.setTextColor(getResources().getColor(R.color.text_tint));
                break;
            case 2:
                main.setBackgroundResource(R.color.white);
                collect.setBackgroundResource(R.color.white);
                shop.setBackgroundResource(R.mipmap.selected_color_piece);
                myself.setBackgroundResource(R.color.white);
                mainimg.setImageResource(R.mipmap.home);
                collectimg.setImageResource(R.mipmap.collection_icon);
                shopimg.setImageResource(R.mipmap.buying_car_pre);
                myselfimg.setImageResource(R.mipmap.user);
                mainname.setTextColor(getResources().getColor(R.color.text_tint));
                collectname.setTextColor(getResources().getColor(R.color.text_tint));
                shopname.setTextColor(getResources().getColor(R.color.white));
                myselfname.setTextColor(getResources().getColor(R.color.text_tint));
                break;
            case 3:
                main.setBackgroundResource(R.color.white);
                collect.setBackgroundResource(R.color.white);
                shop.setBackgroundResource(R.color.white);
                myself.setBackgroundResource(R.mipmap.selected_color_piece);
                mainimg.setImageResource(R.mipmap.home);
                collectimg.setImageResource(R.mipmap.collection_icon);
                shopimg.setImageResource(R.mipmap.buying_car);
                myselfimg.setImageResource(R.mipmap.user_pre);
                mainname.setTextColor(getResources().getColor(R.color.text_tint));
                collectname.setTextColor(getResources().getColor(R.color.text_tint));
                shopname.setTextColor(getResources().getColor(R.color.text_tint));
                myselfname.setTextColor(getResources().getColor(R.color.white));
                break;
        }
    }

    /**
     * 添加或者显示碎片
     *
     * @param transaction
     * @param fragment
     */
    private void addOrShowFragment(FragmentTransaction transaction, BaseFragment fragment) {
        if (knowFragment == fragment) {
            return;
        }

        if (!fragment.isAdded()) { // 如果当前fragment未被添加，则添加到Fragment管理器中
            transaction.hide(knowFragment).add(R.id.content_layout, fragment).commit();
        } else {
            transaction.hide(knowFragment).show(fragment).commit();
        }

        knowFragment = fragment;
    }

    /**
     * 初始化底部标签
     */
    private void initTab() {
        if (Main == null) {
            Main = MainFragment.getMainFragment();
        }
        if (!Main.isAdded()) {
            // 提交事务
            getSupportFragmentManager().beginTransaction().add(R.id.content_layout, Main).commit();
            // 记录当前Fragment
            knowFragment = Main;
        }
        change(0);
    }

    /**
     * 主页tab
     */
    private void HomePageLayout() {
        if (Main == null) {
            Main = MainFragment.getMainFragment();
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), Main);
        knowFragment = Main;
        change(0);
    }

    /**
     * 收藏tab
     */
    private void CollectTab() {
        if (Collect == null) {
            Collect = CollectFragment.getMainFragment();
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), Collect);
        knowFragment = Collect;
        change(1);
    }

    /**
     * 购物车tab
     */
    private void shopingTab() {
        if (Shoping == null) {
            Shoping = ShoppingFragment.getMainFragment();
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), Shoping);
        knowFragment = Shoping;
        change(2);
    }

    /**
     * 我的tab
     */
    private void Myself() {
        if (MySelf == null) {
            MySelf = MyselfFragment.getMainFragment();
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(), MySelf);
        knowFragment = MySelf;
        change(3);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            KLActivityManager.instance().finishAllActivity();
            // finish();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 提供给Fragment通过getActivity()方法来注册自己的触摸事件的方法
     *
     * @param listener
     */
    public void registerMyTouchListener(MyTouchListener listener) {
        myTouchListeners.add(listener);
    }

    /**
     * 提供给Fragment通过getActivity()方法来取消注册自己的触摸事件的方法
     *
     * @param listener
     */
    public void unRegisterMyTouchListener(MyTouchListener listener) {
        myTouchListeners.remove(listener);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        for (MyTouchListener listener : myTouchListeners) {
            listener.onTouchEvent(event);
        }
        return super.dispatchKeyEvent(event);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Collect=null;
        MySelf=null;
        Shoping=null;
        Main=null;
        knowFragment=null;
    }

    public void pressUp(final View view) {
        ObjectAnimator anim = ObjectAnimator//
                .ofFloat(view, "zoom", 0.95F, 1.0F)//
                .setDuration(500);//
        anim.start();
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (Float) animation.getAnimatedValue();
                view.setAlpha(cVal);
                view.setScaleX(cVal);
                view.setScaleY(cVal);
            }
        });
    }

    public void pressDown(final View view) {
        ObjectAnimator anim = ObjectAnimator//
                .ofFloat(view, "shrink", 1.0F, 0.95F)//
                .setDuration(500);//
        anim.start();
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (Float) animation.getAnimatedValue();
                view.setAlpha(cVal);
                view.setScaleX(cVal);
                view.setScaleY(cVal);
            }
        });
    }

    /*在fragment的管理类中，我们要实现这部操作，而他的主要作用是，当D这个activity回传数据到
    这里碎片管理器下面的fragnment中时，往往会经过这个管理器中的onActivityResult的方法。*/
 /*   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        *//*然后在碎片中调用重写的onActivityResult方法*//*
        if (resultCode == 1) {
            if (Main != null) {
                Main.onActivityResult(requestCode, resultCode, data);
            }
        }
    }*/

    @Override
    public void onClick(View view) {
        pressUp(view);
        switch (view.getId()) {
            case R.id.main:
                HomePageLayout();
                break;
            case R.id.collect:
                CollectTab();
                break;
            case R.id.shop:
                shopingTab();
                break;
            case R.id.myself:
                Myself();
                break;
            case R.id.message:

                break;
        }
        pressDown(view);
    }
}
