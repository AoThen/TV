package com.fongmi.android.tv.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fongmi.android.tv.api.ApiConfig;
import com.fongmi.android.tv.bean.Site;
import com.fongmi.android.tv.databinding.AdapterSiteBinding;

import java.util.List;

public class SiteAdapter extends RecyclerView.Adapter<SiteAdapter.ViewHolder> {

    private final OnClickListener mListener;
    private final List<Site> mItems;

    public SiteAdapter(OnClickListener listener) {
        this.mListener = listener;
        this.mItems = ApiConfig.get().getSites();
    }

    public interface OnClickListener {

        void onTextClick(Site item);

        void onSearchClick(Site item);

        void onFilterClick(Site item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final AdapterSiteBinding binding;

        ViewHolder(@NonNull AdapterSiteBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(AdapterSiteBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Site item = mItems.get(position);
        holder.binding.text.setText(item.getActivatedName());
        holder.binding.filter.setImageResource(item.getFilterIcon());
        holder.binding.search.setImageResource(item.getSearchIcon());
        holder.binding.text.setOnClickListener(v -> mListener.onTextClick(item));
        holder.binding.search.setOnClickListener(v -> mListener.onSearchClick(item));
        holder.binding.filter.setOnClickListener(v -> mListener.onFilterClick(item));
    }
}
