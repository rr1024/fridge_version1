package com.example.fridge_version1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fridge_version1.databinding.ActivityStockBinding;
import com.example.fridge_version1.databinding.FragmentMemoBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class StockActivity extends AppCompatActivity {

    private String TAG = "Fridge_Management";
    private ActivityStockBinding stockBinding;
    private StockAdapter stockAdapter;
    private ArrayList<Stock> stocks = new ArrayList<Stock>();
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://fridge-management-app-3f538-default-rtdb.firebaseio.com/");
    DatabaseReference databaseReference = database.getReference("Stock");
    Toolbar toolbar;
    Dialog dialog;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        stockBinding = ActivityStockBinding.inflate(getLayoutInflater());
        View view = stockBinding.getRoot();
        setContentView(view);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dialog = new Dialog(StockActivity.this);
        dialog.setContentView(R.layout.dialog_stock);

        TextView title = findViewById(R.id.title_text);
        title.setText(getIntent().getStringExtra("id"));

        stockBinding.stockRecycler.setHasFixedSize(true);
        stockBinding.stockRecycler.setLayoutManager(new LinearLayoutManager(this));
        stockAdapter = new StockAdapter();
        stockBinding.stockRecycler.setAdapter(stockAdapter);

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CharSequence[] items = {"냉동실 관리", "냉장실 관리", "김냉(왼편) 관리", "김냉(오른쪽) 관리", "실온 제품"};
                AlertDialog.Builder d = new AlertDialog.Builder(StockActivity.this);

                d.setTitle("냉장고 선택").setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        id = i+1;
                        title.setText(items[i]);
                    }
                });
                d.show();
            }
        });
        stockBinding.modeCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CharSequence[] items = {"유통기한 임박순", "등록순"};
                AlertDialog.Builder d = new AlertDialog.Builder(StockActivity.this);

                d.setTitle("정렬 방식").setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        stockBinding.textView21.setText(items[i]);
                    }
                });
                d.show();
            }
        });
        stockBinding.addStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();

                dialog.setTitle("재료 추가");
                Button canBtn = dialog.findViewById(R.id.CanBtn);
                Button okBtn = dialog.findViewById(R.id.OkBtn);
                EditText nameEditText = dialog.findViewById(R.id.editName);

                canBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                okBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Stock addStock = new Stock();
                        addStock.setId(id);
                        addStock.setName(nameEditText.toString());
                        stocks.add(addStock);
                        stockAdapter.addItem(addStock);
                        stockAdapter.notifyDataSetChanged();;
                        finish();
                    }
                });
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}