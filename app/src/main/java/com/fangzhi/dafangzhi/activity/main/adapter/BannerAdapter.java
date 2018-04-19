package com.fangzhi.dafangzhi.activity.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fangzhi.dafangzhi.R;
import com.fangzhi.dafangzhi.listener.NoDoubleClickListener;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;

import java.util.List;

public class BannerAdapter extends StaticPagerAdapter {

    private Context ctx;
    private List<String> list;
    private NoDoubleClickListener mListener;
    ImageView imageView;
    LayoutInflater inflater;

       public BannerAdapter(Context ctx , List<String> list, NoDoubleClickListener listener){
           this.ctx = ctx;
           this.list = list;
           mListener = listener;
           this.inflater = LayoutInflater.from(ctx);
        }

        @Override
        public View getView(ViewGroup container, final int position) {
            View view = inflater.inflate(R.layout.mainimage_item, container, false);
            imageView= (ImageView) view.findViewById(R.id.image);
            //ImageView imageView = new ImageView(ctx);
            //imageView.setLayoutParams(new ViewGroup.LayoutParams(1394, 642));
            //imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //加载图片
            Glide.with(ctx)
                    .load(list.get(position))
                    .placeholder(R.mipmap.zanwei)
                    .crossFade()
                    .into(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onNoDoubleClick(position);
                }
            });
            return view;
        }

        @Override
        public int getCount() {
            return list.size();
        }


    class ViewHolder {
        ImageView image;

    }

    }
