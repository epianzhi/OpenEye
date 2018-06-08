package com.qj.kaiyan.viewholders;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.qj.kaiyan.R;
import com.qj.kaiyan.entitys.HomeResult;
import com.qj.kaiyan.entitys.HomeResultItem;
import com.qj.kaiyan.entitys.ItemListBean;

import butterknife.BindView;

//https://www.jianshu.com/p/34b78dae9f57
public class HomeViewhoder extends BaseViewHolder<ItemListBean> {


//    @BindView(R.id.home_item_topiv)
    ImageView homeItemTopiv;
//    @BindView(R.id.home_item_leftiv)
    ImageView homeItemLeftiv;
//    @BindView(R.id.home_item_title)
    TextView homeItemTitle;
//    @BindView(R.id.home_item_time)
    TextView homeItemTime;
    private HomeResultItem resultItem;

    public HomeViewhoder(ViewGroup parent) {
        super(parent, R.layout.item_fragment_home);
        homeItemTopiv=$(R.id.home_item_topiv);
        homeItemLeftiv=$(R.id.home_item_leftiv);
        homeItemTitle=$(R.id.home_item_title);
        homeItemTime=$(R.id.home_item_time);

    }

    @Override
    public void setData(ItemListBean data) {
        resultItem = data.getData();
        if ( resultItem.getCover() != null) {

            Glide.with(getContext()).load(resultItem.getCover().getDetail()).asBitmap().centerCrop().into(homeItemTopiv);
            Glide.with(getContext()).load(resultItem.getAuthor().getIcon()).asBitmap().centerCrop().into(homeItemLeftiv);


           homeItemTitle.setText(resultItem.getTitle());
            int duration = resultItem.getDuration();
            homeItemTime.setText("发布于   "+resultItem.getCategory()+"/"+(duration/60+":"+duration%60));

        }
    }
}
