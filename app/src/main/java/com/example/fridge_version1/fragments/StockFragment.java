package com.example.fridge_version1.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fridge_version1.MainActivity;
import com.example.fridge_version1.StockActivity;
import com.example.fridge_version1.R;
import com.example.fridge_version1.databinding.FragmentStockBinding;

public class StockFragment extends Fragment {

    private FragmentStockBinding fragmentStockBinding;

    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentStockBinding = FragmentStockBinding.inflate(inflater, container, false);

        view = fragmentStockBinding.getRoot();

        fragmentStockBinding.cardviewFrozen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(((MainActivity)getActivity()), StockActivity.class);
                intent.putExtra("id", "냉동실 관리");
                intent.putExtra("num", "1");
                startActivity(intent);
            }
        });

        fragmentStockBinding.cardviewFridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(((MainActivity)getActivity()), StockActivity.class);
                intent.putExtra("id", "냉장실 관리");
                intent.putExtra("num", "2");
                startActivity(intent);
            }
        });

        fragmentStockBinding.cardviewKimchi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(((MainActivity)getActivity()), StockActivity.class);
                intent.putExtra("id", "김냉(왼쪽) 관리");
                intent.putExtra("num", "3");
                startActivity(intent);
            }
        });

        fragmentStockBinding.cardviewKimchi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(((MainActivity)getActivity()), StockActivity.class);
                intent.putExtra("id", "김냉(오른쪽) 관리");
                intent.putExtra("num", "4");
                startActivity(intent);
            }
        });

        fragmentStockBinding.cardviewRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(((MainActivity)getActivity()), StockActivity.class);
                intent.putExtra("id", "실온 제품");
                intent.putExtra("num", "5");
                startActivity(intent);
            }
        });

        TextView textView = view.findViewById(R.id.title_text);
        textView.setText("냉장고 관리");

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}