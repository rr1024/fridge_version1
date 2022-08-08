package com.example.fridge_version1.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fridge_version1.R;
import com.example.fridge_version1.databinding.FragmentMainBinding;


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


        TextView textView = view.findViewById(R.id.title_text);
        textView.setText("홈");


        return view;
    }

}