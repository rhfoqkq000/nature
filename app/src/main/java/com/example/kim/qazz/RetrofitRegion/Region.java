package com.example.kim.qazz.RetrofitRegion;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ryu on 2016-09-09.
 */
public interface Region {
    //http://45.32.61.201:3000/nature/maeul?address=%EA%B0%95%EB%A6%89%EC%8B%9C
    @GET("maeul")
    Call<Repo> repo(@Query("address") String address);
}
