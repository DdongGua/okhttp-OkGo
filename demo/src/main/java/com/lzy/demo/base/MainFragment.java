package com.lzy.demo.base;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.demo.R;
import com.lzy.demo.model.ItemModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * ================================================
 * 作    者：jeasonlzy（廖子尧）Github地址：https://github.com/jeasonlzy
 * 版    本：1.0
 * 创建日期：2017/6/9
 * 描    述：
 * 修订历史：
 * ================================================
 */
public abstract class MainFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @Bind(R.id.refreshLayout) SwipeRefreshLayout refreshLayout;
    @Bind(R.id.recyclerView) RecyclerView recyclerView;

    protected Context context;
    private MainAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_refresh, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onRefresh() {
        setRefreshing(false);
    }

    @Override
    public void onLoadMoreRequested() {
        adapter.loadComplete();
    }

    @Override
    protected void initData() {
        List<ItemModel> items = new ArrayList<>();
        fillData(items);

        adapter = new MainAdapter(items);
        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        adapter.isFirstOnly(false);
        adapter.setOnLoadMoreListener(this);

        refreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        refreshLayout.setOnRefreshListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));

        recyclerView.setAdapter(adapter);
    }

    public abstract void fillData(List<ItemModel> items);

    public abstract void onItemClick(int position);

    private class MainAdapter extends BaseQuickAdapter<ItemModel> {

        MainAdapter(List<ItemModel> data) {
            super(R.layout.item_main_list, data);
        }

        @Override
        protected void convert(final BaseViewHolder baseViewHolder, ItemModel itemModel) {
            baseViewHolder.setText(R.id.title, itemModel.title);
            baseViewHolder.setText(R.id.des, itemModel.des);
            baseViewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick(baseViewHolder.getAdapterPosition());
                }
            });
        }
    }

    public void setRefreshing(final boolean refreshing) {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(refreshing);
            }
        });
    }
}
