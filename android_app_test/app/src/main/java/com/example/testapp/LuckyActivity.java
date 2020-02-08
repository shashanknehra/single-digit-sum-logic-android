package com.example.testapp;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LuckyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lucky);

        // Get the Intent and extract the string
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String luckyNumber = intent.getStringExtra("lucky");


        System.out.println(name +"'s lucky number is" + luckyNumber);
        // Capture the layout's TextView and set the string as its text
        TextView label = findViewById(R.id.luckyNumberLabel);
        label.setText(name + "'s lucky number is");

        TextView number = findViewById(R.id.luckyNumberText);
        number.setText(luckyNumber);

    }
}
