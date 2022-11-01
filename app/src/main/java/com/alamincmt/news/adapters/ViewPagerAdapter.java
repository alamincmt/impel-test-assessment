package com.alamincmt.news.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.alamincmt.news.ui.fragment.BookmarkFragment;
import com.alamincmt.news.ui.fragment.NewsFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    int tabCount;

    public ViewPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
            return new NewsFragment();
            case 1:
                return new BookmarkFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}

