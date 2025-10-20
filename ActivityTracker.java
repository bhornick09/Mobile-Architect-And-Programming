package com.example.projecttwocs360brandonhornick;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityTracker extends AppCompatActivity {
    private DBHelper dbHelper;
    private TableLayout tableLayout;
    private EditText etDate, etWeight, etNotes;
    private Button btnAddEntry, btnSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_journal);

        dbHelper = new DBHelper(this);
        tableLayout = findViewById(R.id.weight_table);

        etDate = findViewById(R.id.editTextDate);
        etWeight = findViewById(R.id.editTextWeight);
        etNotes = findViewById(R.id.editTextNotes);
        btnAddEntry = findViewById(R.id.add_entry_button);
        btnSettings = findViewById(R.id.settings_button);

        loadTableData();

        // add functionality to button for adding entry to table
        btnAddEntry.setOnClickListener(v -> {
            String date = etDate.getText().toString();
            String weight = etWeight.getText().toString();
            String notes = etNotes.getText().toString();

            // if fields are empty (except notes field)
            if (date.isEmpty() || weight.isEmpty()) {
                Toast.makeText(this, "Please fill in the date and weight fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // create boolean to check for successful entry to table/database
            boolean inserted = dbHelper.insertWeightEntry(date, weight, notes);
            if (inserted) {
                Toast.makeText(this, "Entry added!", Toast.LENGTH_SHORT).show();
                // reset ET boxes
                etDate.setText("");
                etWeight.setText("");
                etNotes.setText("");
                loadTableData(); // refresh display
            } else {
                Toast.makeText(this, "Entry failed", Toast.LENGTH_SHORT).show();
            }
        });

        // nav button to switch to settings page
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityTracker.this, ActivitySettings.class);
                startActivity(intent);
                finish();
            }
        });
    }

    // used to display the table for all entries in database
    private void loadTableData() {
        // clear current display
        int childCount = tableLayout.getChildCount();
        if (childCount > 1) {
            tableLayout.removeViews(1, childCount - 1);
        }

        Cursor cursor = dbHelper.getAllWeightEntries();
        if (cursor.getCount() == 0) {
            return;
        }

        // reload all entries to the table
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String date = cursor.getString(1);
            String weight = cursor.getString(2);
            String notes = cursor.getString(3);

            //start new row
            TableRow row = new TableRow(this);

            // add date text for current row
            TextView tvDate = new TextView(this);
            tvDate.setText(date);
            tvDate.setPadding(8, 8, 8, 8);
            row.addView(tvDate);

            // add weight text for current row
            TextView tvWeight = new TextView(this);
            tvWeight.setText(weight);
            tvWeight.setPadding(8, 8, 8, 8);
            row.addView(tvWeight);

            // add notes if needed for current row
            TextView tvNotes = new TextView(this);
            tvNotes.setText(notes);
            tvNotes.setPadding(8, 8, 8, 8);
            row.addView(tvNotes);

            // add delete button to each row
            Button btnDelete = new Button(this);
            btnDelete.setText("Delete");
            btnDelete.setOnClickListener(v -> {
                boolean deleted = dbHelper.deleteWeightEntry(id);
                if (deleted) {
                    Toast.makeText(this, "Entry deleted!", Toast.LENGTH_SHORT).show();
                    loadTableData();
                } else {
                    Toast.makeText(this, "Error deleting.", Toast.LENGTH_SHORT).show();
                }
            });
            row.addView(btnDelete);

            tableLayout.addView(row);
        }
        cursor.close();
    }
}
