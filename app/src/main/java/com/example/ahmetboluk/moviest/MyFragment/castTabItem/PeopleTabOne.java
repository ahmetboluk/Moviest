package com.example.ahmetboluk.moviest.MyFragment.castTabItem;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ahmetboluk.moviest.R;


public class PeopleTabOne extends Fragment {

    TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_people_tab_one, container, false);

        textView = view.findViewById(R.id.tv_biography);
        return view;
    }



}
