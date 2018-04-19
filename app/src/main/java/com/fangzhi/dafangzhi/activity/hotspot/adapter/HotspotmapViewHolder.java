package com.fangzhi.dafangzhi.activity.hotspot.adapter;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.fangzhi.dafangzhi.R;
import com.fangzhi.dafangzhi.activity.hotspot.bean.DesignList;
import com.fangzhi.dafangzhi.activity.hotspot.bean.HotList;
import com.fangzhi.dafangzhi.activity.materiallist.MaterialActivity;
import com.fangzhi.dafangzhi.activity.room.RoomActivity;
import com.fangzhi.dafangzhi.base.RxBus;
import com.fangzhi.dafangzhi.listener.NoDoubleClickListener;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.zhy.autolayout.utils.AutoUtils;

import rx.functions.Action1;

/**
 * Created by smacr on 2016/9/1.
 */
public class HotspotmapViewHolder extends BaseViewHolder<DesignList> {
    TextView tvName;
    TextView tvxia;
    TextView inventorylist;
    RelativeLayout layoutBg;

    ImageView image;

    public HotspotmapViewHolder(ViewGroup parent) {
        super(parent, R.layout.hotspot);
        AutoUtils.autoSize(itemView);
        tvName = $(R.id.name);
        tvxia = $(R.id.xia);
        image = $(R.id.image);
        inventorylist = $(R.id.inventorylist);
        layoutBg = $(R.id.layout_bg);
    }

    public void remove() {
        layoutBg.removeAllViews();
        layoutBg.addView(image);
    }


    @Override
    public void setData(final DesignList data) {
        tvName.setText(data.getHouse_name() + "");
        Glide.with(getContext())
                .load(data.getImg())
                .placeholder(R.mipmap.zanwei)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(image);

        inventorylist.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(int position) {
                String design_id = data.getDesign_id();
                Intent intent = new Intent(getContext(), MaterialActivity.class);
                intent.putExtra("design_id", design_id);
                getContext().startActivity(intent);
            }
        });

        RxBus.$().register("Ids")
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        String mCurrentPartId = (String) o;
                        if (mCurrentPartId.equals("1")) {
                            remove();
                        }
                    }
                });

        Glide.with(getContext())
                .load(data.getImg())
                .fitCenter()
                .placeholder(R.mipmap.zanwei)
                .crossFade()
                .into(new GlideDrawableImageViewTarget(image) {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                        super.onResourceReady(resource, animation);
                        //delegate.showProgressDialog(true, "初始化热点区域...");
                        //mPresenter.getHouseTypeDetails();

                        layoutBg.post(new Runnable() {
                            @Override
                            public void run() {
                                Log.i("TT", "post(Runnable) : v_view1.getWidth():" + layoutBg.getWidth() + "  v_view1.getHeight():" + layoutBg.getHeight());
                                if (data.getHotList() != null && data.getHotList().size() != 0) {
                                    for (HotList hotbean : data.getHotList()) {
                                        drawHotArea(hotbean);
                                    }
                                }
                            }
                        });
                    }

                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        super.onLoadFailed(e, errorDrawable);
                        //T.showShort(HouseTypeDetailActivity.this, "户型图加载失败,请检查网络!");
                    }
                });

 /*       layoutBg.post(new Runnable() {
            @Override
            public void run() {
                Log.i("TT", "post(Runnable) : v_view1.getWidth():" + layoutBg.getWidth() + "  v_view1.getHeight():" + layoutBg.getHeight());
                if (data.getHotList() != null && data.getHotList().size() != 0) {
                    for (HotList hotbean : data.getHotList()) {
                        drawHotArea(hotbean);
                    }
                }
            }
        });*/

    }

    private void drawHotArea(final HotList houseTypeDetail) {
        int bgWidth = layoutBg.getWidth();
        int bgHeight = layoutBg.getHeight();
        int marginTop = (int) (bgHeight * (Float.valueOf(houseTypeDetail.getHot_top()) / 100));
        int marginLeft = (int) (bgWidth * (Float.valueOf(houseTypeDetail.getHot_left()) / 100));
        int areaWidth = (int) (bgWidth * (Float.valueOf(houseTypeDetail.getHot_long()) / 100));
        int areaHeight = (int) (bgHeight * (Float.valueOf(houseTypeDetail.getHot_wide()) / 100));

        Log.e("drawHotArea", "背景宽:" + bgWidth
                + ",背景高:" + bgHeight
                + ",上间距:" + marginTop
                + ",左间距:" + marginLeft
                + ",区域宽:" + areaWidth
                + ",区域高:" + areaHeight);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(areaWidth, areaHeight);
        layoutParams.topMargin = marginTop;
        layoutParams.leftMargin = marginLeft;
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_hot, null);

        RelativeLayout.LayoutParams layoutParamsimg = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        ImageView iv = new ImageView(getContext());
        if (houseTypeDetail.getIs_collected().equals("0")) {
            iv.setImageResource(R.mipmap.blue_round2);
        } else {
            iv.setImageResource(R.mipmap.red_round2);
        }
        layoutParamsimg.topMargin = marginTop - 50;
        layoutParamsimg.leftMargin = marginLeft + areaWidth - 15;
        view.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(int position) {
                //T.showString(getContext(), "点击了");
                Intent intent = new Intent();
                String hot_id = houseTypeDetail.getHot_id();
                String design_id = houseTypeDetail.getDesign_id();
                String is_collected = houseTypeDetail.getIs_collected();
                Bundle bundle = new Bundle();
                bundle.putString("hot_id", hot_id);
                bundle.putString("design_id", design_id);
                bundle.putString("is_collected", is_collected);
//                if (houseTypeDetail.getSonList() != null && houseTypeDetail.getSonList().size() > 0) {
//                    bundle.putSerializable("window_types", houseTypeDetail.getSonList());
//                    intent.setClass(HouseTypeDetailActivity.this, WindowTypeActivity.class);
//                } else {
//                    intent.setClass(HouseTypeDetailActivity.this, SceneActivity.class);
//                }
//                if (!mHasSonList.isEmpty()) {
//                    if (houseTypeDetail.getSonList() != null
//                            && !houseTypeDetail.getSonList().isEmpty()) {
//                        bundle.putSerializable("window_types", mHasSonList);
//                        intent.setClass(HouseTypeDetailActivity.this, WindowTypeActivity.class);
//                    } else {
//                        T.showShort(HouseTypeDetailActivity.this, "该房间暂无场景，请选择其他房间");
//                        return;
//                    }
//                } else {
                intent.setClass(getContext(), RoomActivity.class);
                //               }
                intent.putExtras(bundle);
                getContext().startActivity(intent);
            }
        });
        layoutBg.addView(view, layoutParams);
        layoutBg.addView(iv, layoutParamsimg);
    }


}
