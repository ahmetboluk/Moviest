package com.example.ahmetboluk.moviest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ahmetboluk.moviest.Api.TmdbApi;
import com.example.ahmetboluk.moviest.Data.detail.Result;
import com.example.ahmetboluk.moviest.Data.detail.Cast;
import com.example.ahmetboluk.moviest.Data.detail.Detail;
import com.example.ahmetboluk.moviest.adapter.CastingAdapter;
import com.example.ahmetboluk.moviest.adapter.SimilarMovieAdapter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailFragment extends Fragment {
    RecyclerView castRecyclerView;
    RecyclerView similarRecyclerView;
    CastingAdapter castingAdapter;
    SimilarMovieAdapter similarMovieAdapter;
    LinearLayoutManager similarlayoutManager,castinglayoutManager;
    Detail movieDetail;
    TextView titleText,dateText,minuteText,genreText,nationText,scoreText,owerviewText;
    ImageView movieImage;


    public static final String API_KEY="31b2377287f733ce461c2d352a64060e";
    Retrofit api =new Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/").addConverterFactory(GsonConverterFactory.create()).build();

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance() {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            api.create(TmdbApi.class).listDetail(getArguments().getInt("movie_id"),API_KEY,"credits,similar").enqueue(new Callback<Detail>() {
                @Override
                public void onResponse(Call<Detail> call, Response<Detail> response) {
                    Log.d("URL",call.request().url().toString());
                    movieDetail=response.body();
                    Glide.with(getContext()).load("https://image.tmdb.org/t/p/w185"+movieDetail.getPosterPath()).into(movieImage);
                    titleText.setText(movieDetail.getTitle());
                    dateText.setText(movieDetail.getReleaseDate().substring(0,4));
                    minuteText.setText(movieDetail.getRuntime().toString());
                    genreText.setText(movieDetail.getGenres().get(0).getName());
                    nationText.setText(movieDetail.getProductionCountries().get(0).getName());
                    scoreText.setText(movieDetail.getVoteAverage().toString());
                    owerviewText.setText(movieDetail.getOverview());
                    castingAdapter = new CastingAdapter(getContext(), movieDetail.getCredits().getCast());
                    castinglayoutManager= new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
                    castRecyclerView.setLayoutManager(castinglayoutManager);
                    castRecyclerView.setAdapter(castingAdapter);
                    similarMovieAdapter = new SimilarMovieAdapter(getContext(), movieDetail.getSimilar().getResults());
                    similarlayoutManager= new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
                    similarRecyclerView.setLayoutManager(similarlayoutManager);
                    similarRecyclerView.setAdapter(similarMovieAdapter);


                }

                @Override
                public void onFailure(Call<Detail> call, Throwable t) {
                    Log.d("Error","Olmadı amk");

                }
            });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail,container,false);

        movieImage = (ImageView) view.findViewById(R.id.iv_movie_jpeg);
        titleText =(TextView) view.findViewById(R.id.tv_movie_title);
        dateText=   (TextView) view.findViewById(R.id.tv_movie_date);
        minuteText = (TextView) view.findViewById(R.id.tv_movie_minute);
        genreText = (TextView) view.findViewById(R.id.tv_movie_genre);
        nationText = (TextView) view.findViewById(R.id.tv_movie_nation);
        scoreText = (TextView) view.findViewById(R.id.tv_movie_score);
        owerviewText = (TextView) view.findViewById(R.id.tv_detail);
        castRecyclerView = (RecyclerView) view.findViewById(R.id.rv_credits);
        similarRecyclerView = (RecyclerView) view.findViewById(R.id.rv_similar_movie);
        return view;
    }


}
