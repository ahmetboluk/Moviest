package com.example.ahmetboluk.moviest.myFragment.mainTabItem;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.ahmetboluk.moviest.api.TmdbApi;
import com.example.ahmetboluk.moviest.data.Genres;
import com.example.ahmetboluk.moviest.myFragment.BottomTabLayotListener;
import com.example.ahmetboluk.moviest.myFragment.GenreDetailFragment;
import com.example.ahmetboluk.moviest.R;
import com.example.ahmetboluk.moviest.RecyclerItemClickListener;
import com.example.ahmetboluk.moviest.adapter.GenresAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TabGenres extends Fragment implements BottomTabLayotListener {

    AnimationDrawable animation;

    public RecyclerView genreRecyclerView;
    public Genres mMovieGenres;
    public Genres mSeriesGenres;
    private GenresAdapter mMovieGenresAdapter;
    private GenresAdapter mSeriesGenresAdapter;
    private int SELECTED=0;
    private int SELECTED_MOVIE=0;
    private int SELECTED_TV=1;

    public RecyclerView.LayoutManager mLayoutManager;

    public static final String API_KEY="31b2377287f733ce461c2d352a64060e";
    Retrofit api =new Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/").addConverterFactory(GsonConverterFactory.create()).build();
    public TabGenres() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api.create(TmdbApi.class).listGenres(API_KEY).enqueue(new Callback<Genres>() {
            @Override
            public void onResponse(Call<Genres> call, Response<Genres> response) {
                mMovieGenres = response.body();
                mLayoutManager = new LinearLayoutManager(getContext());
                mMovieGenresAdapter = new GenresAdapter(getContext(),mMovieGenres);
                genreRecyclerView.setLayoutManager(mLayoutManager);
                genreRecyclerView.setAdapter(mMovieGenresAdapter);
                animation.stop();

            }
            @Override
            public void onFailure(Call<Genres> call, Throwable t) {

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab_genres, container, false);

        ImageView loading = (ImageView) view.findViewById(R.id.im_loading);
        animation= (AnimationDrawable)loading.getDrawable();
        animation.start();

        genreRecyclerView = view.findViewById(R.id.rv_genre_name);
        genreRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), genreRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Toast.makeText(getContext(), "buuu"+mSeriesGenres.getGenres().get(position).getId().toString(), Toast.LENGTH_SHORT).show();
                GenreDetailFragment genreDetailFragment = new GenreDetailFragment();
                Bundle data = new Bundle();
                if(SELECTED==SELECTED_MOVIE) {
                    data.putInt("genre_id", mMovieGenres.getGenres().get(position).getId());
                }else if (SELECTED==SELECTED_TV){
                    data.putInt("genre_id", mSeriesGenres.getGenres().get(position).getId());

                }
                data.putInt("selected",SELECTED);
                genreDetailFragment.setArguments(data);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.main_activity, genreDetailFragment,null);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

        return view;
    }

    @Override
    public void onMovieSelected() {
        SELECTED=SELECTED_MOVIE;
        genreRecyclerView.setAdapter(mMovieGenresAdapter);

    }

    @Override
    public void onSeriesSelected() {
        SELECTED=SELECTED_TV;

        animation.start();
        api.create(TmdbApi.class).listSeriesGenres(API_KEY).enqueue(new Callback<Genres>() {
            @Override
            public void onResponse(Call<Genres> call, Response<Genres> response) {
                mSeriesGenres = response.body();
                mLayoutManager = new LinearLayoutManager(getContext());
                mSeriesGenresAdapter = new GenresAdapter(getContext(),mSeriesGenres);
                genreRecyclerView.setLayoutManager(mLayoutManager);
                genreRecyclerView.setAdapter(mSeriesGenresAdapter);
                animation.stop();

            }
            @Override
            public void onFailure(Call<Genres> call, Throwable t) {

            }
        });

    }
}
