package com.alamincmt.news.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alamincmt.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.alamincmt.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.alamincmt.autoimageslider.SliderAnimations;
import com.alamincmt.autoimageslider.SliderView;
import com.alamincmt.news.adapters.SliderAdapterExample;
import com.alamincmt.news.adapters.ViewPagerAdapter;
import com.alamincmt.news.listeners.ItemsDetailsClickListener;
import com.alamincmt.news.adapters.NewsAdapter;
import com.alamincmt.news.adapters.NewsModel;
import com.alamincmt.news.R;
import com.alamincmt.news.model.Article;
import com.alamincmt.news.model.NewsResponse;
import com.alamincmt.news.model.SliderItem;
import com.alamincmt.news.network.RestClient;
import com.alamincmt.news.ui.DetailsActivity;
import com.alamincmt.news.ui.MainActivity;
import com.alamincmt.news.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class NewsFragment extends Fragment implements ItemsDetailsClickListener, RestClient.ResponseListener {

    public NewsFragment() {
    }

    MainActivity mainActivity;

    NewsAdapter newsAdapter;
    RecyclerView rv;
    ImageView ivShareNews;
    TextView tvSliderTitle;
    ArrayList<NewsModel> itemList;

    RestClient restClient;
    Utils utils;

    SliderView sliderView;
    private SliderAdapterExample adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        mainActivity = new MainActivity();

        itemList = new ArrayList<>();

        rv = view.findViewById(R.id.rv);
        ivShareNews = view.findViewById(R.id.ivShareNews);
        tvSliderTitle = view.findViewById(R.id.tvSliderTitle);
        tvSliderTitle.setText("News org.");
        newsAdapter = new NewsAdapter(itemList,this);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(newsAdapter);

        restClient = new RestClient(getContext());
        restClient.setResponseListener(this);
        restClient.getTopNews("us", "business");


        sliderView = view.findViewById(R.id.imageSlider);
        adapter = new SliderAdapterExample(getActivity());
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();

        sliderView.setCurrentPageListener(new SliderView.OnSliderPageListener() {
            @Override
            public void onSliderPageChanged(int position) {
                if(itemList.get(sliderView.getCurrentPagePosition()).getAuthor() == null || itemList.get(sliderView.getCurrentPagePosition()).getAuthor().equals("")){
                    tvSliderTitle.setText("News org.");
                }else{
                    tvSliderTitle.setText(itemList.get(sliderView.getCurrentPagePosition()).getAuthor());
                }
            }
        });

        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                Log.i("GGG", "onIndicatorClicked: " + sliderView.getCurrentPagePosition());
            }
        });

        ivShareNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareTextUrl(itemList.get(sliderView.getCurrentPagePosition()).getTitle(), itemList.get(sliderView.getCurrentPagePosition()).getImgUri());
            }
        });

        return view;
    }


    @Override
    public void itemClick(NewsModel newsModel) {
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra("imageUri", newsModel.getImgUri());
        intent.putExtra("sourceName", newsModel.getSourceName());
        intent.putExtra("title", newsModel.getTitle());
        intent.putExtra("author", newsModel.getAuthor());
        intent.putExtra("publishedDate", newsModel.getPublishDate());
        intent.putExtra("description", newsModel.getDescription());
        intent.putExtra("authorID", newsModel.getAuthorID());
        getActivity().startActivity(intent);
    }

    @Override
    public void onResponse(@NonNull Call<NewsResponse> call, @NonNull Response<NewsResponse> responseElement) {
        if (responseElement.body() != null){
            Log.d("RESPONSE", "onResponse : Status => " + responseElement.body().getStatus());
            Log.d("RESPONSE", "onResponse : TotalResults => " + responseElement.body().getTotalResults());
            for (Article articles : responseElement.body().getArticles()) {
                itemList.add(new NewsModel(articles.getUrlToImage(), articles.getTitle(), articles.getDescription(), articles.getSource().getName(), articles.getAuthor(), articles.getPublishedAt(), articles.getSource().getId() + ""));
            }
        }
        if (responseElement.isSuccessful()){
            newsAdapter.notifyDataSetChanged();
            renewItems(itemList);
        }
    }

    @Override
    public void onFailure(@NonNull Call<NewsResponse> call, @NonNull Throwable t) {
        Log.d("RESPONSE", "onFailure : " + t.getMessage());
    }

    public void renewItems(ArrayList<NewsModel> itemList) {
        List<SliderItem> sliderItemList = new ArrayList<>();
        for (int i = 0; i < itemList.size(); i++) {
            SliderItem sliderItem = new SliderItem();
            sliderItem.setDescription(itemList.get(i).getSourceName());
            sliderItem.setImageUrl(itemList.get(i).getImgUri());
            sliderItemList.add(sliderItem);
        }
        adapter.renewItems(sliderItemList);
    }

    public void shareTextUrl(String title, String shareUrl) {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        // Add data to the intent, the receiving app will decide
        // what to do with it.
        share.putExtra(Intent.EXTRA_SUBJECT, title);
        share.putExtra(Intent.EXTRA_TEMPLATE, "This is template... ");
        share.putExtra(Intent.EXTRA_TEXT, "News Sharing...\n"+shareUrl);

        startActivity(Intent.createChooser(share, "Share"));

    }
}