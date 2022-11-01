package com.alamincmt.news.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.alamincmt.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.alamincmt.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.alamincmt.autoimageslider.SliderAnimations;
import com.alamincmt.autoimageslider.SliderView;
import com.alamincmt.news.R;
import com.alamincmt.news.adapters.SliderAdapterExample;
import com.alamincmt.news.adapters.ViewPagerAdapter;
import com.alamincmt.news.model.SliderItem;
import com.alamincmt.news.network.RestClient;
import com.alamincmt.news.utils.Utils;
import com.google.android.material.tabs.TabLayout;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener{
    public TabLayout tabLayout;
    public ViewPager viewPager;
    public ViewPagerAdapter viewPagerAdapter;
    private Utils utils;
    private RestClient restClient;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.utils = new Utils(getApplicationContext());
        this.restClient = new RestClient(getApplicationContext());

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tabL);

        View root = tabLayout.getChildAt(0);
        if (root instanceof LinearLayout) {
            ((LinearLayout) root).setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setColor(getResources().getColor(R.color.white));
            drawable.setSize(2, 1);
            ((LinearLayout) root).setDividerPadding(30);
            ((LinearLayout) root).setDividerDrawable(drawable);
        }

        tabLayout.addTab(tabLayout.newTab().setText("News"));
        tabLayout.addTab(tabLayout.newTab().setText("Bookmark"));

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setOnTabSelectedListener(this);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Objects.requireNonNull(tabLayout.getTabAt(position)).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


//        restClient.getTopNews("us", "business");
//        if (utils.isNetworkAvailable()) {
//            restClient.getTopNews("us", "business");
//        } else {
//            utils.showToast("Please connect to the internet and try again. ");
//        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}