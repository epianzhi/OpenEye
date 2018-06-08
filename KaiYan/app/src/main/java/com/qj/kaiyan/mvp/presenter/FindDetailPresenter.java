package com.qj.kaiyan.mvp.presenter;

import android.util.Log;

import com.qj.kaiyan.entitys.ItemListBean;
import com.qj.kaiyan.mvp.contract.FinddetailContract;
import com.qj.kaiyan.mvp.model.FindDetailModel;
import com.qj.kaiyan.mvp.model.RankModel;
import com.qj.kaiyan.mvp.model.SearchModel;
import com.qj.kaiyan.mvp.view.FindDetailView;

import java.util.List;

public class FindDetailPresenter {


    FindDetailView view;

    public FindDetailPresenter(FindDetailView view) {
        this.view = view;
    }

    public void getData(String categoryName, String strategy){
        FindDetailModel.getdata(categoryName, strategy, new FinddetailContract() {
            @Override
            public void prepare() {

            }

            @Override
            public void onSuccess(List<ItemListBean> listBeans) {
                view.showData(listBeans);
            }
        });

    }

    public void getMoreData(int start,String category,String strategy){

        FindDetailModel.getMoredata(start, category, strategy, new FinddetailContract() {
            @Override
            public void prepare() {

            }

            @Override
            public void onSuccess(List<ItemListBean> listBeans) {
                view.showData(listBeans);
            }
        });
    }

    public void getRank(String strategy){

        RankModel.getRankData(strategy, new FinddetailContract() {
            @Override
            public void prepare() {

            }

            @Override
            public void onSuccess(List<ItemListBean> listBeans) {
                view.showData(listBeans);
            }
        });

    }

    public void getSearchData(int start,String query){
        SearchModel.getSearchModel(start, query, new FinddetailContract() {
            @Override
            public void prepare() {

            }

            @Override
            public void onSuccess(List<ItemListBean> listBeans) {
                view.showData(listBeans);
            }
        });
    }
}
