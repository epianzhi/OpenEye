package com.qj.kaiyan.views;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.gyf.barlibrary.ImmersionBar;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.qj.kaiyan.R;
import com.qj.kaiyan.base.BaseActitvity;
import com.qj.kaiyan.mvp.view.FindDetailView;
import com.qj.kaiyan.utils.ToastUtils;
import com.qj.kaiyan.viewholders.SearchHotViewHoder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends BaseActitvity {


    @BindView(R.id.iv_search_back)
    ImageView ivSearchBack;
    @BindView(R.id.iv_search_edit)
    EditText ivSearchEdit;
    @BindView(R.id.iv_search_search)
    ImageView ivSearchSearch;
    @BindView(R.id.search_easyrecycleview)
    EasyRecyclerView searchEasyrecycleview;

    RecyclerArrayAdapter<String> adapter;

    private List<String> itemList;

    String[] array = new String[]{"脱口秀", "城会玩", "666", "笑cry", "漫威",
            "清新", "匠心", "VR", "心理学", "舞蹈", "品牌广告", "粉丝自制", "电影相关", "萝莉", "魔性"
            , "第一视角", "教程", "毕业设计", "奥斯卡", "燃", "冰与火之歌", "温情", "线下campaign", "公益"};
    private FlexboxLayoutManager flexboxLayoutManager;
    private String trim;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        ImmersionBar.with(this).transparentBar().barAlpha(0.3f).fitsSystemWindows(true).init();


        flexboxLayoutManager = new FlexboxLayoutManager();
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
        flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);
        searchEasyrecycleview.setLayoutManager(flexboxLayoutManager);

        searchEasyrecycleview.setAdapter(adapter=new RecyclerArrayAdapter<String>(this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new SearchHotViewHoder(parent);
            }
        });
    }

    @Override
    protected void initData() {

        itemList = new ArrayList<String>();

        for (int i = 0; i < array.length; i++) {
            itemList.add(array[i]);
        }

        adapter.addAll(itemList);

    }

    @Override
    protected void initListener() {

        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                startSearch(itemList.get(position));
            }
        });
    }



    @OnClick({R.id.iv_search_back, R.id.iv_search_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_search_back:
                onBackPressed();
                finish();
                break;
            case R.id.iv_search_search:

                trim = ivSearchEdit.getText().toString().trim();
                if (trim!=""&&trim.length()>0&&!trim.equals(getStringMethod(R.string.hint_search))){
                    Log.d("flag", "onViewClicked: "+trim);
                    startSearch(trim);
                }else {
                    ToastUtils.getInstance().toastShow(getStringMethod(R.string.tip_search));
                }
                break;
        }
    }
    private void startSearch(String string) {
        Bundle bundle=new Bundle();
        bundle.putString("title",string);
        openActivity(SearchResultActivity.class,bundle);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
