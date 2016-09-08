package com.example.kim.qazz.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by user on 2016-09-08.
 */
public interface HelpHouse {
    @GET()
    Call<Repo> repo();
}
