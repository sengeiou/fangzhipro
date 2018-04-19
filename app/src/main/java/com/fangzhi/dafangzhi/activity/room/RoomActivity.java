package com.fangzhi.dafangzhi.activity.room;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.fangzhi.dafangzhi.R;
import com.fangzhi.dafangzhi.activity.room.adapter.RoomAdapter;
import com.fangzhi.dafangzhi.activity.room.adapter.RoomHeandAdapter;
import com.fangzhi.dafangzhi.activity.room.adapter.SameSceneAdapter;
import com.fangzhi.dafangzhi.activity.room.bean.DefaultPartList;
import com.fangzhi.dafangzhi.activity.room.bean.JsonBean;
import com.fangzhi.dafangzhi.activity.room.bean.PartList;
import com.fangzhi.dafangzhi.activity.room.bean.PartTypeList;
import com.fangzhi.dafangzhi.activity.room.bean.RestRoom;
import com.fangzhi.dafangzhi.activity.room.bean.RoomBean;
import com.fangzhi.dafangzhi.activity.room.bean.SceneInfo;
import com.fangzhi.dafangzhi.activity.room.bean.SceneList;
import com.fangzhi.dafangzhi.activity.room.bean.Sceneorder;
import com.fangzhi.dafangzhi.activity.room.bean.TypeList;
import com.fangzhi.dafangzhi.base.BaseActivity;
import com.fangzhi.dafangzhi.base.BaseBean;
import com.fangzhi.dafangzhi.config.SpKey;
import com.fangzhi.dafangzhi.dowload.DownLoadImageService;
import com.fangzhi.dafangzhi.utils.SPUtils;
import com.fangzhi.dafangzhi.utils.T;
import com.fangzhi.dafangzhi.views.DialogDelegate;
import com.fangzhi.dafangzhi.views.SweetAlertDialogDelegate;
import com.fangzhi.dafangzhi.views.loading.AVLoadingIndicatorView;
import com.fangzhi.dafangzhi.views.loading.BallSpinFadeLoaderIndicator;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by smacr on 2017/3/30.
 */

public class RoomActivity extends BaseActivity<RoomPresenter, RoomModel> implements RoomContract.View {

    Context context = RoomActivity.this;
    @Bind(R.id.add)
    LinearLayout add;
    @Bind(R.id.addhead)
    LinearLayout addhead;
    @Bind(R.id.yingname)
    TextView yingname;
    @Bind(R.id.ruanname)
    TextView ruanname;

    @Bind(R.id.iv_show)
    ImageView iv_show;

    @Bind(R.id.iv_eye)
    ImageView imageView;

    @Bind(R.id.view_loading)
    View layoutLoading;

    @Bind(R.id.avi)
    AVLoadingIndicatorView aviLoading;

    @Bind(R.id.view_product)
    LinearLayout view_product; //点击隐藏

    @Bind(R.id.conceal)
    LinearLayout conceal; //点击显示


    RoomBean roomBean = null; //数据源
    RestRoom restRoom = null; //其他房间数据源
    DownLoadImageService downLoadImageService;
    boolean isback = false;
    String scene_img = "";

