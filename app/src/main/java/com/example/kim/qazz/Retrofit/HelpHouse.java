package com.example.kim.qazz.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by user on 2016-09-08.
 */
public interface HelpHouse {
//    http://45.32.61.201:3000/nature/help_house?sido=강원도&sigun=양양군
    @GET("help_house")
    Call<Repo> repo(@Query("sido") String sido, @Query("sigun") String sigun);
}