package com.project.hushh;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleCustomSearchService {
    @GET("customsearch/v1")
    Call<SearchResponse> search(@Query("q") String query, @Query("key") String apiKey);
}
