package com.example.fridge_version1.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fridge_version1.ExpireStockAdapter;
import com.example.fridge_version1.R;
import com.example.fridge_version1.Stock;
import com.example.fridge_version1.StockAdapter;
import com.example.fridge_version1.databinding.ActivityStockBinding;
import com.example.fridge_version1.databinding.FragmentMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;


public class MainFragment extends Fragment {

    FragmentMainBinding fragmentMainBinding;
    private ExpireStockAdapter stockAdapter;
    private ArrayList<Stock> stocks = new ArrayList<Stock>();
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://fridge-management-app-3f538-default-rtdb.firebaseio.com/");
    DatabaseReference databaseReference = database.getReference("Stock");
    private View view;
    private final int ONE_DAY = 24 * 60 * 60 * 1000;
    private int num;

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

        fragmentMainBinding.expireStockRecycler.setHasFixedSize(true);
        fragmentMainBinding.expireStockRecycler.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
        stockAdapter = new ExpireStockAdapter();
        fragmentMainBinding.expireStockRecycler.setAdapter(stockAdapter);


        for(num = 0; num < 5; num++) {
            databaseReference.child(Integer.toString(num)).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Stock stock = dataSnapshot.getValue(Stock.class);
                        int dday = getDday(stock.getDday());
                        if (dday < 4) {
                            stocks.add(stock);
                            stockAdapter.addItem(stock);
                        }
                    }
                    stockAdapter.notifyDataSetChanged();
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }


        return view;
    }

    private int getDday(long dDay) {

        dDay = dDay / ONE_DAY;
        final long today = System.currentTimeMillis() / ONE_DAY;
        long result = dDay - today;

        return (int) result;
    }

}