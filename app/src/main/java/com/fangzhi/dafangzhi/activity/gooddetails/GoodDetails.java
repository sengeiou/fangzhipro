package com.fangzhi.dafangzhi.activity.gooddetails;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.fangzhi.dafangzhi.R;
import com.fangzhi.dafangzhi.activity.indent.IndentActivity;
import com.fangzhi.dafangzhi.base.KLBaseFragmentActivity;
import com.fangzhi.dafangzhi.config.SpKey;
import com.fangzhi.dafangzhi.utils.SPUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by smacr on 2017/4/10.
 */

public class GoodDetails extends KLBaseFragmentActivity {

    @Bind(R.id.parttime_job_web)
    WebView parttime_job_web;  //Web

    @Bind(R.id.iv_back)
    ImageView iv_back;  //返回

    Context context=GoodDetails.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.good_details_activity);
        ButterKnife.bind(this);
        String goods_id = getIntent().getStringExtra("goods_id");
        String user_id = SPUtils.getString(this, SpKey.USER_ID, "");
        String url = "http://120.76.212.114/html/details/index.html?goods_id=+"+goods_id+"&user_id="+user_id;
        //开启javaScript
        parttime_job_web.getSettings().setJavaScriptEnabled(true);
        parttime_job_web.getSettings().setLoadsImagesAutomatically(true);  //支持自动加载图片
        parttime_job_web.getSettings().setDefaultTextEncodingName("utf-8");//设置编码格式
/*           parttime_job_web.getSettings().setUseWideViewPort(true);  //将图片调整到适合webview的大小
            parttime_job_web.getSettings().setLoadWithOverviewMode(true); // 缩放至屏幕的大小
            parttime_job_web.getSettings().setSupportZoom(true);  //支持缩放，默认为true。是下面那个的前提。
            parttime_job_web.getSettings(). setBuiltInZoomControls(true); //设置内置的缩放控件。

            parttime_job_web.getSettings().setDisplayZoomControls(false); //隐藏原生的缩放控件
            parttime_job_web.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);  //提高渲染的优先级

            parttime_job_web.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); //支持内容重新布局
            parttime_job_web.getSettings().supportMultipleWindows();  //多窗口
            parttime_job_web.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);  //关闭webview中缓存
            parttime_job_web.getSettings().setAllowFileAccess(true);  //设置可以访问文件
            parttime_job_web.getSettings().setNeedInitialFocus(true); //当webview调用requestFocus时为webview设置节点
            parttime_job_web.getSettings().setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
            parttime_job_web.getSettings().setLoadsImagesAutomatically(true);  //支持自动加载图片
            parttime_job_web.getSettings().setDefaultTextEncodingName("utf-8");//设置编码格式*/

        parttime_job_web.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });

        //自定义webView设置
        WebSettings webSettings = parttime_job_web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        parttime_job_web.addJavascriptInterface(new MyJavaScriptInterface(this), "JSInterface");
        //如果注释了，javaScript中的alert弹窗等就会失效，不显示
        parttime_job_web.setWebChromeClient(new WebChromeClient());
        parttime_job_web.setWebViewClient(new HelloWebView());
        parttime_job_web.loadUrl(url);

        parttime_job_web.setWebViewClient(new WebViewClient() {
            //网页加载开始时调用，显示加载提示旋转进度条
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                //Toast.makeText(ElecHall.this, "onPageStarted", 2).show();
            }

            //网页加载完成时调用，隐藏加载提示旋转进度条
            @Override
            public void onPageFinished(WebView view, String url) {
                // TODO Auto-generated method stub
                super.onPageFinished(view, url);
            //Toast.makeText(ElecHall.this, "onPageFinished", 2).show();
            }

            //网页加载失败时调用，隐藏加载提示旋转进度条
            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                Toast.makeText(context, "加载失败", Toast.LENGTH_LONG).show();
            }
        });
       /* //申明我是客户端
        parttime_job_web.setWebChromeClient(new WebChromeClient());
        //开启javaScript
        parttime_job_web.getSettings().setJavaScriptEnabled(true);
        //String url="http://weather.sina.cn/?vt=4";
        parttime_job_web.loadUrl(url);*/
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


    private class HelloWebView extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            view.loadUrl(url);
            return true;
        }
    }

    /**
     * 在主线程中定义JavaScript可以调用的安卓接口
     * @author CHQ
     * API 以后，每个被调用java函数都要叫声明 @JavascriptInterface
     */
    public class MyJavaScriptInterface{
        private Context context;
        public MyJavaScriptInterface(Context context){
            this.context = context;
        }
        @JavascriptInterface
        public String toString() {
            return "this is interface";
        }
        @JavascriptInterface
        public void changeActivity(String one) {
            Log.i("TT",one);
            //Toast.makeText(context, "js调用安卓：...."+one, Toast.LENGTH_SHORT).show();
           Intent intent=new Intent(GoodDetails.this, IndentActivity.class);
            intent.putExtra("one",one);
            startActivity(intent);

        }
        /**
         * 安卓调用JS接口，要开启子线程调用
         */
        @JavascriptInterface
        public void call() {
            Toast.makeText(context, "安卓客户端再调用JavaScript接口", Toast.LENGTH_SHORT).show();
            Handler handler=new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    String param = "bb";
                    // webView.loadUrl("javascript:showTitle('"+param+"')");
                }
            });
        }
    }

}
