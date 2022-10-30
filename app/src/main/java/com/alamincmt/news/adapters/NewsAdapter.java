package com.alamincmt.news.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alamincmt.news.listeners.ItemsDetailsClickListener;
import com.alamincmt.news.R;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private ArrayList<NewsModel> itemList;
    private ItemsDetailsClickListener itemsDetailsClickListener;

    public NewsAdapter(ArrayList<NewsModel> itemList, ItemsDetailsClickListener itemsDetailsClickListener) {
        this.itemList = itemList;
        this.itemsDetailsClickListener = itemsDetailsClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(v -> itemsDetailsClickListener.itemClick(itemList.get(holder.getAdapterPosition())));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
