package com.example.ahmetboluk.moviest;

import android.Manifest;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;


import com.example.ahmetboluk.moviest.myFragment.AboutFragment;
import com.example.ahmetboluk.moviest.myFragment.BottomTabLayotListener;
import com.example.ahmetboluk.moviest.myFragment.ListFragment;
import com.example.ahmetboluk.moviest.myFragment.SearchFragment;
import com.example.ahmetboluk.moviest.myFragment.mainTabItem.PagerAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int MOVIES_SELECTED = 0;
    private static final int SERIES_SELECTED = 1;
    private int lastchooseOne = 0;
    private int lastchooseTwo = 0;


    private static Context mContext;
    private ImageButton imageButton;

    public static Context getAppContext() {
        return mContext;
    }
//SON GG

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
        tabLayoutbottom.addTab(tabLayoutbottom.newTab().setText("Lists"));
        tabLayoutbottom.addTab(tabLayoutbottom.newTab().setText("Credits"));
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
        //FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        FragmentManager manager = this.getSupportFragmentManager();
        //BU SATIRLAR GERİ GELİNMESİ DURUMLARINI AYARLANMAK İÇİN EKLENMİŞTİR

        //

        if (position == MOVIES_SELECTED) {
            for (int i = 0; i < fragments.size(); i++) {
                if (fragments.get(i) instanceof BottomTabLayotListener) {
                    ((BottomTabLayotListener) fragments.get(i)).onMovieSelected();
                }
                if (lastchooseOne == 1) {
                    getSupportFragmentManager().beginTransaction().detach(manager.findFragmentByTag("List")).commit();
                    lastchooseOne = 0;
                }else if (lastchooseTwo == 2) {
                    getSupportFragmentManager().beginTransaction().detach(manager.findFragmentByTag("About")).commit();
                    lastchooseTwo = 0;
                }
            }
        } else if (position == SERIES_SELECTED) {
            for (int i = 0; i < fragments.size(); i++) {
                if (fragments.get(i) instanceof BottomTabLayotListener) {
                    ((BottomTabLayotListener) fragments.get(i)).onSeriesSelected();
                }
                if (lastchooseOne == 1) {
                    getSupportFragmentManager().beginTransaction().detach(manager.findFragmentByTag("List")).commit();
                    lastchooseOne = 0;
                }else if (lastchooseTwo == 2) {
                    getSupportFragmentManager().beginTransaction().detach(manager.findFragmentByTag("About")).commit();

                    lastchooseTwo = 0;
                }
            }
        } else if (position == 2) {
            ListFragment listFragment = new ListFragment();
            //fragmentTransaction.replace(R.id.rl_solution, listFragment, "LIST");
            //fragmentTransaction.commit();
            manager.beginTransaction()
                    .replace(R.id.rl_solution, listFragment, "List")
                    .commit();
            lastchooseOne = 1;
            //BU SATIRLAR GERİ GELİNMESİ DURUMLARINI AYARLANMAK İÇİN EKLENMİŞTİR
        } else if (position == 3) {
            AboutFragment aboutFragment = new AboutFragment();
            //fragmentTransaction.replace(R.id.rl_solution, aboutFragment, "ABOUT");
            //fragmentTransaction.commit();
            manager.beginTransaction()
                    .replace(R.id.rl_solution, aboutFragment, "About")
                    .commit();
            lastchooseTwo = 2;
            //BU SATIRLAR GERİ GELİNMESİ DURUMLARINI AYARLANMAK İÇİN EKLENMİŞTİR
        }
    }
}