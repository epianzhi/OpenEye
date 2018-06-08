package com.qj.kaiyan.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qj.kaiyan.R;
import com.qj.kaiyan.entitys.HomeResultItem;
import com.qj.kaiyan.entitys.ItemListBean;

import java.util.List;

public class Myadapter extends RecyclerView.Adapter<Myadapter.MyviewHolder> {


    private Context context;
    private List<HomeResultItem> list;

    public Myadapter(Context context, List<HomeResultItem> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(List<HomeResultItem> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.search_result_item, parent, false);

        return new MyviewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(MyviewHolder holder, int position) {

        HomeResultItem homeResultItem = list.get(position);
        Glide.with(context).load(homeResultItem.getCover().getFeed()).asBitmap().centerCrop().into(holder.im);
        holder.title.setText(homeResultItem.getTitle());
        holder.detail.setText(homeResultItem.getCategory()+"  "+homeResultItem.getDuration()/60+"'"+homeResultItem.getDuration()%60+"''");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyviewHolder extends RecyclerView.ViewHolder {

        ImageView im;
        TextView title,detail;

        public MyviewHolder(View itemView) {
            super(itemView);
           im= itemView.findViewById(R.id.iv_photo);
           title=itemView.findViewById(R.id.tv_title);
           detail=itemView.findViewById(R.id.tv_detail);
        }
    }
}
