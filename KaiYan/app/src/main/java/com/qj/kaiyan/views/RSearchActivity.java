package com.qj.kaiyan.views;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.ActionSheetDialog;
import com.flyco.dialog.widget.NormalDialog;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.qj.kaiyan.R;
import com.qj.kaiyan.adapters.Myadapter;
import com.qj.kaiyan.base.BaseActitvity;
import com.qj.kaiyan.base.MyApplication;
import com.qj.kaiyan.entitys.HomeResultItem;
import com.qj.kaiyan.entitys.ItemListBean;
import com.qj.kaiyan.greendao.gen.HomeResultItemDao;
import com.qj.kaiyan.mvp.presenter.FindDetailPresenter;
import com.qj.kaiyan.mvp.view.FindDetailView;
import com.qj.kaiyan.utils.ToastUtils;
import com.qj.kaiyan.viewholders.ResultViewHolder;
import com.qj.kaiyan.viewholders.SearchResultItemHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RSearchActivity extends BaseActitvity  {

    @BindView(R.id.result_toolbar)
    Toolbar resultToolbar;

    @BindView(R.id.result_recycle)
    EasyRecyclerView recyclerView;



    RecyclerArrayAdapter<HomeResultItem> myadapter;


    private List<HomeResultItem> list=new ArrayList<>();
    private List<HomeResultItem> homeResultItems=new ArrayList<>();
    private HomeResultItemDao homeResultItemDao;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_rsearch);
        ButterKnife.bind(this);


        resultToolbar.setTitle("观看记录");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(myadapter=new RecyclerArrayAdapter<HomeResultItem>(this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new ResultViewHolder(parent);
            }
        });

    }

    @Override
    protected void initData() {

        getquerydata();

    }

    private void getquerydata() {
        homeResultItems.clear();
        myadapter.clear();
        homeResultItemDao = MyApplication.getDaoSession().getHomeResultItemDao();
        homeResultItems = homeResultItemDao.loadAll();

        myadapter.addAll(homeResultItems);
    }

    @Override
    protected void initListener() {

        resultToolbar.setNavigationIcon(R.drawable.ic_back_24dp);
        resultToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


      myadapter.setOnItemLongClickListener(new RecyclerArrayAdapter.OnItemLongClickListener() {
          @Override
          public boolean onItemLongClick(final int position) {
              final NormalDialog normalDialog=new NormalDialog(RSearchActivity.this);
              normalDialog.content("确定删除该记录");
              normalDialog.show();
              normalDialog.setOnBtnClickL(new OnBtnClickL() {
                  @Override
                  public void onBtnClick() {
                    normalDialog.dismiss();
                  }
              }, new OnBtnClickL() {
                  @Override
                  public void onBtnClick() {

                      normalDialog.dismiss();

                      List<HomeResultItem> list = homeResultItemDao.queryBuilder().where(HomeResultItemDao.Properties.Title.eq(homeResultItems.get(position).getTitle())).list();
                      for (int i = 0; i < list.size(); i++) {
                          homeResultItemDao.delete(list.get(i));
                      }
                      getquerydata();
                  }
              });
              return false;
          }
      });

    }


}
