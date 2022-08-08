package com.example.fridge_version1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class StockAdapter extends RecyclerView.Adapter<StockAdapter.ViewHolder>{

    ArrayList<Stock> items = new ArrayList<Stock>();

    Context context;
    int lastPosition = -1;

    @NonNull
    @Override
    public StockAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.stock_layout, viewGroup, false);

        context = viewGroup.getContext();

        return new StockAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StockAdapter.ViewHolder viewHolder, int position) {
        Stock item = items.get(position);
        viewHolder.setItem(item);
    }

    public void addItem(Stock item) {
        items.add(item);
    }

    public void addItem(int position, Stock item) {
        items.add(position, item);
    }

    public void removeAllItem() {
        items.clear();
    }

    public void removeItem(int position) {
        items.remove(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name_view;
        TextView dDayText;
        CircleImageView dDayImage;

        private final int ONE_DAY = 24 * 60 * 60 * 1000;
        int color;

        // 디데이 표현하기 위한 함수
        private String getDday(int mYear, int mMonth, int mDay) {
            final Calendar dDayCalendar = Calendar.getInstance();
            dDayCalendar.set(mYear, mMonth, mDay);

            final long dday = dDayCalendar.getTimeInMillis() / ONE_DAY;
            final long today = Calendar.getInstance().getTimeInMillis() / ONE_DAY;
            long result = dday - today;

            color = (int) result;
            String strFormat;
            if (result > 0) {
                strFormat = "D-%d";
            } else if (result == 0) {
                strFormat = "Today";
            }
            else {
                result *= -1;
                strFormat = "D+%d";
            }
            final String strCount = (String.format(strFormat, result));

            return strCount;
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name_view = itemView.findViewById(R.id.stockNameText);
            dDayText = itemView.findViewById(R.id.dday_Text);
            dDayImage = itemView.findViewById(R.id.expireImage);
        }

        public void setItem(Stock item) {
            name_view.setText(item.getName());
            dDayText.setText(getDday(item.getYear(), item.getMonth(), item.getDay()));
            if (color > 3) {
                dDayImage.setImageResource(R.drawable.green);
            }
            else if (color <= 3 && color > -1) {
                dDayImage.setImageResource(R.drawable.red);
            }
            else {
                dDayImage.setImageResource(R.drawable.black);
            }
        }

    }

}
