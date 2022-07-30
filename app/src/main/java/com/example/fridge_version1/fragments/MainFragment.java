package com.example.fridge_version1.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fridge_version1.MainActivity;
import com.example.fridge_version1.R;
import com.example.fridge_version1.SimpleTextAdapter;
import com.example.fridge_version1.databinding.FragmentMainBinding;


import java.util.ArrayList;

public class MainFragment extends Fragment {

    FragmentMainBinding fragmentMainBinding;

    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentMainBinding = FragmentMainBinding.inflate(inflater, container, false);

        view = fragmentMainBinding.getRoot();

        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < 100; i++) {
            list.add(String.format("TEXT %d", i));
        }

        TextView textView = view.findViewById(R.id.title_text);
        textView.setText("홈");

        fragmentMainBinding.recycler1.setLayoutManager(new LinearLayoutManager(((MainActivity)getActivity())));
        SimpleTextAdapter adapter = new SimpleTextAdapter(list);
        fragmentMainBinding.recycler1.setAdapter(adapter);

        return view;
    }

}