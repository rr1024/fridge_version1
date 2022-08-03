package com.example.fridge_version1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StockAdapter extends RecyclerView.Adapter<StockAdapter.ViewHolder>{

    ArrayList<Stock> items = new ArrayList<Stock>();

    Context context;

    int lastPosition = -1;

    @NonNull
    @Override
    public StockAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.stock_layout, viewGroup, false);

        context = viewGroup.getContext();

        return new StockAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StockAdapter.ViewHolder viewHolder, int position) {
        Stock item = items.get(position);
        viewHolder.setItem(item);
    }

    public void addItem(Stock item) {
        items.add(item);
    }

    public void addItem(int position, Stock item) {
        items.add(position, item);
    }

    public void removeAllItem() {
        items.clear();
    }

    public void removeItem(int position) {
        items.remove(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name_view;
        TextView dateText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name_view = itemView.findViewById(R.id.stockNameText);
            dateText = itemView.findViewById(R.id.dateText);

        }

        public void setItem(Stock item) {
            name_view.setText(item.getName());
            dateText.setText(item.getDate());
        }
    }
}
