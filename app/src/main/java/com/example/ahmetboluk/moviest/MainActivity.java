package com.example.ahmetboluk.moviest;

import android.Manifest;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;


import com.example.ahmetboluk.moviest.myFragment.BottomTabLayotListener;
import com.example.ahmetboluk.moviest.myFragment.SearchFragment;
import com.example.ahmetboluk.moviest.myFragment.mainTabItem.PagerAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int MOVIES_SELECTED = 0;
    private static final int SERIES_SELECTED = 1;

    private static Context mContext;
    private ImageButton imageButton;

    public static Context getAppContext() {
        return mContext;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.INTERNET}, 0);
        }
        mContext = getApplicationContext();
        imageButton = findViewById(R.id.main_search_button);


        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Genres"));
        tabLayout.addTab(tabLayout.newTab().setText("Popular"));
        tabLayout.addTab(tabLayout.newTab().setText("Top Rated"));
        tabLayout.addTab(tabLayout.newTab().setText("Now Playing"));
        tabLayout.addTab(tabLayout.newTab().setText("Up Coming"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        final TabLayout tabLayoutbottom = (TabLayout) findViewById(R.id.tab_layout_bottom);
        tabLayoutbottom.addTab(tabLayoutbottom.newTab().setText("Movie"));
        tabLayoutbottom.addTab(tabLayoutbottom.newTab().setText("Tv"));
        tabLayoutbottom.setTabGravity(tabLayoutbottom.GRAVITY_FILL);


        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setOffscreenPageLimit(4);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
        });

        tabLayoutbottom.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                onBottomLayoutSelected(tab.getPosition());
                if (tab.getPosition() == 1) {
                    tabLayout.getTabAt(3).setText("Airing Today");
                    tabLayout.getTabAt(4).setText("On The Air");
                } else {
                    tabLayout.getTabAt(3).setText("Now Playing");
                    tabLayout.getTabAt(4).setText("Up Coming");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                SearchFragment searchFragment = new SearchFragment();
                fragmentTransaction.setCustomAnimations(R.anim.enter_from_bottom_right, R.anim.exit_from_top_right, R.anim.enter_from_bottom_right, R.anim.exit_from_top_right);
                fragmentTransaction.add(R.id.main_activity, searchFragment, "SEARCH");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


    }

    private void onBottomLayoutSelected(int position) {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();

        if (position == MOVIES_SELECTED) {

            for (int i = 0; i < fragments.size(); i++) {

                if (fragments.get(i) instanceof BottomTabLayotListener) {

                    ((BottomTabLayotListener) fragments.get(i)).onMovieSelected();
                }
            }
        } else if (position == SERIES_SELECTED) {
            for (int i = 0; i < fragments.size(); i++) {
                if (fragments.get(i) instanceof BottomTabLayotListener) {
                    ((BottomTabLayotListener) fragments.get(i)).onSeriesSelected();
                }
            }
        }
    }

}


