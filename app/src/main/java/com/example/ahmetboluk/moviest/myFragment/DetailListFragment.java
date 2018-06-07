package com.example.ahmetboluk.moviest.myFragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ahmetboluk.moviest.MainActivity;
import com.example.ahmetboluk.moviest.R;
import com.example.ahmetboluk.moviest.adapter.ListAdapter;
import com.example.ahmetboluk.moviest.dataBase.DatabaseResult;
import com.example.ahmetboluk.moviest.dataBase.MoviestContract;

import java.util.ArrayList;

public class DetailListFragment extends Fragment {


    ArrayList<DatabaseResult> databaseResultArrayList;
    ArrayList<DatabaseResult> movieArrayList;
    ArrayList<DatabaseResult> seriesArrayList;
    DatabaseResult databaseResult;
    Cursor cursor;
    RecyclerView movieRecyclerView, seriesRecyclerView;
    LinearLayoutManager mLayoutManager, sLayoutManager;
    ListAdapter mAdapter, sAdapter;
    TextView textViewEmtyOne,textViewEmtyTwo;
    FragmentManager fragmentManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getActivity().getSupportFragmentManager();
        cursor = MainActivity.getAppContext().getContentResolver().query(MoviestContract.MoviestItemEntry.CONTENT_URI, new String[]{
                "moviestItem._id",
                "moviestItem.itemid",
                "moviestItem.name",
                "moviestItem.year",
                "moviestItem.thumbnailUrl",
                "moviestItem.rate",
                "moviestItem.type",
                "moviestItem.categoryId"}, null, null, null, null);
        databaseResultArrayList = new ArrayList<>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                databaseResult = new DatabaseResult();
                databaseResult.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                databaseResult.setItemid(cursor.getInt(cursor.getColumnIndex("itemid")));
                databaseResult.setName(cursor.getString(cursor.getColumnIndex("name")));
                databaseResult.setYear(cursor.getString(cursor.getColumnIndex("year")));
                databaseResult.setThumbnailUrl(cursor.getString(cursor.getColumnIndex("thumbnailUrl")));
                databaseResult.setRate(cursor.getString(cursor.getColumnIndex("rate")));
                databaseResult.setType(cursor.getInt(cursor.getColumnIndex("type")));
                databaseResult.setCategoryId(cursor.getInt(cursor.getColumnIndex("categoryId")));
                databaseResultArrayList.add(databaseResult);
            }
        }
        cursor.close();
        movieArrayList = new ArrayList<>();
        seriesArrayList = new ArrayList<>();
        switch (getArguments().getInt("list")) {
            case 1:
                for (int i = 0; i < databaseResultArrayList.size(); i++) {
                    if (databaseResultArrayList.get(i).getType() == 0 && databaseResultArrayList.get(i).getCategoryId() == 1) {
                        movieArrayList.add(databaseResultArrayList.get(i));
                    } else if (databaseResultArrayList.get(i).getType() == 1 && databaseResultArrayList.get(i).getCategoryId() == 1) {
                        seriesArrayList.add(databaseResultArrayList.get(i));
                    }
                }
                break;
            case 2:
                for (int i = 0; i < databaseResultArrayList.size(); i++) {
                    if (databaseResultArrayList.get(i).getType() == 0 && databaseResultArrayList.get(i).getCategoryId() == 2) {
                        movieArrayList.add(databaseResultArrayList.get(i));
                    } else if (databaseResultArrayList.get(i).getType() == 1 && databaseResultArrayList.get(i).getCategoryId() == 2) {
                        seriesArrayList.add(databaseResultArrayList.get(i));
                    }
                }
                break;
            case 3:
                for (int i = 0; i < databaseResultArrayList.size(); i++) {
                    if (databaseResultArrayList.get(i).getType() == 0 && databaseResultArrayList.get(i).getCategoryId() == 3) {
                        movieArrayList.add(databaseResultArrayList.get(i));
                    } else if (databaseResultArrayList.get(i).getType() == 1 && databaseResultArrayList.get(i).getCategoryId() == 3) {
                        seriesArrayList.add(databaseResultArrayList.get(i));
                    }
                }
                break;
            default:
                throw new IllegalArgumentException("geçmiş olsun");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_list, container, false);

        DividerItemDecoration itemDecorator = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);

        textViewEmtyOne=view.findViewById(R.id.tv_is_emty_movie);
        textViewEmtyTwo=view.findViewById(R.id.tv_is_emty_series);

        movieRecyclerView = (RecyclerView) view.findViewById(R.id.movie_list_recycler);
        mLayoutManager = new LinearLayoutManager(getContext());
        itemDecorator.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.divider));
        movieRecyclerView.addItemDecoration(itemDecorator);
        movieRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ListAdapter(MainActivity.getAppContext(), movieArrayList);
        movieRecyclerView.setAdapter(mAdapter);

        if (movieArrayList.size()==0){
            textViewEmtyOne.setVisibility(View.VISIBLE);
        }

        seriesRecyclerView = (RecyclerView) view.findViewById(R.id.series_list_recycler);
        sLayoutManager = new LinearLayoutManager(getContext());
        itemDecorator.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.divider));
        seriesRecyclerView.addItemDecoration(itemDecorator);
        seriesRecyclerView.setLayoutManager(sLayoutManager);
        sAdapter = new ListAdapter(MainActivity.getAppContext(), seriesArrayList);
        seriesRecyclerView.setAdapter(sAdapter);

        if (seriesArrayList.size()==0){
            textViewEmtyTwo.setVisibility(View.VISIBLE);
        }

        return view;
    }

}