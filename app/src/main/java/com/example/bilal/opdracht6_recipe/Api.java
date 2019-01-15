package com.example.bilal.opdracht6_recipe;


import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;



public interface Api {
    String BASE_URL = "https://www.food2fork.com/";


    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    @GET("/api/search?key=74159d9db7f065fe915776084a7b0c57&sort=r&count=3")
    Call<ListRecipe> getdata();

}

