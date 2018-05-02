package com.example.ahmetboluk.moviest.MyFragment.castTabItem;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ahmetboluk.moviest.Data.peopleDetail.PersonDetail;
import com.example.ahmetboluk.moviest.R;


public class PeopleTabThree extends Fragment {

    public PeopleTabThree() {
        // Required empty public constructor
    }

    public static PeopleTabThree newInstance() {
        PeopleTabThree fragment = new PeopleTabThree();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_people_tab_three, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }



}
