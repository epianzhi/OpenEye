package com.qj.kaiyan.viewholders;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.qj.kaiyan.R;
import com.qj.kaiyan.entitys.HomeResultItem;
import com.qj.kaiyan.entitys.ItemListBean;

public class ResultViewHolder extends BaseViewHolder<HomeResultItem> {

    ImageView ivphoto;
    TextView tvtitle;
    TextView tvDetail;

    public ResultViewHolder(ViewGroup itemView) {
        super(itemView, R.layout.search_result_item);
        ivphoto=$(R.id.iv_photo);
        tvtitle=$(R.id.tv_title);
        tvDetail=$(R.id.tv_detail);

    }

    @Override
    public void setData(HomeResultItem data) {
        super.setData(data);
        Glide.with(getContext()).load(data.getCover().getFeed()).asBitmap().centerCrop().into(ivphoto);
        tvtitle.setText(data.getTitle());
        tvDetail.setText(data.getCategory()+"  "+data.getDuration()/60+"'"+data.getDuration()%60+"''");
    }
}
