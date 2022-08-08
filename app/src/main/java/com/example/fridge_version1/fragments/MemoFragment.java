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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class MemoFragment extends Fragment {

    private FragmentMemoBinding fragmentMemoBinding;
    private MemoAdapter memoAdapter;
    private View view;
    private ArrayList<Memo> memos = new ArrayList<Memo>();
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://fridge-management-app-3f538-default-rtdb.firebaseio.com/");
    DatabaseReference databaseReference = database.getReference("memo");

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

        /*
         Firebase database에서 memo 가져오는 부분
         adapter에 memo 아이템 추가해서 뷰 초기화
         */
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                memos.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    Memo initMemo = snapshot1.getValue(Memo.class);
                    memos.add(initMemo);
                    memoAdapter.addItem(initMemo);
                }
                memoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // 메모 삭제하는 부분 구현
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            // 왼쪽으로 슬라이싱하여 memo 삭제 기능 구현
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position =  viewHolder.getAdapterPosition();

                switch (direction) {
                    case ItemTouchHelper.LEFT:
                        Memo deleteMemo = memos.get(position);

                        memos.remove(position);
                        memoAdapter.removeItem(position);
                        memoAdapter.notifyDataSetChanged();
                        databaseReference.child(deleteMemo.getTitle()).removeValue();

                        // snackbar 클릭시 삭제한 메모 복구
                        Snackbar.make(fragmentMemoBinding.memoRecycler, deleteMemo.getTitle(), Snackbar.LENGTH_LONG)
                                .setAction("복구", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        memos.add(position, deleteMemo);
                                        memoAdapter.addItem(position, deleteMemo);
                                        memoAdapter.notifyItemInserted(position);
                                        databaseReference.child(deleteMemo.getTitle()).setValue(deleteMemo);
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

        // memo 추가하는 floatingActionButton
        fragmentMemoBinding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentMemoBinding.cardView.setVisibility(View.VISIBLE);
                fragmentMemoBinding.floatingActionButton.setVisibility(View.INVISIBLE);
            }
        });

        // cardView에 메모 작성 후 어댑터와 데이터베이스에 메모 추가 구현
        fragmentMemoBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Memo addMemo = new Memo();
                addMemo.setTitle(fragmentMemoBinding.editText.getText().toString());

                memos.add(addMemo);
                memoAdapter.addItem(addMemo);
                databaseReference.child(addMemo.getTitle()).setValue(addMemo);
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