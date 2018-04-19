package com.fangzhi.dafangzhi.activity.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.fangzhi.dafangzhi.R;
import com.fangzhi.dafangzhi.activity.city.CityActivity;
import com.fangzhi.dafangzhi.activity.main.adapter.BannerAdapter;
import com.fangzhi.dafangzhi.activity.main.bean.CountyList;
import com.fangzhi.dafangzhi.activity.main.bean.MainBean;
import com.fangzhi.dafangzhi.activity.main.bean.PremiseList;
import com.fangzhi.dafangzhi.activity.main_type.HouseTypeActivity;
import com.fangzhi.dafangzhi.adapter.MainAdapter;
import com.fangzhi.dafangzhi.base.BaseActivity;
import com.fangzhi.dafangzhi.base.KLActivityManager;
import com.fangzhi.dafangzhi.config.SpKey;
import com.fangzhi.dafangzhi.listener.NoDoubleClickListener;
import com.fangzhi.dafangzhi.utils.SPUtils;
import com.fangzhi.dafangzhi.utils.T;
import com.fangzhi.dafangzhi.views.DialogDelegate;
import com.fangzhi.dafangzhi.views.HeadViewPagerTransformer;
import com.fangzhi.dafangzhi.views.MyScrollView;
import com.fangzhi.dafangzhi.views.SweetAlertDialogDelegate;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnTouch;
import rx.Subscription;

