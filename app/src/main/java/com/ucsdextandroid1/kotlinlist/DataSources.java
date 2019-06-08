package com.ucsdextandroid1.kotlinlist;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by rjaylward on 2019-06-08
 */
public class DataSources {

    private static DataSources instance;
    private ItunesApi itunesApi;

    private DataSources() {
        Gson gson = new GsonBuilder().create();
        this.itunesApi = new Retrofit.Builder()
                .baseUrl("https://itunes.apple.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(ItunesApi.class);
    }

    public static DataSources getInstance() {
        if(instance == null)
            instance = new DataSources();

        return instance;
    }

    public void search(String searchTerm, Callback<List<Models.SongItem>> callback) {
        Log.d("DataSources", "performing search for term " + searchTerm);
        itunesApi.searchItunes(searchTerm).enqueue(new retrofit2.Callback<Models.SearchResults>() {

            @Override
            public void onResponse(Call<Models.SearchResults> call, Response<Models.SearchResults> response) {
                if(response.isSuccessful()) {
                    callback.onDataFetched(response.body().getSongs());
                } else
                    callback.onDataFetched(Collections.emptyList());
            }

            @Override
            public void onFailure(Call<Models.SearchResults> call, Throwable t) {
                callback.onDataFetched(Collections.emptyList());
            }
        });

    }

    public interface Callback<T> {
        void onDataFetched(T data);
    }

    public interface ItunesApi {
        //TODO add a method that corresponds to the search method on the iTunesApi
        @GET("search?media=music&limit=25")
        Call<Models.SearchResults> searchItunes(@Query("term") String term);
    }
}
