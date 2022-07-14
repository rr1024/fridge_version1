package com.example.fridge_version1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.fridge_version1.FridgeManagement.FridgeFragment;
import com.example.fridge_version1.FridgeManagement.FrozenFragment;
import com.example.fridge_version1.FridgeManagement.Kimchi1Fragment;
import com.example.fridge_version1.FridgeManagement.Kimchi2Fragment;
import com.example.fridge_version1.FridgeManagement.RoomFragment;

public class MainActivity3 extends AppCompatActivity {

    private String TAG = "Fridge_Management";

    Fragment fragment_fridge;
    Fragment fragment_frozen;
    Fragment fragment_kimche1;
    Fragment fragment_kimche2;
    Fragment fragment_room;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        fragment_frozen = new FrozenFragment();
        fragment_fridge = new FridgeFragment();
        fragment_kimche1 = new Kimchi1Fragment();
        fragment_kimche2 = new Kimchi2Fragment();
        fragment_room = new RoomFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.manage_layout, fragment_frozen).commitAllowingStateLoss();

        actionBar = this.getSupportActionBar();
        actionBar.setTitle(getIntent().getStringExtra("id"));
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}