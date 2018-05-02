package com.example.ahmetboluk.moviest.MyFragment.castTabItem;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.ahmetboluk.moviest.Data.peopleDetail.PersonDetail;

public class PeoplePagerAdapter extends FragmentStatePagerAdapter{

    int mNoOfTabs;

    public PeoplePagerAdapter(FragmentManager fm, int NumberOfTabs) {
        super(fm);
        this.mNoOfTabs = NumberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                PeopleTabOne tabOne = new PeopleTabOne();
                return tabOne;
            case 1:
                PeopleTabTwo tabTwo = new PeopleTabTwo();
                return tabTwo;
            case 2:
                PeopleTabThree tabThree = new PeopleTabThree();
                return tabThree;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNoOfTabs;
    }

}
