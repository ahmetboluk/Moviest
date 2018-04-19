package com.example.ahmetboluk.moviest.Api;

import com.example.ahmetboluk.moviest.Data.PageData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TmdbApi {
    @GET("movie/popular")
    Call<PageData> listPopular(@Query("api_key") String apikey,@Query("page") int page);
    @GET("movie/top_rated")
    Call<PageData> listTopRated(@Query("api_key") String apikey,@Query("page") int page);
}
