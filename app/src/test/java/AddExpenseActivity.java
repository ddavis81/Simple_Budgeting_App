package com.example.simplebudget;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    TextView welcomeText;
    Button addExpenseBtn, searchBtn;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        welcomeText = findViewById(R.id.welcomeText);
        addExpenseBtn = findViewById(R.id.addExpenseBtn);
        searchBtn = findViewById(R.id.searchBtn);

        username = getIntent().getStringExtra("username");
        welcomeText.setText("Welcome, " + username);

        addExpenseBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), AddExpenseActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        });

        searchBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        });
    }
}

