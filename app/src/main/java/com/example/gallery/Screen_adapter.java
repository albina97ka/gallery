package com.example.gallery;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

@SuppressWarnings("deprecation")
public class Screen_adapter extends FragmentStatePagerAdapter {
    private final ArrayList<Main_image> images;
    public Screen_adapter(FragmentManager fm, ArrayList<Main_image> images) {
        super(fm);
        this.images = images;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {return ScreenFr.newInstance(images.get(position).getPath());}

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {return images.get(position).getTitle();}
}