package com.example.ahmetboluk.moviest.myFragment;


import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.ahmetboluk.moviest.MainActivity;
import com.example.ahmetboluk.moviest.R;
import com.example.ahmetboluk.moviest.dataBase.MoviestContract;

public class DialogFragment extends android.support.v4.app.DialogFragment {

    public DialogFragment() {
        // Required empty public constructor
    }

    Button addToWatch, addWatched, addFavorites;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dialog, container, false);
        addToWatch = view.findViewById(R.id.add_to_watch);
        addWatched = view.findViewById(R.id.add_watched);
        addFavorites = view.findViewById(R.id.add_favorites);

        getDb();

        addToWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(MoviestContract.MoviestItemEntry.COLUMN_ID, String.valueOf(getArguments().getInt("movie_id")));
                contentValues.put(MoviestContract.MoviestItemEntry.COLUMN_NAME, getArguments().getString("name"));
                contentValues.put(MoviestContract.MoviestItemEntry.COLUMN_YEAR, getArguments().getString("date"));
                contentValues.put(MoviestContract.MoviestItemEntry.COLUMN_THUMBNAIL_URL, getArguments().getString("poster"));
                contentValues.put(MoviestContract.MoviestItemEntry.COLUMN_RATE, getArguments().getString("rate"));
                contentValues.put(MoviestContract.MoviestItemEntry.COLUMN_TYPE, getArguments().getInt("selected"));
                contentValues.put(MoviestContract.MoviestItemEntry.COLUMN_CATEGORY, 0);
                Uri _uri = MainActivity.getAppContext().getContentResolver().insert(MoviestContract.MoviestItemEntry.CONTENT_URI, contentValues);
                Toast.makeText(getContext(), "OLDUMU" + _uri, Toast.LENGTH_LONG).show();

            }
        });
        addWatched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        addFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }

    private void getDb() {

        String[] projection = {
                "moviestItem._id",
                "moviestItem.itemid",
                "moviestItem.name",
                "moviestItem.year",
                "moviestItem.thumbnailUrl",
                "moviestItem.rate",
                "moviestItem.type",
                "moviestItem.categoryId"};
        Cursor cursor = MainActivity.getAppContext().getContentResolver().query(MoviestContract.MoviestItemEntry.CONTENT_URI, projection, "itemid=?", new String[]{String.valueOf(getArguments().getInt("movie_id"))}, null, null);
        String kayitlar = "";
        if (cursor!=null) {
            while (cursor.moveToNext()) {
                for (int j = 0; j <= 6; j++) {
                    kayitlar += cursor.getString(j) + " - ";
                }
                kayitlar += "\n";
            }
            cursor.close();
        }
        Log.i("TAG", "onClick: " + kayitlar);
        Toast.makeText(getContext(), "OLDUMU" + kayitlar, Toast.LENGTH_LONG).show();



    }

}