    //当前图层urls
    private Map<Integer, String> mapUrl = new TreeMap<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    });

    //当前id
    private Map<Integer, String> mapId = new TreeMap<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    });

    //保持不变的
    private Map<Integer, PartList> productMap = new TreeMap<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    });
    private Map<Integer, PartList> productMaps = new TreeMap<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    });

    @Bind(R.id.ying)
    EasyRecyclerView ying;
    @Bind(R.id.ruan)
    EasyRecyclerView ruan;
    TypeList ruanlist;
    TypeList yinglist;
    RoomHeandAdapter yingHeandAdapter;
    RoomHeandAdapter ruanHeandAdapter;

    String hot_id;
    String design_id;
    String is_collected;
    List<PartTypeList> partTypeList = new ArrayList<>();

    DialogDelegate dialogDelegate;

    @Override
    public int getLayoutId() {
        return R.layout.activity_room;
    }

    @Override
    public void initView() {
        BallSpinFadeLoaderIndicator indicator = new BallSpinFadeLoaderIndicator();
        aviLoading.setIndicator(indicator);
        dialogDelegate = new SweetAlertDialogDelegate(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        hot_id = bundle.getString("hot_id");
        design_id = bundle.getString("design_id");
        is_collected = bundle.getString("is_collected");
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        StaggeredGridLayoutManager staggeredGridLayoutManager1 = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        yingHeandAdapter = new RoomHeandAdapter(this);
        ruanHeandAdapter = new RoomHeandAdapter(this);
        ying.setLayoutManager(staggeredGridLayoutManager);
        ruan.setLayoutManager(staggeredGridLayoutManager1);
        ying.setAdapter(yingHeandAdapter);
        ruan.setAdapter(ruanHeandAdapter);

        yingHeandAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                for (int i = 0; i < yinglist.getStairTypeList().size(); i++) {
                    if (i == position) {
                        if (yinglist.getStairTypeList().get(i).isture()) {
                            //yinglist.getStairTypeList().get(i).setIsture(false);
                        } else {
                            yinglist.getStairTypeList().get(i).setIsture(true);
                            emptylistview(position, 0);
                        }
                    } else {
                        yinglist.getStairTypeList().get(i).setIsture(false);
                    }
                }
                for (int i = 0; i < ruanlist.getStairTypeList().size(); i++) {
                    ruanlist.getStairTypeList().get(i).setIsture(false);
                }
                yingHeandAdapter.notifyDataSetChanged();
                ruanHeandAdapter.notifyDataSetChanged();
            }
        });

        ruanHeandAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                for (int i = 0; i < ruanlist.getStairTypeList().size(); i++) {
                    if (i == position) {
                        if (ruanlist.getStairTypeList().get(i).isture()) {
                            // ruanlist.getStairTypeList().get(i).setIsture(false);
                        } else {
                            ruanlist.getStairTypeList().get(i).setIsture(true);
                            emptylistview(position, 1);
                        }
                    } else {
                        ruanlist.getStairTypeList().get(i).setIsture(false);
                    }
                }
                for (int i = 0; i < yinglist.getStairTypeList().size(); i++) {
                    yinglist.getStairTypeList().get(i).setIsture(false);
                }
                yingHeandAdapter.notifyDataSetChanged();
                ruanHeandAdapter.notifyDataSetChanged();
            }
        });
        dialogDelegate.showProgressDialog(true, "正在获取数据...");
        mPresenter.gainMaterialList();
    }

    /**
     * 添加材料数据
     *
     * @param i
     * @param partTypeList
     */
    public void setview(int i, final PartTypeList partTypeList) {
        View view = LayoutInflater.from(this).inflate(R.layout.activity_room_item, null);
        final AutoLinearLayout autoLinearLayout = (AutoLinearLayout) view.findViewById(R.id.nameLayout);
        final EasyRecyclerView easyRecyclerView = (EasyRecyclerView) view.findViewById(R.id.recycler_view);
        final TextView name = (TextView) view.findViewById(R.id.name);
        name.setText(partTypeList.getType_name());
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        easyRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        final RoomAdapter roomAdapter = new RoomAdapter(this);
        easyRecyclerView.setAdapter(roomAdapter);
        roomAdapter.addAll(partTypeList.getPartList());
        easyRecyclerView.setTag(i);
        name.setTag(i);
        roomAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                for (int i = 0; i < partTypeList.getPartList().size(); i++) {
                    if (position == i) {
                        if (partTypeList.getPartList().get(i).isture()) {
                            change(partTypeList.getOrder_num(), partTypeList.getPartList().get(i).getPart_img(), true);
                            mapUrl.remove(partTypeList.getOrder_num());
                            mapId.put(partTypeList.getOrder_num(), "");
                            productMap.remove(partTypeList.getOrder_num());
                            partTypeList.getPartList().get(i).setIsture(false);
                            if (!isClear) {
                                productMaps.remove(partTypeList.getOrder_num());
                            }
                        } else {
                            change(partTypeList.getOrder_num(), partTypeList.getPartList().get(i).getPart_img(), false);
                            mapUrl.put(partTypeList.getOrder_num(), partTypeList.getPartList().get(i).getPart_img());
                            mapId.put(partTypeList.getOrder_num(), partTypeList.getPartList().get(i).getPart_id());
                            partTypeList.getPartList().get(i).setOrder_num(partTypeList.getOrder_num() + "");
                            productMap.put(partTypeList.getOrder_num(), partTypeList.getPartList().get(i));
                            partTypeList.getPartList().get(i).setIsture(true);
                            if (!isClear) {
                                productMaps.put(partTypeList.getOrder_num(), partTypeList.getPartList().get(i));
                            }
                        }
                    } else {
                        partTypeList.getPartList().get(i).setIsture(false);
                    }
                }
                roomAdapter.notifyDataSetChanged();

            }
        });

        autoLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (partTypeList.isture()) {
                    autoLinearLayout.setBackgroundResource(R.drawable.iv_roomyin_not);
                    name.setTextColor(getResources().getColor(R.color.login_text));
                    easyRecyclerView.setVisibility(View.GONE);
                } else {
                    autoLinearLayout.setBackgroundResource(R.drawable.text_textbg);
                    name.setTextColor(getResources().getColor(R.color.white));
                    easyRecyclerView.setVisibility(View.VISIBLE);
                }
                partTypeList.setIsture(!partTypeList.isture());
            }
        });

        if (partTypeList.isture()) {
            autoLinearLayout.setBackgroundResource(R.drawable.text_textbg);
            name.setTextColor(getResources().getColor(R.color.white));
            easyRecyclerView.setVisibility(View.VISIBLE);
        } else {
            autoLinearLayout.setBackgroundResource(R.drawable.iv_roomyin_not);
            name.setTextColor(getResources().getColor(R.color.login_text));
            easyRecyclerView.setVisibility(View.GONE);
        }

        add.addView(view);
    }

    @Override
    public void tokenInvalid(String msg) {

    }

    @Override
    public void onError(String msg) {
        dialogDelegate.stopProgressWithFailed(msg, "");
    }

    @Override
    public String getdesign_id() {
        return design_id;
    }

    @Override
    public String getuser_id() {
        return SPUtils.getString(this, SpKey.USER_ID, "");
    }

    @Override
    public String gethot_id() {
        return hot_id;
    }

    @Override
    public String getscene_img() {
        return scene_img;
    }

    @Override
    public String getis_collected() {
        return is_collected;
    }


    @Override
    public String getjsonString() {
        String json = "";
        List<JsonBean> jsonBeanList = new ArrayList<>();

        for (Map.Entry<Integer, PartList> entry : productMap.entrySet()) {
            JsonBean JsonBean = new JsonBean();
            JsonBean.setOrder_num(entry.getValue().getOrder_num());
            JsonBean.setPart_id(entry.getValue().getPart_id());
            JsonBean.setPart_type_id(entry.getValue().getType_id() + "");
            jsonBeanList.add(JsonBean);
        }

        json = JSON.toJSONString(jsonBeanList);
        Log.i("TT", json);
        return json;
    }

    @Override
    public String getPicturejsonString() {
        String json = "";
        List<Sceneorder> sceneorderList = new ArrayList<>();
        if (isClear) {
            Sceneorder sceneorders = new Sceneorder();
            sceneorders.setScene_id(roomBean.getSceneInfo().getScene_id());
            sceneorders.setPart_url(mapUrl.get(0));
            sceneorders.setOrder_num("0");
            sceneorderList.add(sceneorders);
            for (Map.Entry<Integer, PartList> entry : productMap.entrySet()) {
                Sceneorder sceneorder = new Sceneorder();
                sceneorder.setScene_id(roomBean.getSceneInfo().getScene_id());
                sceneorder.setPart_url(entry.getValue().getPart_img());
                sceneorder.setOrder_num(entry.getValue().getOrder_num());
                sceneorderList.add(sceneorder);
                //Log.i("TT",i);
            }
        } else {
            Sceneorder sceneorders = new Sceneorder();
            sceneorders.setScene_id(roomBean.getSceneInfo().getScene_id());
            sceneorders.setPart_url(mapUrl.get(0));
            sceneorders.setOrder_num("0");
            sceneorderList.add(sceneorders);
            for (Map.Entry<Integer, PartList> entry : productMaps.entrySet()) {
                Sceneorder sceneorder = new Sceneorder();
                sceneorder.setScene_id(roomBean.getSceneInfo().getScene_id());
                sceneorder.setPart_url(entry.getValue().getPart_img());
                sceneorder.setOrder_num(entry.getValue().getOrder_num());
                sceneorderList.add(sceneorder);
                //Log.i("TT",i);
            }
        }
        json = JSON.toJSONString(sceneorderList);
        Log.i("TT", json);
        return json;
    }

    @Override
    public String getscene_id() {
        return roomBean.getSceneInfo().getScene_id();
    }


    boolean isC = true;

    @Override
    public void getMaterialListSucceed(RoomBean roomBean) {
        dialogDelegate.clearDialog();
        if (isC) {
            dialogDelegate.showProgressDialog(true, "正在获取数据...");
            mPresenter.getOtherScene();
            isC = !isC;
        }

        /*-------------清空数据---------------------------*/
        ruanHeandAdapter.clear();
        yingHeandAdapter.clear();
        partTypeList.clear();
        ruanlist = null;
        yinglist = null;
        mapId.clear();
        productMap.clear();
        productMaps.clear();
        mapUrl.clear();
        add.removeAllViews();

        // T.showString(context, "成功");
        this.roomBean = roomBean;
        SceneInfo sceneInfo = roomBean.getSceneInfo();
        List<TypeList> typeList = roomBean.getTypeList();
        ruanlist = typeList.get(0);
        yinglist = typeList.get(1);
        yinglist.getStairTypeList().get(0).setIsture(true);
        ruanHeandAdapter.addAll(ruanlist.getStairTypeList());
        yingHeandAdapter.addAll(yinglist.getStairTypeList());
        ruanname.setText(ruanlist.getFixture_name() + ">>");
        yingname.setText(yinglist.getFixture_name() + ">>");
        partTypeList = yinglist.getStairTypeList().get(0).getPartTypeList();
        List<DefaultPartList> defaultPartList = sceneInfo.getDefaultPartList();

        //获取当前图片的id
        for (int i = 0; i < defaultPartList.size(); i++) {
            mapId.put(defaultPartList.get(i).getOrder_num(), defaultPartList.get(i).getPart_id());
            PartList partList = new PartList();
            partList.setGoods_id(defaultPartList.get(i).getGoods_id());
            partList.setPart_id(defaultPartList.get(i).getPart_id());
            partList.setPart_name(defaultPartList.get(i).getPart_name());
            partList.setPart_img(defaultPartList.get(i).getPart_img());
            partList.setStyle_code(defaultPartList.get(i).getStyle_code());
            partList.setPart_code(defaultPartList.get(i).getPart_code());
            partList.setType_id(defaultPartList.get(i).getType_id());
            partList.setPart_img_short(defaultPartList.get(i).getPart_img_short());
            partList.setOrder_num(defaultPartList.get(i).getOrder_num() + "");
            partList.setIsture(true);
            productMap.put(defaultPartList.get(i).getOrder_num(), partList);
        }
        partTypeList.get(0).getPartList().get(0).setIsture(true);
        for (int i = 0; i < partTypeList.size(); i++) {

            for (int j = 0; j < partTypeList.get(i).getPartList().size(); j++) {

                for (Map.Entry<Integer, String> entry : mapId.entrySet()) {
                    System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                    if (entry.getValue().equals(partTypeList.get(i).getPartList().get(j).getPart_id())) {
                        partTypeList.get(i).getPartList().get(j).setIsture(true);
                        break;
                    } else {
                        partTypeList.get(i).getPartList().get(j).setIsture(false);
                    }
                }
            }
            setview(i, partTypeList.get(i));
        }

        // setlistview();
        setimage(sceneInfo);
    }

    @Override
    public void getMaterialListFailed(String msg) {
        T.showString(context, msg);
        dialogDelegate.showWarningDialog("获取失败重新获取", "", new DialogDelegate.OnDialogListener() {
            @Override
            public void onClick() {
                dialogDelegate.showProgressDialog(true, "正在获取数据...");
                mPresenter.gainMaterialList();

            }
        });

    }

    @Override
    public void getOtherSceneSucceed(RestRoom baseBean) {
        dialogDelegate.clearDialog();
        this.restRoom = baseBean;
    }

    @Override
    public void getOtherSceneFailed(String msg) {
        dialogDelegate.clearDialog();
        //dialogDelegate.stopProgressWithFailed(msg, "");
        dialogDelegate.showProgressDialog(true, "正在获取数据...");
        mPresenter.getOtherScene();
    }

    @Override
    public void collectSucceed(BaseBean baseBean) {
        dialogDelegate.stopProgressWithSuccess("收藏成功", "", new DialogDelegate.OnDialogListener() {
            @Override
            public void onClick() {
                //  RxBus.$().post("RoomActivity", "100");
                //backs(1);
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setAction("HotspotActivity");
                sendBroadcast(intent);
                finish();
            }
        });
    }


    @Override
    public void collectFailed(String msg) {
        dialogDelegate.stopProgressWithFailed("收藏失败", "");
    }

    @Override
    public void showbackSucceed(String sceneorderback) {
        if (isback) {
            scene_img = sceneorderback;
            mPresenter.collectDesign();
        } else {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("url", sceneorderback);
            intent.putExtras(bundle);
            intent.setClass(this, RoommapActivity.class);
            startActivity(intent);
            dialogDelegate.clearDialog();
        }
    }

    @Override
    public void showbackFailed(String msg) {
        dialogDelegate.stopProgressWithFailed("请求失败请重试", "");
    }


    /**
     * 初始化图片
     *
     * @param sceneInfo
     */
    public void setimage(SceneInfo sceneInfo) {
        String bgUrl = sceneInfo.getHl_img();
        mapUrl.put(0, bgUrl);
        List<DefaultPartList> defaultPartList = sceneInfo.getDefaultPartList();
        for (int i = 0; i < defaultPartList.size(); i++) {
            mapUrl.put(defaultPartList.get(i).getOrder_num(), defaultPartList.get(i).getPart_img());
            //mapIdToOrder.put(defaultPartList.get(i).getType_id(), defaultPartList.get(i).getOrder_num());
            //productMap.put(defaultPartList.get(i).getOrder_num(), defaultPartList.get(i));
        }
        downLoadImageService = new DownLoadImageService(mapUrl, this, new DownLoadImageService.OnDrawListener() {
            @Override
            public void onDrawSucceed(Bitmap bitmap) {
                if (iv_show == null) {
                    return;
                }
                iv_show.setImageBitmap(bitmap);
                //    ivShow.setImage(ImageSource.bitmap(bitmap));
                layoutLoading.setVisibility(View.INVISIBLE);
            }
        }, false);
    }

    /**
     * 修改选择框的所有
     *
     * @param numb
     */
    public void emptylistview(int numb, int type) {
        add.removeAllViews();
        if (type == 0) {
            partTypeList = yinglist.getStairTypeList().get(numb).getPartTypeList();
        } else {
            partTypeList = ruanlist.getStairTypeList().get(numb).getPartTypeList();
        }
        // partTypeList.get(0).setIsture(true);
        for (int i = 0; i < partTypeList.size(); i++) {

            for (int j = 0; j < partTypeList.get(i).getPartList().size(); j++) {

                for (Map.Entry<Integer, String> entry : mapId.entrySet()) {
                    System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
                    if (entry.getValue().equals(partTypeList.get(i).getPartList().get(j).getPart_id())) {
                        partTypeList.get(i).getPartList().get(j).setIsture(true);
                        break;
                    } else {
                        partTypeList.get(i).getPartList().get(j).setIsture(false);
                    }
                }
            }
            setview(i, partTypeList.get(i));
        }
        //  setlistview();
    }


    /**
     * 当前集合一修改就马上触发重绘制
     *
     * @param number
     * @param url
     * @param isCancel
     */
    private void change(int number, String url, boolean isCancel) {
        layoutLoading.setVisibility(View.VISIBLE);
        downLoadImageService.drawOne(number, url, isCancel);
    }


    /**
     * DIY
     */
    @OnClick(R.id.iv_home)
    public void onHome() {
        view_product.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏DIY
     */
    @OnClick(R.id.conceal)
    public void conceal() {
        view_product.setVisibility(View.GONE);
    }

    /**
     * 关闭
     */
    @OnClick(R.id.iv_close)
    public void iv_close() {
        //finish();
        back();
    }

    public void back() {
        dialogDelegate.showWarningDialog1("退出前是否收藏所选建材？", "", new DialogDelegate.OnDialogListener() {
            @Override
            public void onClick() {
                dialogDelegate.showProgressDialog(true, "正在收藏该场景...");
                mPresenter.getmergePicture();
                isback = true;
            }
        }, new DialogDelegate.OnDialogListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
    }

    /**
     * 切换房间
     */
    @OnClick(R.id.iv_eye)
    public void iv_eye() {
        View view = LayoutInflater.from(this).inflate(R.layout.view_room_scene, null);
        EasyRecyclerView recyclerView = (EasyRecyclerView) view.findViewById(R.id.recycler_view);

        final SameSceneAdapter adapter = new SameSceneAdapter(this);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        recyclerView.setAdapter(adapter);

        adapter.addAll(restRoom.getSceneList());
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                SceneList scene = adapter.getItem(position);
                if (!scene.isSelected()) {
                    for (SceneList s : restRoom.getSceneList()) {
                        s.setSelected(false);
                    }
                    scene.setSelected(true);
                    adapter.notifyDataSetChanged();
                    //重新请求部件数据
                    hot_id = scene.getHot_id();
                    is_collected = scene.getIs_collected();
                    dialogDelegate.showProgressDialog(true, "正在获取数据...");
                    mPresenter.gainMaterialList();
/*                    bgUrl = scene.getHl_img();
                    //切换场景
                    setOrder(scene.getSonList());
                    //重新请求部件数据
                    mHotTypeId = scene.getHot_type();
                    mSceneId = scene.getScene_id();
                    mHlCode = scene.getHl_code();*/
                    // mPresenter.getRoomPartTypeList();
                }
            }
        });

        PopupWindow popupWindow = new PopupWindow(view, 700, 300);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOnDismissListener(new popDismissListener());
        backgroundAlpha(0.6f);

        int[] location = new int[2];
        imageView.getLocationOnScreen(location);

        popupWindow.showAtLocation(imageView, Gravity.NO_GRAVITY, location[0] + imageView.getWidth() + 40, location[1] - 80);
        imageView.setImageResource(R.mipmap.round_pre_ipad);

    }

    class popDismissListener implements PopupWindow.OnDismissListener {
        @Override
        public void onDismiss() {
            backgroundAlpha(1f);
            //iv_eye.setba(getResources().getDrawable(R.drawable.icon_scene_more_n));
            imageView.setImageResource(R.mipmap.round_ipad);
        }
    }

    /**
     * 放大功能
     */
    @OnClick(R.id.iv_eyx)
    public void iv_eyx() {
        dialogDelegate.showProgressDialog(true, "正在获取高清图...");
        mPresenter.getmergePicture();
    }


    private boolean isClear = true;

    @OnClick(R.id.iv_calculate)
    public void onEye() {
        if (isClear) {
            downLoadImageService.clearAll();
        } else {
            layoutLoading.setVisibility(View.VISIBLE);
            downLoadImageService.drawAll(mapUrl);
        }
        isClear = !isClear;
    }

    /**
     * 设置添加屏幕的背景透明度
     * 注意：此时activity背景style 要设置为 <item name="android:windowIsTranslucent">false</item>
     * 因为activity滑动删除功能需要将android:windowIsTranslucent设置为true
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dialogDelegate.clearDialog();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            back();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }
}
