package com.example.travelshare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Setting extends AppCompatActivity {
    TextView name;
    TextView phone;
    TextView email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);

        SharedPreferences shared = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        name.setText((shared.getString("name", "")));
        phone.setText((shared.getString("phone", "")));
        email.setText((shared.getString("email", "")));

    }
}
