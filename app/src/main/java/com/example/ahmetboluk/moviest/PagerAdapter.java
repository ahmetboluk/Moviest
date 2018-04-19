package com.example.ahmetboluk.moviest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter{

    int mNoOfTabs;


    public PagerAdapter(FragmentManager fm, int NumberOfTabs) {
        super(fm);
        this.mNoOfTabs = NumberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                TabOne tabOne = new TabOne();
                return tabOne;
            case 1:
                TabTwo tabTwo = new TabTwo();
                return tabTwo;
            case 2:
                TabThree tabThree = new TabThree();
                return tabThree;
            case 3:
                TabFour tabFour  = new TabFour();
                return tabFour;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
