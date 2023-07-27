package com.example.recycler;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/posts")
    Call<List<Posts>> getPosts();
}
