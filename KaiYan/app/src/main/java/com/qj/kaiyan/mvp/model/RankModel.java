package com.qj.kaiyan.mvp.model;

import com.qj.kaiyan.entitys.ItemListBean;
import com.qj.kaiyan.http.RetrofitClient;
import com.qj.kaiyan.mvp.contract.FinddetailContract;
import com.qj.kaiyan.mvp.model.bean.FindDetailResult;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RankModel {
    public static void getRankData(String strategy, final FinddetailContract contract){
        RetrofitClient.getService().getRankData(10,strategy,"26868b32e808498db32fd51fb422d00175e179df",83)
                .map(new Function<FindDetailResult, List<ItemListBean>>() {
                    @Override
                    public List<ItemListBean> apply(FindDetailResult findDetailResult) throws Exception {
                        return findDetailResult.getItemList();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ItemListBean>>() {
                    @Override
                    public void accept(List<ItemListBean> itemListBeans) throws Exception {
                        contract.onSuccess(itemListBeans);
                    }
                });

    }
}
