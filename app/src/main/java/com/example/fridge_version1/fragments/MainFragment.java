package com.example.fridge_version1.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fridge_version1.MainActivity;
import com.example.fridge_version1.R;
import com.example.fridge_version1.SimpleTextAdapter;


import java.util.ArrayList;

public class MainFragment extends Fragment {

    private View view;
    private String TAG = "Home";
    ActionBar actionBar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        view = inflater.inflate(R.layout.fragment_main, container, false);

        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < 100; i++) {
            list.add(String.format("TEXT %d", i));
        }

        RecyclerView recyclerView = view.findViewById(R.id.recycler1);
        recyclerView.setLayoutManager(new LinearLayoutManager(((MainActivity)getActivity())));

        SimpleTextAdapter adapter = new SimpleTextAdapter(list);
        recyclerView.setAdapter(adapter);

        actionBar = ((MainActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("홈");
        actionBar.setDisplayHomeAsUpEnabled(false);
        return view;
    }

}