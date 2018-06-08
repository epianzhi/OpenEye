package com.qj.kaiyan.views;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.qj.kaiyan.R;
import com.qj.kaiyan.base.BaseActitvity;
import com.qj.kaiyan.entitys.ItemListBean;
import com.qj.kaiyan.mvp.presenter.FindDetailPresenter;
import com.qj.kaiyan.mvp.view.FindDetailView;
import com.qj.kaiyan.utils.ToastUtils;
import com.qj.kaiyan.viewholders.FindDetailViewHolder;
import com.qj.kaiyan.viewholders.HomeViewhoder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FindItemListActivity extends BaseActitvity implements FindDetailView{

    @BindView(R.id.findetail_toolbar)
    Toolbar findetailToolbar;
    @BindView(R.id.findlist_rv)
    EasyRecyclerView findlistRv;
    private String name;

    private int start=10;

    private RecyclerArrayAdapter<ItemListBean> adapter;
    private List<ItemListBean> itemListBeanList=new ArrayList<>();
    private List<ItemListBean> list=new ArrayList<>();

    private FindDetailPresenter presenter;
    private boolean isrefresh=false;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_find_item_list);
        ButterKnife.bind(this);

        name = getIntent().getExtras().getString("name");
        findetailToolbar.setTitle(name);

        findlistRv.setLayoutManager(new LinearLayoutManager(this));
        findlistRv.setProgressView(R.layout.view_progress);


        presenter=new FindDetailPresenter(this);
    }


    @Override
    protected void initData() {
      presenter.getData(name,"date");

        findlistRv.setAdapter(adapter=new RecyclerArrayAdapter<ItemListBean>(this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new FindDetailViewHolder(parent);
            }
        });

        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                presenter.getMoreData(start,name,"date");
                start+=10;
            }
        });

    }

    @Override
    protected void initListener() {
        findetailToolbar.setNavigationIcon(R.mipmap.ic_back2);
        findetailToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Bundle bundle=new Bundle();
                bundle.putSerializable("data",list.get(position));
                openActivity(HomeDetailActivity.class,bundle);
            }
        });
        findlistRv.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isrefresh =true;
                presenter.getData(name,"date");
                start=10;
            }
        });
    }

    @Override
    public void showData(List<ItemListBean> data) {
        if (isrefresh){
            list.clear();
            adapter.clear();
        }

        itemListBeanList.clear();
        itemListBeanList.addAll(data);
        adapter.addAll(itemListBeanList);

        list.addAll(data);
        isrefresh=false;
    }


}
