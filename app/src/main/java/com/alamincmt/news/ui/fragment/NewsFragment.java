package com.alamincmt.news.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alamincmt.news.listeners.ItemsDetailsClickListener;
import com.alamincmt.news.adapters.NewsAdapter;
import com.alamincmt.news.adapters.NewsModel;
import com.alamincmt.news.R;

import java.util.ArrayList;

public class NewsFragment extends Fragment implements ItemsDetailsClickListener {
    public NewsFragment() {}

    NewsAdapter newsAdapter;
    RecyclerView rv;
    ArrayList<NewsModel> itemList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        itemList = new ArrayList<>();
        rv = view.findViewById(R.id.rv);
        newsAdapter = new NewsAdapter(itemList, this);
        rv.setLayoutManager( new LinearLayoutManager(getContext()));
        rv.setAdapter(newsAdapter);

        for (int i= 0; i<10; i++){
            itemList.add(new NewsModel(i, "", ""));
        }

        return view;
    }


    @Override
    public void itemClick(NewsModel newsModel) {
        Log.d("NEWSF", " Details Click");
    }
}