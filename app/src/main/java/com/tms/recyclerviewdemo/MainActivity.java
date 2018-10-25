package com.tms.recyclerviewdemo;

import android.annotation.TargetApi;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NewsAdapter.OnClickItemListener,
        View.OnClickListener, NewsAdapter.OnDeleteItemListener {
    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;


    private List<NewsBean> list = new ArrayList<>();
    private NewsAdapter adapter;
    private Button btnShow;
    private Button btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = ((RecyclerView) findViewById(R.id.recyclerView));
        btnShow = ((Button) findViewById(R.id.btn_show));
        btnReset = ((Button) findViewById(R.id.btn_reset));


        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(manager);

        adapter = new NewsAdapter(this, list);
        recyclerView.setAdapter(adapter);

        iniListener();
    }

    private void iniListener() {
        btnShow.setOnClickListener(this);
        btnReset.setOnClickListener(this);
        adapter.setOnClickItemListener(this);
        adapter.setOnDeleteItemListener(this);
    }


    private void initData() {
        List<NewsBean> data = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            data.add(new NewsBean("Title " + i, false));
        }
        adapter.addAll(data);
    }

    private void clearAddData() {
        List<NewsBean> data = new ArrayList<>();

        for (int i = 50; i < 100; i++) {
            data.add(new NewsBean("Title " + i, false));
        }
        adapter.clearAddAll(data);
    }

    @Override
    public void onClickItem(View view, int position) {
        Log.i(TAG, "onClickItem position:" + position);
 /*       NewsBean item = adapter.getItem(position);
        String newsTitle = item.getNewsTitle();
        Intent intent = new Intent(this, NewsActivity.class);
        intent.putExtra("title", newsTitle);
        startActivity(intent);*/

        NewsBean item = adapter.getItem(position);

        adapter.changeToHasReadUI(position);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show:
                initData();
                break;
            case R.id.btn_reset:
                clearAddData();
                break;
        }
    }

    @Override
    public void onDeleteItem(View view, int position) {
        Log.i(TAG, "onDeleteItem position:" + position);
        adapter.removeItem(position);
        //  adapter.removeItemAndNotify(position);
    }
}
