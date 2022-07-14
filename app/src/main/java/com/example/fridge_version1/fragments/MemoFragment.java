package com.example.fridge_version1.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.fridge_version1.MainActivity;
import com.example.fridge_version1.MainActivity2;
import com.example.fridge_version1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MemoFragment extends Fragment {

    private View view;
    private String TAG = "Memo";
    ActionBar actionBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        view = inflater.inflate(R.layout.fragment_memo, container, false);
        FloatingActionButton btn = (FloatingActionButton) view.findViewById(R.id.btn1);
        Button btn2 = (Button) view.findViewById(R.id.btn2);

        actionBar = ((MainActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("장보기 메모");
        actionBar.setDisplayHomeAsUpEnabled(false);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent((MainActivity)getActivity(), MainActivity2.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(((MainActivity)getActivity()), MainActivity2.class);
                startActivity(intent);
            }
        });

        return view;
    }
}