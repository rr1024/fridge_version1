package com.example.fridge_version1.fragments;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fridge_version1.MainActivity;
import com.example.fridge_version1.Memo;
import com.example.fridge_version1.MemoAdapter;
import com.example.fridge_version1.R;
import com.example.fridge_version1.databinding.FragmentMemoBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class MemoFragment extends Fragment {

    private FragmentMemoBinding fragmentMemoBinding;
    private MemoAdapter memoAdapter;
    private View view;
    private ArrayList<Memo> memos = new ArrayList<Memo>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        fragmentMemoBinding = FragmentMemoBinding.inflate(inflater, container, false);
        view = fragmentMemoBinding.getRoot();

        fragmentMemoBinding.memoRecycler.setHasFixedSize(true);
        fragmentMemoBinding.memoRecycler.setLayoutManager(new LinearLayoutManager(((MainActivity)getActivity())));
        memoAdapter = new MemoAdapter();
        fragmentMemoBinding.memoRecycler.setAdapter(memoAdapter);
        fragmentMemoBinding.cardView.setVisibility(View.INVISIBLE);

        TextView textView = view.findViewById(R.id.title_text);
        textView.setText("메모");


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position =  viewHolder.getAdapterPosition();

                switch (direction) {
                    case ItemTouchHelper.LEFT:
                        Memo deleteMemo = memos.get(position);

                        memos.remove(position);
                        memoAdapter.removeItem(position);
                        memoAdapter.notifyDataSetChanged();

                        Snackbar.make(fragmentMemoBinding.memoRecycler, deleteMemo.getTitle(), Snackbar.LENGTH_LONG)
                                .setAction("복구", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        memos.add(position, deleteMemo);
                                        memoAdapter.addItem(position, deleteMemo);
                                        memoAdapter.notifyItemInserted(position);
                                    }
                                }).show();
                        break;
                }
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder,
                        dX, dY, actionState, isCurrentlyActive)
                        .addSwipeLeftBackgroundColor(Color.RED)
                        .addSwipeLeftActionIcon(R.drawable.ic_delete)
                        .addSwipeLeftLabel("Delete")
                        .setSwipeLeftLabelColor(Color.WHITE)
                        .create()
                        .decorate();
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        }).attachToRecyclerView(fragmentMemoBinding.memoRecycler);

        fragmentMemoBinding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentMemoBinding.cardView.setVisibility(View.VISIBLE);
                fragmentMemoBinding.floatingActionButton.setVisibility(View.INVISIBLE);
            }
        });

        fragmentMemoBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Memo addMemo = new Memo();
                addMemo.setTitle(fragmentMemoBinding.editText.getText().toString());

                memos.add(addMemo);
                memoAdapter.addItem(addMemo);
                databaseReference.child("memo").setValue(addMemo);
                fragmentMemoBinding.editText.setText("");
                fragmentMemoBinding.cardView.setVisibility(View.INVISIBLE);
                fragmentMemoBinding.floatingActionButton.setVisibility(View.VISIBLE);

                memoAdapter.notifyDataSetChanged();

                fragmentMemoBinding.memoRecycler.startLayoutAnimation();
            }
        });
        return view;
    }
}