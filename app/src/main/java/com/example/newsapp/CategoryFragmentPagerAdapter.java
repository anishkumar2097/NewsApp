package com.example.newsapp;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.newsapp.utils.Constants;

public class CategoryFragmentPagerAdapter extends FragmentPagerAdapter {
   private Context mContext;
    private String tabTitles[] = new String[] {  "Home" ,"Business","Sport"};
    public CategoryFragmentPagerAdapter(FragmentManager fm, Context context){
        super(fm,CategoryFragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.mContext=context;
    }

public Fragment getItem(int position){

        switch(position){

            case Constants.HOME:
            return new HomeFragment();


            case Constants.BUSINESS:
            return new BusinessFragment();

            case Constants.SPORT:
                return new SportFragment();
            default:
                return null;
        }
}

public int getCount(){
        return 3;
}

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
      return tabTitles[position];

          }
    }



