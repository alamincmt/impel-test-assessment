package com.alamincmt.news.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alamincmt.news.listeners.ItemsClickListener;
import com.alamincmt.news.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private ArrayList<NewsModel> itemList = new ArrayList<>();
    private ItemsClickListener itemsDetailsClickListener;
    private Context context;

    public NewsAdapter(ArrayList<NewsModel> itemList, ItemsClickListener itemsDetailsClickListener) {
        this.itemList = itemList;
        this.itemsDetailsClickListener = itemsDetailsClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(itemList.get(position).getImgUri()).into(holder.imageView);
        holder.title.setText(itemList.get(position).getTitle());
        holder.description.setText(itemList.get(position).getDescription());
        holder.readMore.setOnClickListener(v -> itemsDetailsClickListener.itemClick(itemList.get(holder.getAdapterPosition())));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView title;
        private TextView readMore;
        private TextView description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgV);
            title = itemView.findViewById(R.id.tv_title);
            readMore = itemView.findViewById(R.id.tv_read_more);
            description = itemView.findViewById(R.id.tv_description);
        }
    }
}
