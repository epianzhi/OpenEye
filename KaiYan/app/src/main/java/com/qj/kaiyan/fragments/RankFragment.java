package com.qj.kaiyan.fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.qj.kaiyan.R;
import com.qj.kaiyan.base.BaseFragment;
import com.qj.kaiyan.base.LazyLoadFragment;
import com.qj.kaiyan.entitys.ItemListBean;
import com.qj.kaiyan.mvp.presenter.FindDetailPresenter;
import com.qj.kaiyan.mvp.view.FindDetailView;
import com.qj.kaiyan.viewholders.FindDetailViewHolder;
import com.qj.kaiyan.views.HomeDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class RankFragment extends LazyLoadFragment implements FindDetailView {


    @BindView(R.id.find_rv)
    EasyRecyclerView findRv;
    Unbinder unbinder;
    private String stragegy;
    private List<ItemListBean> list=new ArrayList<>();

    private FindDetailPresenter presenter;

    RecyclerArrayAdapter<ItemListBean> adapter;
    public RankFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public RankFragment(String title) {
        stragegy = title;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initView() {
        Log.d("flag", "initView: ");


        findRv.setLayoutManager(new LinearLayoutManager(getContext()));
        findRv.setAdapter(adapter=new RecyclerArrayAdapter<ItemListBean>(getContext()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new FindDetailViewHolder(parent);
            }
        });

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

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
    protected void lazyLoad() {
        presenter=new FindDetailPresenter(this);
       presenter.getRank(stragegy);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showData(List<ItemListBean> data) {

        list.addAll(data);
        adapter.addAll(data);

    }
}
