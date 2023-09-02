package com.project.hushh;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText searchEditText;
    private Button searchButton;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchEditText = findViewById(R.id.searchEditText);
        searchButton = findViewById(R.id.searchButton);
//        webView = findViewById(R.id.webView);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = searchEditText.getText().toString();
                if (!query.isEmpty()) {
                    // Initialize Retrofit
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://www.googleapis.com")
//                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    // Create a Retrofit service instance
                    GoogleCustomSearchService searchService = retrofit.create(GoogleCustomSearchService.class);

                    // Make the search request
                    Call<SearchResponse> call = searchService.search(query, "https://cse.google.com/cse?cx=a4d09d5e6548f4c34");

                    // Execute the request asynchronously
                    call.enqueue(new Callback<SearchResponse>() {
                        @Override
                        public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                            if (response.isSuccessful()) {
                                // Handle the successful response here
                                SearchResponse searchResponse = response.body();
                                List<SearchResult> results = searchResponse.getItems();

                                // Process the search results as needed
                                // For example, you can display them in a RecyclerView
                            } else {
                                // Handle the error response here
                            }
                        }

                        @Override
                        public void onFailure(Call<SearchResponse> call, Throwable t) {
                            // Handle the network request failure here
                        }
                    });
                }
            }
        });
    }
}
