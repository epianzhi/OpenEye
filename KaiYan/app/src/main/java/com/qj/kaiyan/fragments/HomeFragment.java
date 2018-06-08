package com.qj.kaiyan.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.qj.kaiyan.base.MyApplication;
import com.qj.kaiyan.entitys.HomeResult;
import com.qj.kaiyan.entitys.HomeResultItem;
import com.qj.kaiyan.entitys.ItemListBean;
import com.qj.kaiyan.greendao.gen.HomeResultItemDao;
import com.qj.kaiyan.http.RetrofitClient;
import com.qj.kaiyan.http.RxSubscribe;
import com.qj.kaiyan.viewholders.HomeViewhoder;
import com.qj.kaiyan.views.HomeDetailActivity;
import com.umeng.commonsdk.framework.UMLogDataProtocol;

import org.greenrobot.greendao.query.QueryBuilder;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {


    boolean isrefresh=false;
    Unbinder unbinder;
    @BindView(R.id.home_easyRecycleview)
    EasyRecyclerView homeEasyRecycleview;

    String TAG="flag";
private RecyclerArrayAdapter<ItemListBean> adapter;
private List<ItemListBean> listBeans=new ArrayList<>();
private List<ItemListBean> listAllBeans=new ArrayList<>();
    private String date;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    protected void initView() {

        homeEasyRecycleview.setLayoutManager(new LinearLayoutManager(getContext()));
        homeEasyRecycleview.setAdapter(adapter=new RecyclerArrayAdapter<ItemListBean>(getContext()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new HomeViewhoder(parent);
            }
        });
        homeEasyRecycleview.setErrorView(R.layout.view_error);
        adapter.setError(R.layout.view_error, new RecyclerArrayAdapter.OnErrorListener() {
            @Override
            public void onErrorShow() {

                Log.d("flag", "onErrorShow: ");
                adapter.resumeMore();
            }

            @Override
            public void onErrorClick() {

                Log.d(TAG, "onErrorClick: ");
                adapter.resumeMore();
            }
        });

    }

    @Override
    protected void initData() {
        RetrofitClient.getService()
                .getHomeData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        if (!isrefresh)
                       homeEasyRecycleview.showProgress();
                    }

                    @Override
                    public void onNext(HomeResult homeResult) {

                        if (isrefresh){
                            adapter.clear();
                        }

                        listBeans.clear();
                        listAllBeans.clear();
                        if (homeResult.getIssueList().size()>0){
                            for (int i = 0; i < homeResult.getIssueList().size(); i++) {

                                int i2 = homeResult.getNextPageUrl().indexOf("152");
                                date = homeResult.getNextPageUrl().substring(i2, i2+13);

                                for (int i1 = 0; i1 < homeResult.getIssueList().get(i).getItemList().size(); i1++) {
                                    if (homeResult.getIssueList().get(i).getItemList().get(i1).getType().equals("video"))
                                        listBeans.add(homeResult.getIssueList().get(i).getItemList().get(i1));
                                }
                            }

                            adapter.addAll(listBeans);
                            listAllBeans.addAll(listBeans);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof SocketTimeoutException){
                            homeEasyRecycleview.showError();

                            Log.d(TAG, "onError: ");
                        }
                    }

                    @Override
                    public void onComplete() {


                    }
                });

    }

    @Override
    protected void initListener() {
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                homeEasyRecycleview.getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isrefresh=false;
                       getMore();
                    }
                },500);

            }
        });

        homeEasyRecycleview.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                isrefresh=true;
                homeEasyRecycleview.getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                    }
                });

            }
        });
        adapter.setNoMore(R.layout.view_nomore);

        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                HomeResultItemDao homeResultItemDao = MyApplication.getDaoSession().getHomeResultItemDao();


                List<HomeResultItem> list = homeResultItemDao.queryBuilder().where(HomeResultItemDao.Properties.Title.eq(listAllBeans.get(position).getData().getTitle())).list();
                if (list.size()==0){
                    long insert = homeResultItemDao.insertOrReplace(listAllBeans.get(position).getData());
                    Log.d(TAG, "onItemClick insert: "+insert);
                }

                List<HomeResultItem> homeResultItems = homeResultItemDao.loadAll();

                for (int i = 0; i < homeResultItems.size(); i++) {
                    Log.d(TAG, "onItemClick: "+homeResultItems.get(i).getTitle());
                }
                Bundle bundle=new Bundle();
                bundle.putSerializable("data",listAllBeans.get(position));
                openActivity(HomeDetailActivity.class,bundle);

            }
        });


    }

    private void getMore() {
        isrefresh=false;
        RetrofitClient.getService()
                .getHomeMoreData(date,"2")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeResult homeResult) {

                        listBeans.clear();
                        for (int i = 0; i < homeResult.getIssueList().size(); i++) {

                            int i2 = homeResult.getNextPageUrl().indexOf("152");
                            date = homeResult.getNextPageUrl().substring(i2, i2+13);

                            for (int i1 = 0; i1 < homeResult.getIssueList().get(i).getItemList().size(); i1++) {
                                if (homeResult.getIssueList().get(i).getItemList().get(i1).getType().equals("video"))
                                    listBeans.add(homeResult.getIssueList().get(i).getItemList().get(i1));
                            }
                        }

                        adapter.addAll(listBeans);
                        listAllBeans.addAll(listBeans);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("flag", "onError");

                    }

                    @Override
                    public void onComplete() {
                        Log.d("flag", "onComplete");

                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
