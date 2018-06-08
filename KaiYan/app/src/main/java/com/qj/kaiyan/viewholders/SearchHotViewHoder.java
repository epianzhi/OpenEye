package com.qj.kaiyan.viewholders;

import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.qj.kaiyan.R;

public class SearchHotViewHoder extends BaseViewHolder<String> {

    TextView tvText;

    public SearchHotViewHoder(ViewGroup parent) {
        super(parent, R.layout.search_hot_item);
        tvText=$(R.id.tv_text);

    }

    @Override
    public void setData(String data) {
        super.setData(data);
        tvText.setText(data);
    }
}
