package com.example.ahmetboluk.moviest.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ahmetboluk.moviest.data.Genres;
import com.example.ahmetboluk.moviest.R;

public class GenresAdapter extends RecyclerView.Adapter<GenresAdapter.MyviewHolder>{
    public Context mContext;
    public Genres mGenres;

    public GenresAdapter(Context context,Genres genres){
        mContext=context;
        mGenres=genres;

    }
    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.genres_line,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        holder.textView.setText(mGenres.getGenres().get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mGenres.getGenres().size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public MyviewHolder(View itemView) {
            super(itemView);

            textView=itemView.findViewById(R.id.tv_movie_genre_name);
        }
    }
}
