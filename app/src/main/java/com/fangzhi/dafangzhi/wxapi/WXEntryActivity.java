package com.fangzhi.dafangzhi.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.fangzhi.dafangzhi.R;
import com.fangzhi.dafangzhi.activity.weixinlogin.WeixinRegisterActivity;
import com.fangzhi.dafangzhi.activity.weixinlogin.bean.WXBean;
import com.fangzhi.dafangzhi.utils.T;
import com.google.gson.Gson;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author xiong_it
 * @description 微信第三方登录，分享demo,更多移动开发内容请关注： http://blog.csdn.net/xiong_it
 * @charset UTF-8
 * @date 2015-9-9下午2:50:14
 */
/*
 * 微信登录，分享应用中必须有这个名字叫WXEntryActivity，并且必须在wxapi包名下，腾讯官方文档中有要求
 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private static final String TAG = "WXEntryActivity";

    public static final String APP_ID = "wx1d9e8e252cefa177";// 微信开放平台申请到的app_id
    public static final String APP_SECRET = "db0dec8684755b80097e65a3ab7d7a7b";// 微信开放平台申请到的app_id对应的app_secret
    private static final String WEIXIN_SCOPE = "snsapi_userinfo";// 用于请求用户信息的作用域
    private static final String WEIXIN_STATE = "login_state"; // 自定义

    protected static final int RETURN_OPENID_ACCESSTOKEN = 0;// 返回openid，accessToken消息码
    protected static final int RETURN_NICKNAME_UID = 1; // 返回昵称，uid消息码


    private IWXAPI api;
    private SendAuth.Req req;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weixinactiivty);
        api = WXAPIFactory.createWXAPI(this, APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
        Log.i("TT", req.toString());
    }

    @Override
    public void onResp(BaseResp resp) {
        Log.d("TT", "onPayFinish, errCode = " + resp.errCode);
        int errCode = resp.errCode;
        SendAuth.Resp sendAuthResp = (SendAuth.Resp) resp;// 用于分享时不要有这个，不能强转
        String code = sendAuthResp.code;
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            // 支付

        } else if (resp.getType() == ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX) {
            // 分享

        } else if (resp.getType() == ConstantsAPI.COMMAND_SENDAUTH) {
            // 登录

            if (errCode == 0) {
                //Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                getResult(code);
            }

            if (errCode == -1) {
                //错误
                T.showString(WXEntryActivity.this,"系统异常");
                finish();
            }

            if (errCode == -2) {
                finish();
                //Toast.makeText(this, "用户取消登录", Toast.LENGTH_SHORT).show();
                //用户取消
            }
        }
        //finish();
    }

    /**
     * 获取openid accessToken值用于后期操作
     *
     * @param code 请求码
     */
    private void getResult(final String code) {
        String path = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
                + APP_ID
                + "&secret="
                + APP_SECRET
                + "&code="
                + code
                + "&grant_type=authorization_code";
        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //创建一个Request
        final Request request = new Request.Builder()
                .url(path)
                .build();
        //new call
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                T.showString(WXEntryActivity.this,"系统异常");
                finish();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
               // Log.e("okHttp","get="+response.body().string().toString());
                ResponseBody body = response.body();
                String src = body.string();
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(src);
                    Log.e("okHttp","get="+jsonObject.toString());
                    String access_token = jsonObject.getString("access_token");
                    String openid = jsonObject.getString("openid");
                    getUID(openid,access_token);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * 获取用户唯一标识
     *
     * @param openId
     * @param accessToken
     */
    private void getUID(final String openId, final String accessToken) {
        String path = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + openId;
        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //创建一个Request
        final Request request = new Request.Builder()
                .url(path)
                .build();
        //new call
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                T.showString(WXEntryActivity.this,"系统异常");
                finish();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody body = response.body();
                String src = body.string();
                JSONObject jsonObject = null;
                Gson gson=new Gson();
                WXBean wxBean=gson.fromJson(src, WXBean.class);
                Intent intent =new Intent();
                intent.setClass(WXEntryActivity.this, WeixinRegisterActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("wxBean", wxBean);
                intent.putExtras(bundle);
                WXEntryActivity.this.startActivity(intent);
                finish();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }




}
