package com.qj.kaiyan.viewholders;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.qj.kaiyan.R;
import com.qj.kaiyan.base.FindResult;

public class FindViewHolder extends BaseViewHolder<FindResult> {
    ImageView photo;
    TextView title;
    public FindViewHolder(ViewGroup parent) {
        super(parent, R.layout.find_item);
        photo=$(R.id.find_item_photo);
        title=$(R.id.find_item_title);
    }

    @Override
    public void setData(FindResult data) {

        Glide.with(getContext()).load(data.getBgPicture()).diskCacheStrategy(DiskCacheStrategy.RESULT).centerCrop().into(photo);
        title.setText(data.getName());

    }
}
