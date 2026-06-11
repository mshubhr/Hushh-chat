package com.project.hushh.view;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.hushh.databinding.SearchResultItemBinding;
import com.project.hushh.model.SearchResult;

import java.util.List;

public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter.SearchResultViewHolder> {

    private List<SearchResult> searchResults;

    @SuppressLint("NotifyDataSetChanged")
    public void setSearchResults(List<SearchResult> results) {
        this.searchResults = results;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchResultViewHolder(SearchResultItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultViewHolder holder, int position) {
        holder.binding.titleTextView.setText(searchResults.get(position).getTitle());
        holder.binding.linkTextView.setText(searchResults.get(position).getLink());
    }

    @Override
    public int getItemCount() {
        return searchResults != null ? searchResults.size() : 0;
    }

    public static class SearchResultViewHolder extends RecyclerView.ViewHolder {
        private final SearchResultItemBinding binding;

        public SearchResultViewHolder(@NonNull SearchResultItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}