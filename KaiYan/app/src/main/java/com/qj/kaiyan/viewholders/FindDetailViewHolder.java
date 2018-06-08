package com.qj.kaiyan.viewholders;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.qj.kaiyan.R;
import com.qj.kaiyan.entitys.HomeResultItem;
import com.qj.kaiyan.entitys.ItemListBean;

public class FindDetailViewHolder extends BaseViewHolder<ItemListBean> {

    ImageView ivPhoto;
    TextView tvtitle,tvtime;
    private HomeResultItem resultItem;

    public FindDetailViewHolder(ViewGroup parent) {
        super(parent, R.layout.find_detail_item);
        ivPhoto=$(R.id.find_detail_photo);
        tvtitle=$(R.id.find_detail_title);
        tvtime=$(R.id.find_detail_time);
    }

    @Override
    public void setData(ItemListBean data) {
        super.setData(data);

        resultItem = data.getData();
        Glide.with(getContext()).load(resultItem.getCover().getDetail()).diskCacheStrategy(DiskCacheStrategy.RESULT).centerCrop().into(ivPhoto);
        tvtitle.setText(resultItem.getTitle());
        tvtime.setText(resultItem.getCategory()+"  "+(resultItem.getDuration()/60+"'"+resultItem.getDuration()%60+"''"));
    }
}
