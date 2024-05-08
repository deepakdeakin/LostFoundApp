package com.jk.apps.foundandlost;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.jk.apps.foundandlost.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        onClick();
    }

    public void onClick() {
        binding.btnNewAdvert.setOnClickListener(v -> {
            startActivity(new Intent(this, NewPostActivity.class));
        });
        binding.btnShowAll.setOnClickListener(v -> {
            startActivity(new Intent(this, AllPostActivity.class));
        });
    }
}