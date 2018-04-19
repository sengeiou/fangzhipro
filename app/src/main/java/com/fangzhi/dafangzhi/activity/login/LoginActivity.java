package com.fangzhi.dafangzhi.activity.login;

import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.fangzhi.dafangzhi.R;
import com.fangzhi.dafangzhi.activity.forget.ForgetActivity;
import com.fangzhi.dafangzhi.activity.home.HomeActivity;
import com.fangzhi.dafangzhi.activity.login.bean.LoginBean;
import com.fangzhi.dafangzhi.activity.register.RegisterActivity;
import com.fangzhi.dafangzhi.application.MyApplication;
import com.fangzhi.dafangzhi.base.BaseActivity;
import com.fangzhi.dafangzhi.config.SpKey;
import com.fangzhi.dafangzhi.listener.NoDoubleClickListener;
import com.fangzhi.dafangzhi.utils.MD5Util;
import com.fangzhi.dafangzhi.utils.SPUtils;
import com.fangzhi.dafangzhi.utils.StringHelp;
import com.fangzhi.dafangzhi.utils.T;
import com.fangzhi.dafangzhi.views.DialogDelegate;
import com.fangzhi.dafangzhi.views.SweetAlertDialogDelegate;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.zhy.autolayout.AutoLinearLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by smacr on 2017/3/18.
 */

public class LoginActivity extends BaseActivity<LoginPresenter, LoginModel> implements LoginContract.View {

    Context context = LoginActivity.this;

    @Bind(R.id.weixin)
    AutoLinearLayout weixin; //微信登录
    @Bind(R.id.weixint)
    ImageView weixint;

    @Bind(R.id.forget)
    TextView forget; //忘记密码

    @Bind(R.id.register)
    TextView register; //注册

    @Bind(R.id.login)
    TextView login; //登录

    @Bind(R.id.phone)
    EditText phone;  //手机号

    @Bind(R.id.password)
    EditText password;  //密码

    DialogDelegate dialogDelegate;


    @Override
    public int getLayoutId() {
        return R.layout.login_activity;
    }

    @Override
    public void initView() {
        // mPresenter.login();
        setSwipeBackEnable(false);
        dialogDelegate = new SweetAlertDialogDelegate(this);
        phone.setText(SPUtils.getString(MyApplication.getContext(), SpKey.USER_NAME, ""));
        password.setText(SPUtils.getString(MyApplication.getContext(), SpKey.PASSWORD, ""));
        register.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(int position) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        forget.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(int position) {
                Intent intent = new Intent(LoginActivity.this, ForgetActivity.class);
                startActivity(intent);
            }
        });
        weixin.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(int position) {
                //Intent intent=new Intent(LoginActivity.this, WeixinRegisterActivity.class);
                //startActivity(intent);
                //Intent intent=new Intent(LoginActivity.this, WXEntryActivity.class);
                //startActivity(intent);
                IWXAPI api = WXAPIFactory.createWXAPI(LoginActivity.this,"wx44aae9e9aaec0fb3",false);;
                SendAuth.Req req = new SendAuth.Req();

                //授权读取用户信息
                req.scope = "snsapi_userinfo";

                //自定义信息
                req.state = "wechat_sdk_demo_test";

                //向微信发送请求
                api.sendReq(req);
            }
        });
        weixint.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(int position) {
                //Intent intent = new Intent(LoginActivity.this, WeixinRegisterActivity.class);
                //startActivity(intent);
                IWXAPI api = WXAPIFactory.createWXAPI(LoginActivity.this,"wx1d9e8e252cefa177",false);;
                SendAuth.Req req = new SendAuth.Req();

                //授权读取用户信息
                req.scope = "snsapi_userinfo";

                //自定义信息
                req.state = "login_state";

                //向微信发送请求
                api.sendReq(req);
            }
        });
    }


    @OnClick(R.id.login)
    public void login() {
        if (StringHelp.checkNull(GetPhone()) && StringHelp.checkPhone(GetPhone())) {

        } else {
            T.showString(context, "请输入正确的手机号码");
            return;
        }

        if (StringHelp.checkNull(GetPassword())) {

        } else {
            T.showString(context, "密码不能为空");
            return;
        }
        dialogDelegate.showProgressDialog(true, "正在登录...");
        mPresenter.login();

    }

    @Override
    public void tokenInvalid(String msg) {

    }

    @Override
    public void onError(String msg) {
        dialogDelegate.stopProgressWithFailed(msg, "");
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
     * 获取md5
     *
     * @return
     */
    @Override
    public String GetMD5() {
        return MD5Util.getInstance().getMD5(new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()) + "fangzhi");
    }

    @Override
    public void LoginSucceed(LoginBean loginBean) {
        // dialogDelegate.stopProgressWithFailed(msg,"");
        SPUtils.put(this, SpKey.USER_NAME, getEdtext(phone));
        SPUtils.put(this, SpKey.PASSWORD, getEdtext(password));
        SPUtils.put(this, SpKey.USER_ID, loginBean.getUserID());
        SPUtils.put(this, SpKey.TOKEN, loginBean.getToken());
        dialogDelegate.clearDialog();
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void LoginFailed(String msg) {
        dialogDelegate.stopProgressWithFailed(msg, "");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dialogDelegate.clearDialog();
    }
}
