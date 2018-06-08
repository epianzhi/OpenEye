package com.qj.kaiyan.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.qj.kaiyan.R;
import com.qj.kaiyan.base.BaseFragment;
import com.qj.kaiyan.base.FindResult;
import com.qj.kaiyan.http.ApiService;
import com.qj.kaiyan.http.RetrofitClient;
import com.qj.kaiyan.viewholders.FindViewHolder;
import com.qj.kaiyan.views.FindItemListActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindFragment extends BaseFragment {


    @BindView(R.id.find_rv)
    EasyRecyclerView findRv;
    Unbinder unbinder;

    private RecyclerArrayAdapter<FindResult> adapter;
    private List<FindResult> findResultslist=new ArrayList<>();
    public FindFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_find, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    protected void initView() {

        findRv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        findRv.addItemDecoration(new SpaceDecoration(5));
        findRv.setProgressView(R.layout.view_progress);
        findRv.setAdapter(adapter=new RecyclerArrayAdapter<FindResult>(getContext()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new FindViewHolder(parent);
            }
        });
    }

    @Override
    protected void initData() {

        getdata();

    }

    private void getdata() {

        RetrofitClient.getService().getFindData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<FindResult>>() {
                    @Override
                    public void onSubscribe(Disposable d) {


                              findRv.showProgress();

                    }

                    @Override
                    public void onNext(List<FindResult> findResults) {

                        findResultslist.addAll(findResults);
                        adapter.addAll(findResults);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void initListener() {

        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                Bundle bundle=new Bundle();
                bundle.putString("name",findResultslist.get(position).getName());
                openActivity(FindItemListActivity.class,bundle);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
