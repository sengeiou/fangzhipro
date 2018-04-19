package com.fangzhi.dafangzhi.activity.forget;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.fangzhi.dafangzhi.R;
import com.fangzhi.dafangzhi.activity.login.LoginActivity;
import com.fangzhi.dafangzhi.base.BaseBean;
import com.fangzhi.dafangzhi.base.KLActivityManager;
import com.fangzhi.dafangzhi.network.ErrorCode;
import com.fangzhi.dafangzhi.network.MySubscriber;
import com.fangzhi.dafangzhi.network.Network;
import com.fangzhi.dafangzhi.utils.MD5Util;
import com.fangzhi.dafangzhi.utils.StringHelp;
import com.fangzhi.dafangzhi.utils.T;
import com.fangzhi.dafangzhi.views.DialogDelegate;
import com.fangzhi.dafangzhi.views.SweetAlertDialogDelegate;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by smacr on 2017/3/21.
 */

public class SetPasswordActivity extends SwipeBackActivity {
    Context context = SetPasswordActivity.this;
    DialogDelegate dialogDelegate;


    @Bind(R.id.password)
    EditText password;  //密码

    String phone; //手机号
    private Subscription mDownloadSp;

    KLActivityManager klActivityManager=KLActivityManager.instance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setpassword_activity);
        klActivityManager.addActivity(this);
        ButterKnife.bind(this);
        dialogDelegate = new SweetAlertDialogDelegate(this);
        if (getIntent() != null && StringHelp.checkNull(getIntent().getStringExtra("phone"))) {
            phone = getIntent().getStringExtra("phone");
        } else {
            T.showString(context, "系统异常");
            finish();
        }


    }

    @OnClick(R.id.back)
    public void back() {
        finish();
    }

    @OnClick(R.id.confirm)
    public void confirm() {
        String passw = password.getText().toString().trim();
        if (StringHelp.checkNull(passw)) {

        } else {
            T.showString(context, "请输入密码");
            return;
        }
        if (passw.length() >= 6 && passw.length() <= 12) {

        } else {
            T.showString(context, "请输入6-12位密码");
            return;
        }
        if (Network.checkNetWork(context)){

        }else {
            //T.showString(RegisterActivity.this, "查看网络是否连接");
            dialogDelegate.showErrorDialog("无网络", "请查看网络是否连接", new DialogDelegate.OnDialogListener() {
                @Override
                public void onClick() {
                    dialogDelegate.clearDialog();
                }
            });
            return;
        }
        dialogDelegate.showProgressDialog(true, "正在修改...");
        SetModify(passw);
    }

    public void SetModify(String passw) {
        mDownloadSp = Network.getApiService().FzAction_MODIFY(phone, passw, GetMD5()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new MySubscriber<BaseBean>() {
                    @Override
                    public void onNext(final BaseBean BaseBean) {
                        if (BaseBean.getError_code().equals(ErrorCode.SUCCEED)){
                            dialogDelegate.stopProgressWithSuccess(BaseBean.getMsg(), "", new DialogDelegate.OnDialogListener() {
                                @Override
                                public void onClick() {
                                    dialogDelegate.stopProgressWithSuccess(BaseBean.getMsg(), "", new DialogDelegate.OnDialogListener() {
                                        @Override
                                        public void onClick() {
                                            Intent intent = new Intent(context, LoginActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(intent);
                                        }
                                    });

                                }
                            });

                        }else {
                            dialogDelegate.stopProgressWithFailed(BaseBean.getMsg(),"");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        dialogDelegate.stopProgressWithFailed("服务器连接失败","");
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

    public String GetMD5() {
        return MD5Util.getInstance().getMD5(new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()) + "fangzhi");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDownloadSp != null) {
            mDownloadSp.unsubscribe();
        }
        ButterKnife.unbind(this);
        klActivityManager.removeActivity(this);
    }
}
