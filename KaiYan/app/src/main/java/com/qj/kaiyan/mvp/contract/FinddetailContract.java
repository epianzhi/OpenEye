package com.qj.kaiyan.mvp.contract;

import com.qj.kaiyan.entitys.ItemListBean;

import java.util.List;

public interface FinddetailContract {

    void prepare();
    void onSuccess(List<ItemListBean> listBeans);

}
