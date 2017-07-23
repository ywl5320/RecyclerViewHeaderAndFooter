package com.ywl5320.recyclerveiwdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private WapHeaderAndFooterAdapter headerAndFooterAdapter;
    private List<String> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        datas = new ArrayList<>();
        for(int i = 0; i < 60; i++)
        {
            datas.add(i + "");
        }
        myAdapter = new MyAdapter(this, datas);
        headerAndFooterAdapter = new WapHeaderAndFooterAdapter(myAdapter);

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(linearLayoutManager);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);

        View header = LayoutInflater.from(this).inflate(R.layout.header_view, recyclerView, false);
        View footer = LayoutInflater.from(this).inflate(R.layout.footer_view, recyclerView, false);

        headerAndFooterAdapter.addHeader(header);
        headerAndFooterAdapter.addFooter(footer);

        recyclerView.setAdapter(headerAndFooterAdapter);
        headerAndFooterAdapter.setOnloadMoreListener(new WapHeaderAndFooterAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                Toast.makeText(MainActivity.this, "加载更多", Toast.LENGTH_SHORT).show();
            }
        }, recyclerView);
    }

}
