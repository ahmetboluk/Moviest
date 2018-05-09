package com.example.ahmetboluk.moviest.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ahmetboluk.moviest.Data.SeriesResult;
import com.example.ahmetboluk.moviest.R;

import java.util.List;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.MyViewHolder>{
    private List<SeriesResult> mSeriesResult;
    private Context mContext;
    public TvAdapter (Context context, List<SeriesResult> seriesResult){
        this.mContext=context;
        this.mSeriesResult=seriesResult;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_contact,parent,false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(mSeriesResult.get(position).getName());
        holder.count.setText(mSeriesResult.get(position).getVoteCount().toString());
        holder.date.setText(mSeriesResult.get(position).getFirstAirDate());
        Glide.with(mContext).load("https://image.tmdb.org/t/p/w185"+mSeriesResult.get(position).getPosterPath()).into(holder.thumbnail);

    }
    @Override
    public int getItemCount() {
        return mSeriesResult.size();
    }

    public SeriesResult getItem(int position) { return mSeriesResult.get(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count, date;
        public ImageView thumbnail;
        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            count = (TextView) itemView.findViewById(R.id.count);
            date = (TextView) itemView.findViewById(R.id.date);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
        }
    }
}
