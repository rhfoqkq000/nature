package com.example.kim.qazz.RetrofitSuccess;

import com.example.kim.qazz.RetrofitHelpHouse.Repo;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by user on 2016-09-08.
 */
public interface Success {
//    http://45.32.61.201:3000/nature/article
    @GET("article")
    Call<com.example.kim.qazz.RetrofitSuccess.Repo> repo();
}