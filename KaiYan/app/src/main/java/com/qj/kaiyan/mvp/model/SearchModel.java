package com.qj.kaiyan.mvp.model;

import com.qj.kaiyan.entitys.ItemListBean;
import com.qj.kaiyan.http.RetrofitClient;
import com.qj.kaiyan.mvp.contract.FinddetailContract;
import com.qj.kaiyan.mvp.model.bean.FindDetailResult;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class SearchModel {
    public static void getSearchModel(int start, String query, final FinddetailContract contract){
        RetrofitClient.getService().getSearchData(10,query,start)
                .map(new Function<FindDetailResult, List<ItemListBean>>() {
                    @Override
                    public List<ItemListBean> apply(FindDetailResult findDetailResult) throws Exception {
                        return findDetailResult.getItemList();
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ItemListBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        contract.prepare();
                    }

                    @Override
                    public void onNext(List<ItemListBean> itemListBeans) {


                        contract.onSuccess(itemListBeans);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
