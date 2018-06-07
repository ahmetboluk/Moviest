package com.example.ahmetboluk.moviest.myFragment;


import android.content.ContentResolver;
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
import com.example.ahmetboluk.moviest.dataBase.DatabaseResult;
import com.example.ahmetboluk.moviest.dataBase.MoviestContract;

import java.util.ArrayList;

public class DialogFragment extends android.support.v4.app.DialogFragment {

    public DialogFragment() {
        // Required empty public constructor
    }

    Button addToWatch, addWatched, addFavorites;
    DatabaseResult databaseResult;
    Cursor cursor;

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
                if (databaseResult.itemid == getArguments().getInt("itemid") && (databaseResult.categoryId == 2 || databaseResult.categoryId == 3)) {
                    ContentValues values = new ContentValues();
                    values.put(MoviestContract.MoviestItemEntry.COLUMN_CATEGORY, 1);
                    int recordsUpdated = MainActivity.getAppContext().getContentResolver().update(MoviestContract.MoviestItemEntry.CONTENT_URI, values, "itemid=?", new String[]{String.valueOf(getArguments().getInt("itemid"))});
                    Toast.makeText(getContext(), "First Remove From Watch" + recordsUpdated, Toast.LENGTH_SHORT).show();
                    getDb();
                } else if (!(databaseResult.itemid == getArguments().getInt("itemid") && databaseResult.categoryId == 1)) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(MoviestContract.MoviestItemEntry.COLUMN_ID, String.valueOf(getArguments().getInt("itemid")));
                    contentValues.put(MoviestContract.MoviestItemEntry.COLUMN_NAME, getArguments().getString("name"));
                    contentValues.put(MoviestContract.MoviestItemEntry.COLUMN_YEAR, getArguments().getString("date"));
                    contentValues.put(MoviestContract.MoviestItemEntry.COLUMN_THUMBNAIL_URL, getArguments().getString("poster"));
                    contentValues.put(MoviestContract.MoviestItemEntry.COLUMN_RATE, getArguments().getString("rate"));
                    contentValues.put(MoviestContract.MoviestItemEntry.COLUMN_TYPE, getArguments().getInt("selected"));
                    contentValues.put(MoviestContract.MoviestItemEntry.COLUMN_CATEGORY, 1);
                    Uri _uri = MainActivity.getAppContext().getContentResolver().insert(MoviestContract.MoviestItemEntry.CONTENT_URI, contentValues);
                    getDb();
                } else {
                    ContentResolver contentResolver = MainActivity.getAppContext().getContentResolver();
                    int i = contentResolver.delete(MoviestContract.MoviestItemEntry.CONTENT_URI, "itemid=?", new String[]{String.valueOf(getArguments().getInt("itemid"))});
                    getDb();
                    dismiss();
                    Toast.makeText(getContext(), "TO BE CONTINUED" + i, Toast.LENGTH_SHORT).show();
                }
            }
        });
        addWatched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (databaseResult.itemid == getArguments().getInt("itemid") && (databaseResult.categoryId == 1 || databaseResult.categoryId == 3)) {
                    ContentValues values = new ContentValues();
                    values.put(MoviestContract.MoviestItemEntry.COLUMN_CATEGORY, 2);
                    int recordsUpdated = MainActivity.getAppContext().getContentResolver().update(MoviestContract.MoviestItemEntry.CONTENT_URI, values, "itemid=?", new String[]{String.valueOf(getArguments().getInt("itemid"))});
                    Toast.makeText(getContext(), "First Remove From Watch" + recordsUpdated, Toast.LENGTH_SHORT).show();
                    getDb();
                } else if (!(databaseResult.itemid == getArguments().getInt("itemid") && databaseResult.categoryId == 2)) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(MoviestContract.MoviestItemEntry.COLUMN_ID, String.valueOf(getArguments().getInt("itemid")));
                    contentValues.put(MoviestContract.MoviestItemEntry.COLUMN_NAME, getArguments().getString("name"));
                    contentValues.put(MoviestContract.MoviestItemEntry.COLUMN_YEAR, getArguments().getString("date"));
                    contentValues.put(MoviestContract.MoviestItemEntry.COLUMN_THUMBNAIL_URL, getArguments().getString("poster"));
                    contentValues.put(MoviestContract.MoviestItemEntry.COLUMN_RATE, getArguments().getString("rate"));
                    contentValues.put(MoviestContract.MoviestItemEntry.COLUMN_TYPE, getArguments().getInt("selected"));
                    contentValues.put(MoviestContract.MoviestItemEntry.COLUMN_CATEGORY, 2);
                    Uri _uri = MainActivity.getAppContext().getContentResolver().insert(MoviestContract.MoviestItemEntry.CONTENT_URI, contentValues);
                    getDb();
                } else {
                    ContentResolver contentResolver = MainActivity.getAppContext().getContentResolver();
                    int i = contentResolver.delete(MoviestContract.MoviestItemEntry.CONTENT_URI, "itemid=?", new String[]{String.valueOf(getArguments().getInt("itemid"))});
                    getDb();
                    dismiss();
                    Toast.makeText(getContext(), "TO BE CONTINUED" + i, Toast.LENGTH_SHORT).show();
                }
            }
        });
        addFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (databaseResult.itemid == getArguments().getInt("itemid") && (databaseResult.categoryId == 1) || databaseResult.categoryId == 2) {
                    ContentValues values = new ContentValues();
                    values.put(MoviestContract.MoviestItemEntry.COLUMN_CATEGORY, 3);
                    int recordsUpdated = MainActivity.getAppContext().getContentResolver().update(MoviestContract.MoviestItemEntry.CONTENT_URI, values, "itemid=?", new String[]{String.valueOf(getArguments().getInt("itemid"))});
                    Toast.makeText(getContext(), "First Remove From Watch" + recordsUpdated, Toast.LENGTH_SHORT).show();
                    getDb();
                } else if (!(databaseResult.itemid == getArguments().getInt("itemid") && databaseResult.categoryId == 3)) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(MoviestContract.MoviestItemEntry.COLUMN_ID, String.valueOf(getArguments().getInt("itemid")));
                    contentValues.put(MoviestContract.MoviestItemEntry.COLUMN_NAME, getArguments().getString("name"));
                    contentValues.put(MoviestContract.MoviestItemEntry.COLUMN_YEAR, getArguments().getString("date"));
                    contentValues.put(MoviestContract.MoviestItemEntry.COLUMN_THUMBNAIL_URL, getArguments().getString("poster"));
                    contentValues.put(MoviestContract.MoviestItemEntry.COLUMN_RATE, getArguments().getString("rate"));
                    contentValues.put(MoviestContract.MoviestItemEntry.COLUMN_TYPE, getArguments().getInt("selected"));
                    contentValues.put(MoviestContract.MoviestItemEntry.COLUMN_CATEGORY, 3);
                    Uri _uri = MainActivity.getAppContext().getContentResolver().insert(MoviestContract.MoviestItemEntry.CONTENT_URI, contentValues);
                    getDb();
                } else {
                    ContentResolver contentResolver = MainActivity.getAppContext().getContentResolver();
                    int i = contentResolver.delete(MoviestContract.MoviestItemEntry.CONTENT_URI, "itemid=?", new String[]{String.valueOf(getArguments().getInt("itemid"))});
                    getDb();
                    dismiss();
                    Toast.makeText(getContext(), "TO BE CONTINUED" + i, Toast.LENGTH_SHORT).show();
                }
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
        cursor = MainActivity.getAppContext().getContentResolver().query(MoviestContract.MoviestItemEntry.CONTENT_URI, projection, "itemid=?", new String[]{String.valueOf(getArguments().getInt("itemid"))}, null, null);
        databaseResult = new DatabaseResult();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                databaseResult.id = cursor.getInt(0);
                databaseResult.itemid = cursor.getInt(1);
                databaseResult.name = cursor.getString(2);
                databaseResult.year = cursor.getString(3);
                databaseResult.thumbnailUrl = cursor.getString(4);
                databaseResult.rate = cursor.getString(5);
                databaseResult.type = cursor.getInt(6);
                databaseResult.categoryId = cursor.getInt(7);
            }
            cursor.close();
        }
        switch (databaseResult.categoryId) {
            case 1:
                if (databaseResult.itemid == getArguments().getInt("itemid")) {
                    addToWatch.setText("Delete Watch");
                    addWatched.setText("Move to Watched");
                    addFavorites.setText("Move to Favorities");
                }
                break;
            case 2:
                if (databaseResult.itemid == getArguments().getInt("itemid")) {
                    addWatched.setText("Delete Watched");
                    addFavorites.setText("Move to Favorities");
                    addToWatch.setText("Move to Watch");
                }
                break;
            case 3:
                if (databaseResult.itemid == getArguments().getInt("itemid")) {
                    addFavorites.setText("Delete Favorities");
                    addWatched.setText("Move to Watched");
                    addToWatch.setText("Move to Watch");
                }
                break;
            default:
                addToWatch.setText("Add To Watch");
                addWatched.setText("Add Watched");
                addFavorites.setText("Add Favorities");
        }
    }
}