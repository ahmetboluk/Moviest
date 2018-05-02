package com.example.ahmetboluk.moviest.MyFragment;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ahmetboluk.moviest.Api.TmdbApi;
import com.example.ahmetboluk.moviest.Data.movieDetail.Detail;
import com.example.ahmetboluk.moviest.R;
import com.example.ahmetboluk.moviest.RecyclerItemClickListener;
import com.example.ahmetboluk.moviest.adapter.CastingAdapter;
import com.example.ahmetboluk.moviest.adapter.SimilarMovieAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailFragment extends Fragment {
    AnimationDrawable animation;
    RecyclerView castRecyclerView;
    RecyclerView similarRecyclerView;
    CastingAdapter castingAdapter;
    SimilarMovieAdapter similarMovieAdapter;
    LinearLayoutManager similarlayoutManager,castinglayoutManager;
    Detail movieDetail;
    TextView titleText,dateText,minuteText,genreText,nationText,scoreText,owerviewText;
    ImageView movieImage;
    RelativeLayout relativeLayout;

    public static final String API_KEY="31b2377287f733ce461c2d352a64060e";
    Retrofit api =new Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/").addConverterFactory(GsonConverterFactory.create()).build();

    public DetailFragment() {

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
                    animation.stop();
                    relativeLayout.setVisibility(View.VISIBLE);
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

        ImageView loading = (ImageView) view.findViewById(R.id.im_loading);
        animation= (AnimationDrawable)loading.getDrawable();
        animation.start();

        relativeLayout = (RelativeLayout) view.findViewById(R.id.rv_layout);
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
        similarRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(),similarRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        DetailFragment detailFragment = new DetailFragment();
                        Bundle data=new Bundle();
                        data.putInt("movie_id",similarMovieAdapter.getItem(position).getId());
                        detailFragment.setArguments(data);
                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_activity,detailFragment,null);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                })
        );
        castRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), castRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        CastDetailFragment castDetailFragment = new CastDetailFragment();
                        Bundle data=new Bundle();
                        data.putInt("person_id",movieDetail.getCredits().getCast().get(position).getId());
                        castDetailFragment.setArguments(data);
                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_activity,castDetailFragment,null);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                })
        );
        return view;
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