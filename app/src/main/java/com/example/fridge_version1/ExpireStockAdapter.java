package com.example.fridge_version1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class ExpireStockAdapter extends RecyclerView.Adapter<ExpireStockAdapter.ViewHolder>{

    ArrayList<Stock> items = new ArrayList<Stock>();

    Context context;
    int lastPosition = -1;

    @NonNull
    @Override
    public ExpireStockAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.expirestock_layout, viewGroup, false);

        context = viewGroup.getContext();

        return new ExpireStockAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpireStockAdapter.ViewHolder viewHolder, int position) {
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name_view = itemView.findViewById(R.id.expirestock);
        }

        public void setItem(Stock item) {
            name_view.setText(item.getName());
        }

    }
}
