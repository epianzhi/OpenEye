package com.qj.kaiyan.viewholders;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.qj.kaiyan.R;
import com.qj.kaiyan.entitys.HomeResultItem;
import com.qj.kaiyan.entitys.ItemListBean;
import com.qj.kaiyan.utils.DateUtils;

public class SearchResultItemHolder extends BaseViewHolder<ItemListBean> {

    ImageView photo;
    TextView title,detail;

    public SearchResultItemHolder(ViewGroup paent) {
        super(paent,R.layout.search_result_item);

        photo=$(R.id.iv_photo);
        title=$(R.id.tv_title);
        detail=$(R.id.tv_detail);
    }



    @Override
    public void setData(ItemListBean data) {
        super.setData(data);
        HomeResultItem resultItem = data.getData();
        Glide.with(getContext()).load(resultItem.getCover().getFeed()).asBitmap().centerCrop().into(photo);
        title.setText(resultItem.getTitle());
        String dateToString = DateUtils.getDateToString(resultItem.getReleaseTime(), DateUtils.M_d);
        detail.setText(resultItem.getCategory()+" / "+(resultItem.getDuration()/60+"'"+resultItem.getDuration()%60+"''")+" / "+dateToString);
    }
}