public class MainActivity extends BaseActivity<MainPresenter, MainModel> implements MainContract.View, RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    Context context = MainActivity.this;

    @Bind(R.id.roll_pager_view)
    RollPagerView rollPagerView;

    @Bind(R.id.biaoti)
    AutoRelativeLayout biaoti;

    @Bind(R.id.bushibiaoti)
    AutoRelativeLayout bushibiaoti;

    @Bind(R.id.myscrll)
    MyScrollView myscrll;

    @Bind(R.id.tv_location)
    TextView tv_location; //定位

    @Bind(R.id.tv_location1)
    TextView tv_location1; //顶部定位

    @Bind(R.id.distinguish)
    Spinner distinguish; //区县

    @Bind(R.id.distinguish1)
    Spinner distinguish1; //顶部区县

    @Bind(R.id.et_keyword)
    EditText et_keyword; //搜索

    @Bind(R.id.et_keyword1)
    EditText et_keyword1; //顶部搜索

    BannerAdapter mBannerAdapter;
    List<String> mListImages = new ArrayList<>();

    @Bind(R.id.recycler_view)
    EasyRecyclerView easyRecyclerView;
    MainAdapter mainAdapter = null;
    List<PremiseList> list = new ArrayList<>();
    List<CountyList> countyList = new ArrayList<>();

    private Subscription mDownloadSp;
    String county = "0";
    private int mPage = 0; //数据页码
    DialogDelegate dialogDelegate;
    String mKeyword = null;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        dialogDelegate = new SweetAlertDialogDelegate(this);
        String currentCity = SPUtils.getString(this, SpKey.CITY_NAME, "");
        if (currentCity.isEmpty()) {
            distinguish.setVisibility(View.GONE);
            distinguish1.setVisibility(View.GONE);
            startActivityForResult(new Intent(this, CityActivity.class), 1);
        }
        rollPagerView.setHintView(new ColorPointHintView(MainActivity.this, Color.GREEN, Color.GRAY));
        rollPagerView.setHintPadding(0, 0, 0, 0);
        rollPagerView.setPlayDelay(6000);
        rollPagerView.getViewPager().setPageTransformer(true, new HeadViewPagerTransformer());
        rollPagerView.getViewPager().setOffscreenPageLimit(4);
        rollPagerView.setAdapter(mBannerAdapter = new BannerAdapter(this, mListImages, new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(int position) {
                T.showString(context, "点击了" + position);
            }
        }));
        mListImages.add("http://img2.3lian.com/img2007/4/22/043654056cd.jpg");
        mListImages.add("http://img2.3lian.com/img2007/4/22/421911044br.jpg");
        mListImages.add("http://img2.3lian.com/img2007/4/22/609389053ca.jpg");
        mListImages.add("http://img2.3lian.com/img2007/4/22/609389053ca.jpg");
        mBannerAdapter.notifyDataSetChanged();

        easyRecyclerView.setRefreshListener(this);
        easyRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mainAdapter = new MainAdapter(this);
        easyRecyclerView.setAdapterWithProgress(mainAdapter);
        mainAdapter.setOnItemClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(int position) {
                //T.showString(MainActivity.this, "点击了" + position);
                String id = mainAdapter.getItem(position).getId()+"";
                String name = mainAdapter.getItem(position).getPre_cname();
                Intent intent = new Intent();
                intent.putExtra("id", id);
                intent.putExtra("name", name);
                intent.setClass(MainActivity.this, HouseTypeActivity.class);
                startActivity(intent);
            }
        });
        if (!currentCity.isEmpty()) {
            tv_location.setText(currentCity);
            tv_location1.setText(currentCity);
            distinguish.setVisibility(View.VISIBLE);
            mPage = 1;
            dialogDelegate.showProgressDialog(true, "正在获取最新楼盘...");
            mPresenter.gain();
        }

        myscrll.setOnScrollListener(new MyScrollView.OnScrollListener() {
            @Override
            public void onScroll(int scrollY) {
                if (scrollY > rollPagerView.getHeight()) {
                    biaoti.setVisibility(View.VISIBLE);
                } else {
                    biaoti.setVisibility(View.INVISIBLE);
                }
            }
        });

        myscrll.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE: {
                        break;
                    }
                    case MotionEvent.ACTION_DOWN: {
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        //当文本的measureheight 等于scroll滚动的长度+scroll的height
                        if (myscrll.getChildAt(0).getMeasuredHeight() <= myscrll.getScrollY() + myscrll.getHeight()) {
                            //button.setVisibility(View.VISIBLE);
                            //T.showString(context,"到底了");
                            //mPresenter.gain();
                            // onRefresh();
                        } else {

                        }
                        break;
                    }
                }
                return false;
            }
        });
    }


    @Override
    public void tokenInvalid(String msg) {

    }

    @Override
    public void onError(String msg) {
        dialogDelegate.stopProgressWithFailed(msg,"");

    }

    @Override
    public String getDeviceId() {
        return null;
    }

    @Override
    public int getPageSize() {
        return 15;
    }

    @Override
    public int getCurrentPage() {
        return mPage;
    }

    @Override
    public String getUserId() {
        return SPUtils.getString(this, SpKey.USER_ID, "");
    }

    @Override
    public String getAreaCode() {
        return SPUtils.getString(this, SpKey.CITY_CODE, "");
    }

    @Override
    public String getcounty() {
        return null;
    }

    @Override
    public String getpremiseName() {
        return mKeyword;
    }

    /**
     * 获取楼盘成功
     */
    @Override
    public void gainSucceed(MainBean mainBean) {
        dialogDelegate.clearDialog();
        list.clear();
        mainAdapter.clear();
        countyList.clear();
        countyList.addAll(mainBean.getCountyList());
        if (countyList != null && countyList.size() != 0) {
            setspinner(distinguish);
            setspinner1(distinguish1);
        } else {
            distinguish1.setVisibility(View.GONE);
            distinguish1.setVisibility(View.GONE);
        }
        if (mainBean.getPremiseList()!=null&&mainBean.getPremiseList().size()!=0){
            list.addAll(mainBean.getPremiseList());
            list.addAll(mainBean.getPremiseList());
            list.addAll(mainBean.getPremiseList());
            mainAdapter.addAll(list);
        }else {
            T.showString(context,"暂时没有数据");
        }
    }

    private boolean isFirstSelect = true;

    public void setspinner(Spinner spinner) {
        spinner.setVisibility(View.VISIBLE);
        List<String> list = new ArrayList<>();
        list.add("全城");
        spinner.setSelection(0,false);
        for (CountyList county : countyList) {
            list.add(county.getArea_cname());
        }
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_simple_spinner, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //第一次默认选中全城不做操作
                if (isFirstSelect) {
                    isFirstSelect = false;
                    return;
                }
                if (position == 0) {//全城
                    county = null;
                    easyRecyclerView.setRefreshing(true);
                    mPage = 1;
                    mainAdapter.clear();
                    //不需要再次设置区县
                    mPresenter.gainnew();
                } else {
                    county = countyList.get(position - 1).getArea_id();
                    //mCurrentCounty = countyList.get(position - 1);
                    easyRecyclerView.setRefreshing(true);
                    mainAdapter.clear();
                    mPresenter.GETCOUNTYPRIMISE();
                }
                distinguish1.setSelection(position,false);
                setEdtext("");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private boolean isFirstSelect1 = true;

    public void setspinner1(Spinner spinner) {
        spinner.setVisibility(View.VISIBLE);
        List<String> list = new ArrayList<>();
        list.add("全城");
        spinner.setSelection(0,false);
        for (CountyList county : countyList) {
            list.add(county.getArea_cname());
        }
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_simple_spinner, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //第一次默认选中全城不做操作
                if (isFirstSelect1) {
                    isFirstSelect1 = false;
                    return;
                }
                if (position == 0) {//全城
                    county = null;
                    easyRecyclerView.setRefreshing(true);
                    mPage = 1;
                    mainAdapter.clear();
                    //不需要再次设置区县
                    mPresenter.gainnew();
                } else {
                    county = countyList.get(position - 1).getArea_id();
                    //mCurrentCounty = countyList.get(position - 1);
                    easyRecyclerView.setRefreshing(true);
                    mainAdapter.clear();
                    mPresenter.GETCOUNTYPRIMISE();
                }
                distinguish.setSelection(position,false);
                setEdtext("");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * 获取楼盘失败
     *
     * @param msg
     */
    @Override
    public void gainFailed(String msg) {
        dialogDelegate.stopProgressWithFailed(msg,"");
    }

    @Override
    public void gainnewSucceed(MainBean mainBean) {
        mainAdapter.clear();
        list.clear();
        distinguish.setVisibility(View.VISIBLE);
        distinguish1.setVisibility(View.VISIBLE);
        if (mainBean.getPremiseList()!=null&&mainBean.getPremiseList().size()!=0){
            list.addAll(mainBean.getPremiseList());
            list.addAll(mainBean.getPremiseList());
            list.addAll(mainBean.getPremiseList());
            mainAdapter.addAll(list);
        }else {
            T.showString(context,"暂时没有数据");
        }
    }

    @Override
    public void gainnewFailed(String msg) {
        //T.showString(context,msg);
        dialogDelegate.showErrorDialog(msg, "", new DialogDelegate.OnDialogListener() {
            @Override
            public void onClick() {
                dialogDelegate.clearDialog();
            }
        });
    }

    @Override
    public void searchpremiseSucceed(MainBean mainBean) {
        if (mainBean.getPremiseList() != null && mainBean.getPremiseList().size() != 0) {
            mainAdapter.addAll(mainBean.getPremiseList());
        }
    }

    @Override
    public void searchpremisefailed(String msg) {
        dialogDelegate.showErrorDialog(msg, "", new DialogDelegate.OnDialogListener() {
            @Override
            public void onClick() {
                dialogDelegate.clearDialog();
            }
        });
    }

    @Override
    public void countyprimiseSucceed(MainBean mainBean) {
        if (mainBean.getPremiseList() != null && mainBean.getPremiseList().size() != 0) {
            mainAdapter.addAll(mainBean.getPremiseList());
        }
    }

    @Override
    public void countyprimiseFailed(String msg) {
        dialogDelegate.showErrorDialog(msg, "", new DialogDelegate.OnDialogListener() {
            @Override
            public void onClick() {
                dialogDelegate.clearDialog();
            }
        });
    }


    @OnTouch({R.id.tv_location, R.id.tv_location1})
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            pressUp(v);
            switch (v.getId()) {
                case R.id.tv_location:
                    startActivityForResult(new Intent(this, CityActivity.class), 1);
                    break;
                case R.id.tv_location1:
                    startActivityForResult(new Intent(this, CityActivity.class), 1);
                    break;
            }
        }
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            pressDown(v);
        }
        //返回false后up无效
        return true;
    }




    /**
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // super.onActivityResult(requestCode, resultCode, data);
        String city = (String) SPUtils.get(this, SpKey.CITY_NAME, "定位失败");
        tv_location.setText(city);
        tv_location1.setText(city);
        if (resultCode == 1) {
            //这里做定位以后的操作
           // onRefresh();
            dialogDelegate.showProgressDialog(true, "正在获取最新楼盘...");
            mPresenter.gain();
        }
    }

    @Override
    public void onRefresh() {
        mPage = 1;
        mainAdapter.clear();
        onLoadMore();
    }

    private boolean isSearch = false;

    @Override
    public void onLoadMore() {
        if (isSearch) {
            dialogDelegate.showProgressDialog(true, "正在加载...");
            mPresenter.gain();
            /*if (!mKeyword.isEmpty()) {
                mPresenter.searchHouseList();
            } else {
                recyclerView.setRefreshing(false);
            }*/
        } else {
            dialogDelegate.showProgressDialog(true, "正在加载...");
            mPresenter.gain();
           /* if (mCurrentCounty == null) {
                //加载更多不需要更新区县列表
                mPresenter.getHousesList();
            } else {
                mPresenter.getCountyHousesList();
            }*/
        }
        mPage++;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        //这里注意要作判断处理，ActionDown、ActionUp都会回调到这里，不作处理的话就会调用两次
        if (KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction()) {
            mKeyword = et_keyword.getText().toString().trim();
            if (!mKeyword.isEmpty()) {
                setEdtext(mKeyword);
                isSearch = true;
                mPage = 1;
                mainAdapter.clear();
                mPresenter.gainSEARCHPREMISE();
                T.showString(context, mKeyword);
                closeKeyboard();
                onWindowFocusChanged(true);
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    public void closeKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public void setEdtext(String text){
        et_keyword.setText(text);
        et_keyword1.setText(text);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dialogDelegate.clearDialog();
    }
}
