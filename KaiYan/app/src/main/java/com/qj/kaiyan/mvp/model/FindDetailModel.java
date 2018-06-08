package com.qj.kaiyan.mvp.model;

import com.qj.kaiyan.entitys.ItemListBean;
import com.qj.kaiyan.http.RetrofitClient;
import com.qj.kaiyan.mvp.contract.FinddetailContract;
import com.qj.kaiyan.mvp.model.bean.FindDetailResult;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;


public class FindDetailModel {
    public static void getdata(String categoryName, String strategy, final FinddetailContract contract){
         RetrofitClient.getService().getFindDetailData(categoryName,strategy,"26868b32e808498db32fd51fb422d00175e179df",83)

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
                    public void accept(List<ItemListBean> findDetailResult) throws Exception {
                        contract.onSuccess(findDetailResult);
                    }
                });
    }
    public static void getMoredata(int start, String categoryName, String strategy, final FinddetailContract contract){
         RetrofitClient.getService().getFindDetailMoreData(start,10,categoryName,strategy)
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
                    public void accept(List<ItemListBean> findDetailResult) throws Exception {
                        contract.onSuccess(findDetailResult);
                    }
                });
    }
}
