package com.project.hushh;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText searchEditText;
    private SearchResultsAdapter adapter;
    private SearchService searchService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchEditText = findViewById(R.id.searchEditText);
        Button searchButton = findViewById(R.id.searchButton);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        // Define the base URL of your API
        String baseUrl = "https://www.googleapis.com/"; // Replace with your APIs base URL

// Create a Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create()) // Use Gson for JSON parsing
                .build();

        searchService = retrofit.create(SearchService.class);

        // Initialize the RecyclerView and adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SearchResultsAdapter();
        recyclerView.setAdapter(adapter);

        searchButton.setOnClickListener(v -> {
            String query = searchEditText.getText().toString();
            if (!query.isEmpty()) {
                // Call the search method in your SearchService
                Call<SearchResponse> call = searchService.search(query, "https://cse.google.com/cse?cx=a4d09d5e6548f4c34");
                call.enqueue(new Callback<SearchResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<SearchResponse> call, @NonNull Response<SearchResponse> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            List<SearchResult> results = response.body().getItems();
                            adapter.setSearchResults(results);
                        } else {
                            Toast.makeText(MainActivity.this, "Search failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<SearchResponse> call, @NonNull Throwable t) {
                        Toast.makeText(MainActivity.this, "Network error", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(MainActivity.this, "Please enter a search query", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
