package com.example.ahmetboluk.moviest.MyFragment.mainTabItem;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ahmetboluk.moviest.Api.TmdbApi;
import com.example.ahmetboluk.moviest.Data.Genres;
import com.example.ahmetboluk.moviest.MyFragment.GenreDetailFragment;
import com.example.ahmetboluk.moviest.R;
import com.example.ahmetboluk.moviest.RecyclerItemClickListener;
import com.example.ahmetboluk.moviest.adapter.GenresAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TabGenres extends Fragment {

    public RecyclerView genreRecyclerView;
    public Genres mGenres;
    private GenresAdapter mGenresAdapter;
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
                mGenres = response.body();
                mLayoutManager = new LinearLayoutManager(getContext());
                mGenresAdapter = new GenresAdapter(getContext(),mGenres);
                genreRecyclerView.setLayoutManager(mLayoutManager);
                genreRecyclerView.setAdapter(mGenresAdapter);

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
        genreRecyclerView = view.findViewById(R.id.rv_genre_name);
        genreRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), genreRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                GenreDetailFragment genreDetailFragment = new GenreDetailFragment();
                Bundle data = new Bundle();
                data.putInt("genre_id",mGenres.getGenres().get(position).getId());
                genreDetailFragment.setArguments(data);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_activity, genreDetailFragment,null);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

        return view;
    }

}
