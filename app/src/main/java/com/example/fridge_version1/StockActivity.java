package com.example.fridge_version1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fridge_version1.databinding.ActivityStockBinding;
import com.example.fridge_version1.databinding.FragmentMemoBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;


public class StockActivity extends AppCompatActivity {

    private String TAG = "Fridge_Management";
    private ActivityStockBinding stockBinding;
    private StockAdapter stockAdapter;
    private ArrayList<Stock> stocks = new ArrayList<Stock>();
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://fridge-management-app-3f538-default-rtdb.firebaseio.com/");
    DatabaseReference databaseReference = database.getReference("Stock");
    Toolbar toolbar;
    Dialog dialog;
    DatePickerDialog datePickerDialog;
    int id;
    String num;

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

        num = getIntent().getStringExtra("num");

        databaseReference.child(num).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                stocks.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Stock initStock = dataSnapshot.getValue(Stock.class);
                    stocks.add(initStock);
                    stockAdapter.addItem(initStock);
                }
                stockAdapter.notifyDataSetChanged();
                stockBinding.stockCount.setText(String.format("%d", stocks.size()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        stockBinding.stockRecycler.setHasFixedSize(true);
        stockBinding.stockRecycler.setLayoutManager(new GridLayoutManager(this, 5));
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
                        id = i;
                        title.setText(items[i]);
                        stockAdapter.removeAllItem();

                        databaseReference.child(Integer.toString(id)).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                stocks.clear();
                                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                    Stock initStock = dataSnapshot.getValue(Stock.class);
                                    stocks.add(initStock);
                                    stockAdapter.addItem(initStock);
                                }
                                stockAdapter.notifyDataSetChanged();
                                stockBinding.stockCount.setText(String.format("%d", stocks.size()));
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
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
                TextView textView = dialog.findViewById(R.id.deadlineText1);

                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar calendar = Calendar.getInstance();
                        int year = calendar.get(Calendar.YEAR);
                        int month = calendar.get(Calendar.MONTH);
                        int day = calendar.get(Calendar.DAY_OF_MONTH);

                        datePickerDialog = new DatePickerDialog(StockActivity.this, new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                        i1 = i1 + 1;
                                        String date = i + "/" + i1 + "/" + i2;

                                        textView.setText(date);
                                    }
                                }, year, month, day);
                        datePickerDialog.show();
                    }
                });

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
                        addStock.setName(String.format("%s", nameEditText.getText()));
                        addStock.setDate(String.format("%s", textView.getText()));
                        stocks.add(addStock);
                        stockAdapter.addItem(addStock);
                        stockAdapter.notifyDataSetChanged();
                        nameEditText.setText("");
                        databaseReference.child(Integer.toString(id)).child(addStock.getName()).setValue(addStock);
                        stockBinding.stockCount.setText(String.format("%d", stocks.size()));
                        dialog.dismiss();
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