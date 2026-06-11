package com.project.hushh.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.project.hushh.SearchService;
import com.project.hushh.databinding.ActivityMainBinding;
import com.project.hushh.model.SearchResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private SearchResultsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initial();
        clickListeners();
    }

    private void initial() {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SearchResultsAdapter();
        binding.recyclerView.setAdapter(adapter);
    }

    private void clickListeners() {
        binding.searchButton.setOnClickListener(v -> {
            if (binding.searchEditText.getText() != null) {
                getSearch(binding.searchEditText.getText().toString());
            } else {
                Toast.makeText(MainActivity.this, "Please enter a search query", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getSearch(String query) {
        new Retrofit.Builder().baseUrl("https://www.googleapis.com/").addConverterFactory(GsonConverterFactory.create()).build().create(SearchService.class).search(query, "https://cse.google.com/cse?cx=a4d09d5e6548f4c34").enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(@NonNull Call<SearchResponse> call, @NonNull Response<SearchResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter.setSearchResults(response.body().getItems());
                } else {
                    Toast.makeText(MainActivity.this, "Search failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<SearchResponse> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}