package com.example.fridge_version1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.fridge_version1.databinding.ActivityStockBinding;


public class StockActivity extends AppCompatActivity {

    private String TAG = "Fridge_Management";
    private ActivityStockBinding stockBinding;

    Toolbar toolbar;

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

        TextView title = findViewById(R.id.title_text);
        title.setText(getIntent().getStringExtra("id"));

        stockBinding.number.setText(getIntent().getStringExtra("num"));

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CharSequence[] items = {"냉동실 관리", "냉장실 관리", "김냉(왼편) 관리", "김냉(오른쪽) 관리", "실온 제품"};
                AlertDialog.Builder d = new AlertDialog.Builder(StockActivity.this);

                d.setTitle("냉장고 선택").setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int num = i+1;
                        title.setText(items[i]);
                        stockBinding.number.setText(num+"");
                    }
                });

                d.show();
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