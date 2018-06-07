package com.example.ahmetboluk.moviest.myFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ahmetboluk.moviest.R;

import java.util.List;

public class AboutFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        return view;

    }
    /*private void onBottomLayoutSelected(int position) {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        FragmentManager manager = this.getSupportFragmentManager();

        //BU SATIRLAR GERİ GELİNMESİ DURUMLARINI AYARLANMAK İÇİN EKLENMİŞTİR
        if(lastchooseOne==1&&lastchooseTwo!=2 ||lastchooseOne!=1&&lastchooseTwo==2){
            this.onBackPressed();
            lastchooseOne=0;
            lastchooseTwo=0;
        }
        //

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
        }else if (position == 2){
            ListFragment listFragment= new ListFragment();
            manager.beginTransaction()
                    .replace(R.id.rl_solution, listFragment,"List")
                    .addToBackStack(null)
                    .commit();

            //BU SATIRLAR GERİ GELİNMESİ DURUMLARINI AYARLANMAK İÇİN EKLENMİŞTİR
            lastchooseOne=1;
            lastchooseTwo=0;

        }else if (position == 3){
            AboutFragment aboutFragment = new AboutFragment();
            manager.beginTransaction()
                    .replace(R.id.rl_solution, aboutFragment,"About")
                    .addToBackStack(null)
                    .commit();

            //BU SATIRLAR GERİ GELİNMESİ DURUMLARINI AYARLANMAK İÇİN EKLENMİŞTİR
            lastchooseTwo=2;
            lastchooseOne=0;

        }
    }



    fragmentManager.beginTransaction()
                                .replace(R.id.main_activity, detailListFragment, null)
                                .addToBackStack(null)
                                .commit();

    */
}