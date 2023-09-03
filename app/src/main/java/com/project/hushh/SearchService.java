package com.project.hushh;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchService {

    @GET("https://cse.google.com/cse?cx=a4d09d5e6548f4c34")
    Call<SearchResponse> search(@Query("q") String query, @Query("apiKey") String apiKey);
}
