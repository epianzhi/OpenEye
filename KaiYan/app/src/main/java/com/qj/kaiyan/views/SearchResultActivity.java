package com.qj.kaiyan.views;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import com.qj.kaiyan.viewholders.SearchResultItemHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchResultActivity extends BaseActitvity implements FindDetailView {

    FindDetailPresenter presenter;
    @BindView(R.id.findetail_toolbar)
    Toolbar findetailToolbar;
    @BindView(R.id.findlist_rv)
    EasyRecyclerView findlistRv;
    private String title;

    int start=10;

    private RecyclerArrayAdapter<ItemListBean> adapter;
    private List<ItemListBean> listBeans=new ArrayList<>();
    private List<ItemListBean> list=new ArrayList<>();
    @Override
    protected void initView() {
        setContentView(R.layout.activity_search_result);
        ButterKnife.bind(this);

        title = getIntent().getExtras().getString("title");

        findetailToolbar.setNavigationIcon(R.drawable.ic_back_24dp);
        findetailToolbar.setTitle("'"+title+"'  相关");
        presenter = new FindDetailPresenter(this);

        findlistRv.setProgressView(R.layout.view_progress);

        findlistRv.setLayoutManager(new GridLayoutManager(this,1));
        findlistRv.setAdapter(adapter=new RecyclerArrayAdapter<ItemListBean>(this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new SearchResultItemHolder(parent);
            }
        });



        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                start+=10;
                presenter.getSearchData(start,title);
            }
        });

    }

    @Override
    protected void initData() {
        presenter.getSearchData(start,title);

    }

    @Override
    protected void initListener() {

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
    }

    @Override
    public void showData(List<ItemListBean> data) {

        listBeans.clear();
        listBeans.addAll(data);

        adapter.addAll(listBeans);
        list.addAll(data);

    }

}
