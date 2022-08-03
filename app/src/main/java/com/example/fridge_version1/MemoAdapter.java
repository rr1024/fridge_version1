package com.example.fridge_version1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.ViewHolder> {

    ArrayList<Memo> items = new ArrayList<Memo>();

    Context context;

    int lastPosition = -1;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.memo_layout, viewGroup, false);

        context = viewGroup.getContext();

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Memo item = items.get(position);
        viewHolder.setItem(item);
    }

    public void addItem(Memo item) {
        items.add(item);
    }

    public void addItem(int position, Memo item) {
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
        TextView title_view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title_view = itemView.findViewById(R.id.title_text);
        }

        public void setItem(Memo item) {
            title_view.setText(item.getTitle());
        }
    }
}
