package com.example.fridge_version1.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fridge_version1.MainActivity;
import com.example.fridge_version1.MainActivity3;
import com.example.fridge_version1.R;

public class StockFragment extends Fragment {

    private View view;
    private String TAG = "Stock";
    ActionBar actionBar;
    CardView card_fro, card_fri, card_kim1, card_kim2, card_room;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        view = inflater.inflate(R.layout.fragment_stock, container, false);

        card_fro = (CardView) view.findViewById(R.id.cardview_frozen);
        card_fri = (CardView) view.findViewById(R.id.cardview_fridge);
        card_kim1 = (CardView) view.findViewById(R.id.cardview_kimchi1);
        card_kim2 = (CardView) view.findViewById(R.id.cardview_kimchi2);
        card_room = (CardView) view.findViewById(R.id.cardview_room);

        actionBar =  ((MainActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("냉장고 재고 관리");
        actionBar.setDisplayHomeAsUpEnabled(false);

        card_fro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(((MainActivity)getActivity()),MainActivity3.class);
                intent.putExtra("id", "냉동실 관리");
                startActivity(intent);
            }
        });

        card_fri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(((MainActivity)getActivity()),MainActivity3.class);
                intent.putExtra("id", "냉장고 관리");
                startActivity(intent);
            }
        });

        card_kim1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(((MainActivity)getActivity()),MainActivity3.class);
                intent.putExtra("id", "김치냉장고 (왼편)");
                startActivity(intent);
            }
        });

        card_kim2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(((MainActivity)getActivity()),MainActivity3.class);
                intent.putExtra("id", "김치냉장고 (오른편)");
                startActivity(intent);
            }
        });

        card_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(((MainActivity)getActivity()),MainActivity3.class);
                intent.putExtra("id", "실온보관 제품");
                startActivity(intent);
            }
        });

        return view;
    }
}