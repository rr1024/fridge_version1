package com.example.fridge_version1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SimpleTextAdapter extends RecyclerView.Adapter<SimpleTextAdapter.ViewHolder> {
    private ArrayList<String> mData = null;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1;

        ViewHolder(View itemView) {
            super(itemView);

            textView1 = itemView.findViewById(R.id.text1);
        }
    }

    public SimpleTextAdapter(ArrayList<String> list) {
        mData = list;
    }

    @Override
    public SimpleTextAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.recyclerview_item, parent, false);
        SimpleTextAdapter.ViewHolder vh = new SimpleTextAdapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(SimpleTextAdapter.ViewHolder holder, int poisition) {
        String text = mData.get(poisition);
        holder.textView1.setText(text);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
