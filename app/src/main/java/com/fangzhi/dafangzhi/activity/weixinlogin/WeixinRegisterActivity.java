package com.fangzhi.dafangzhi.activity.weixinlogin;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.fangzhi.dafangzhi.R;
import com.fangzhi.dafangzhi.activity.weixinlogin.bean.WXBean;
import com.fangzhi.dafangzhi.base.BaseActivity;
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

public class WeixinRegisterActivity extends BaseActivity<WeixinPresenter, WeixinModel> implements WeixinContract.View {

    Context context=WeixinRegisterActivity.this;

    @Bind(R.id.phone)
    EditText phone;  //手机号

    @Bind(R.id.verification)
    EditText verification;  //验证码

    @Bind(R.id.password)
    EditText password;  //密码

    @Bind(R.id.verify)
    TextView verify;  //验证

    @Bind(R.id.agreement)
    CheckBox agreement;  //是否选择用户协议

    @Bind(R.id.protocol)
    TextView protocol;  //用户协议

    DialogDelegate dialogDelegate;
    private CountDownTimer mTimer;
    private int mCount = 60;
    WXBean wxBean;

    @Override
    public int getLayoutId() {
        return R.layout.weixinregister_activity;
    }

    @Override
    public void initView() {
        dialogDelegate = new SweetAlertDialogDelegate(this);

        Intent intent = getIntent();
        wxBean=(WXBean)intent.getSerializableExtra("wxBean");
        final String headimgurl = wxBean.getHeadimgurl();
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

    /**
     * 错误
     *
     * @param msg
     */
    @Override
    public void onError(String msg) {
        dialogDelegate.stopProgressWithFailed(msg,"");
    }

    /**
     * 获取密码
     *
     * @return
     */
    @Override
    public String GetPassword() {
        return getEdtext(password);
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

    @Override
    public String Getsex() {
        return wxBean.getSex()+"";
    }

    @Override
    public String Getopenid() {
        return wxBean.getOpenid();
    }

    @Override
    public String Geticon() {
        return wxBean.getHeadimgurl();
    }

    @Override
    public String Getnick_name() {
        return wxBean.getNickname();
    }

    /**
     * 获取验证码成功
     */
    @Override
    public void GetSucceed(String msg) {
        dialogDelegate.clearDialog();
        mTimer.start();
        T.showString(context,msg);
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

    /**
     * 注册成功
     * @param msg
     */
    @Override
    public void RegisterSucceed(String msg) {
        dialogDelegate.stopProgressWithSuccess(msg, "", new DialogDelegate.OnDialogListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
    }

    @Override
    public void RegisterFailed(String msg) {
        dialogDelegate.stopProgressWithFailed(msg,"");
    }


    @Override
    public void VerifySucceed(String msg) {
        mPresenter.register();
    }

    @Override
    public void VerifyFailed(String msg) {
        dialogDelegate.stopProgressWithFailed(msg,"");
    }


    /**
     * 返回
     */
    @OnClick(R.id.back)
    public void back() {
        finish();
    }

    /**
     * 用户协议
     */
    @OnClick(R.id.protocol)
    public void protocol() {
       // finish();
    }

    /**
     * 获取验证码
     */
    @OnClick(R.id.verify)
    public void gainverify() {
        if (StringHelp.checkNull(GetPhone()) && StringHelp.checkPhone(GetPhone())) {

        } else {
            T.showString(context, "请输入正确的手机号码");
            return;
        }
        if (Network.checkNetWork(context)){

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

    /**
     * 注册
     */
    @OnClick(R.id.register)
    public void register() {
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
        String num= GetPassword();
        if (StringHelp.checkNull(num)){

        }else {
            T.showString(context, "密码不能为空");
            return;
        }
        if (num.length()>=6&&num.length()<=12){

        }else {
            T.showString(context, "请输入6-12位密码");
            return;
        }
        if (agreement.isChecked()){

        }else {
            T.showString(context, "请同意用户协议");
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dialogDelegate.clearDialog();
        mTimer.cancel();
    }
}
