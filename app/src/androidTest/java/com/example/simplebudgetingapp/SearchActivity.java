package com.example.simplebudgetingapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    EditText searchInput;
    Button searchBtn;
    ListView resultsList;
    DBHelper db;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchInput = findViewById(R.id.searchInput);
        searchBtn = findViewById(R.id.searchBtn);
        resultsList = findViewById(R.id.resultsList);

        db = new DBHelper(this);
        username = getIntent().getStringExtra("username");

        searchBtn.setOnClickListener(v -> {
            String query = searchInput.getText().toString();
            SQLiteDatabase database = db.getReadableDatabase();

            Cursor cursor = database.rawQuery(
                    "SELECT amount, category, date, notes FROM transactions WHERE username=? AND (category LIKE ? OR notes LIKE ?)",
                    new String[]{username, "%" + query + "%", "%" + query + "%"}
            );

            ArrayList<String> results = new ArrayList<>();
            while (cursor.moveToNext()) {
                results.add(
                        "Amount: $" + cursor.getDouble(0) +
                                "\nCategory: " + cursor.getString(1) +
                                "\nDate: " + cursor.getString(2) +
                                "\nNotes: " + cursor.getString(3)
                );
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, results);
            resultsList.setAdapter(adapter);
        });
    }
}
