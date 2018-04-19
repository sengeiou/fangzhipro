package com.fangzhi.dafangzhi.activity.forget;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.widget.EditText;
import android.widget.TextView;

import com.fangzhi.dafangzhi.R;
import com.fangzhi.dafangzhi.base.BaseActivity;
import com.fangzhi.dafangzhi.listener.NoDoubleClickListener;
import com.fangzhi.dafangzhi.network.Network;
import com.fangzhi.dafangzhi.utils.MD5Util;
import com.fangzhi.dafangzhi.utils.StringHelp;
import com.fangzhi.dafangzhi.utils.T;
import com.fangzhi.dafangzhi.views.DialogDelegate;
import com.fangzhi.dafangzhi.views.SweetAlertDialogDelegate;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by smacr on 2017/3/21.
 */

public class ForgetActivity extends BaseActivity<ForgetPresenter,ForgetModel> implements ForgetContract.View {

    Context context=ForgetActivity.this;
    @Bind(R.id.next)
    TextView next;

    @Bind(R.id.phone)
    EditText phone;  //手机号

    @Bind(R.id.verification)
    EditText verification;  //验证码

    @Bind(R.id.verify)
    TextView verify;  //验证

    DialogDelegate dialogDelegate;
    private CountDownTimer mTimer;
    private int mCount = 60;

    @Override
    public int getLayoutId() {
        return R.layout.forget_activity;
    }

    @Override
    public void initView() {
        dialogDelegate = new SweetAlertDialogDelegate(this);
        next.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(int position) {
                if (StringHelp.checkNull(GetPhone()) && StringHelp.checkPhone(GetPhone())) {

                } else {
                    T.showString(context, "请输入正确的手机号码");
                    return;
                }

                if (StringHelp.checkNull(GetVerification())){

                }else {
                    T.showString(context, "请输入验证码");
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
                dialogDelegate.showProgressDialog(true, "正在注册...");
                mPresenter.queryVerCode();

            }
        });

        mTimer = new CountDownTimer(1000 * 60, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                verify.setEnabled(false);
                verify.setText(mCount + "秒后再次获取");
                mCount--;
            }

            @Override
            public void onFinish() {
                verify.setText("验证码");
                verify.setEnabled(true);
                mCount = 60;
            }
        };
    }

    @Override
    public void tokenInvalid(String msg) {

    }

    @Override
    public void onError(String msg) {
        dialogDelegate.stopProgressWithFailed(msg,"");
    }

    @OnClick(R.id.back)
    public void back(){
        finish();
    }


    /**
     * 获取手机号
     *
     * @return
     */
    @Override
    public String GetPhone() {
        return getEdtext(phone);
    }

    /**
     * 获取验证码
     *
     * @return
     */
    @Override
    public String GetVerification() {
        return getEdtext(verification);
    }

    /**
     * 获取md5
     *
     * @return
     */
    @Override
    public String GetMD5() {
        return MD5Util.getInstance().getMD5(new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()) + "fangzhi");
    }

    /**
     * 获取验证码成功
     */
    @Override
    public void GetSucceed(String msg) {
        dialogDelegate.clearDialog();
        mTimer.start();
        T.showString(ForgetActivity.this,msg);
    }

    /**
     * 获取验证码失败
     *
     * @param msg
     */
    @Override
    public void GetFailed(String msg) {
        dialogDelegate.stopProgressWithFailed(msg,"");
    }

    @Override
    public void VerifySucceed(String msg) {
        dialogDelegate.clearDialog();
       // mPresenter.register();
        Intent intent=new Intent(ForgetActivity.this, SetPasswordActivity.class);
        intent.putExtra("phone",GetPhone());
        startActivity(intent);
    }

    @Override
    public void VerifyFailed(String msg) {
        dialogDelegate.stopProgressWithFailed(msg,"");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        dialogDelegate.clearDialog();
        mTimer.cancel();
    }

    /**
     * 获取验证码
     */
    @OnClick(R.id.verify)
    public void gainverify() {
        if (StringHelp.checkNull(GetPhone()) && StringHelp.checkPhone(GetPhone())) {

        } else {
            T.showString(this, "请输入正确的手机号码");
            return;
        }
        if (Network.checkNetWork(this)){

        }else {
            //T.showString(RegisterActivity.this, "查看网络是否连接");
            dialogDelegate.showErrorDialog("无网络", "查看网络是否连接", new DialogDelegate.OnDialogListener() {
                @Override
                public void onClick() {
                    dialogDelegate.clearDialog();
                }
            });
            return;
        }
        dialogDelegate.showProgressDialog(true, "正在发送...");
        mPresenter.gain();
    }

}
