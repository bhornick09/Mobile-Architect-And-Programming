package com.example.projecttwocs360brandonhornick;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class ActivitySettings extends AppCompatActivity {

    private static final int SMS_PERMISSION_REQUEST_CODE = 100;

    // fields for activating SMS notifications
    private EditText phoneField;
    private Button requestPermissionButton;
    private Button activateNotificationsButton;

    //nav bar buttons
    private Button weightJournalButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        phoneField = findViewById(R.id.phone_field);
        requestPermissionButton = findViewById(R.id.request_permission_button);
        activateNotificationsButton = findViewById(R.id.activate_notifications_button);
        weightJournalButton = findViewById(R.id.weight_page_button);

        // Check if the user has sms permissions enabled, disable activation button if not enabled
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                == PackageManager.PERMISSION_GRANTED) {
            activateNotificationsButton.setEnabled(false);
            requestPermissionButton.setEnabled(true);
        }

        // Request permissions when request button is clicked
        activateNotificationsButton.setOnClickListener(v -> {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.SEND_SMS},
                    SMS_PERMISSION_REQUEST_CODE
            );
        });

        // Functionality to turn on sms notifications
        requestPermissionButton.setOnClickListener(v -> {
            String phone = phoneField.getText().toString().trim();
            // if user didnt enter phone number
            if (phone.isEmpty()) {
                Toast.makeText(this, "Fill in phone number", Toast.LENGTH_SHORT).show();
                return;
            }
            // if successful
            Toast.makeText(this, "SMS Notifications Activated for: " + phone, Toast.LENGTH_SHORT).show();
        });

        // Bottom nav button to go back to weight page
        weightJournalButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, ActivityTracker.class);
            startActivity(intent);
            finish();
        });
    }

    // Used to handle permission results, disable or enable sms buttons according to permissions
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == SMS_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "SMS permission allowed", Toast.LENGTH_SHORT).show();
                activateNotificationsButton.setEnabled(false);
                requestPermissionButton.setEnabled(true);
            } else {
                Toast.makeText(this, "SMS permission failed", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
