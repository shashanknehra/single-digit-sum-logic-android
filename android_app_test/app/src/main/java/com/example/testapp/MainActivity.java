package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    EditText dateEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button calBtn = (Button)findViewById(R.id.calButton);
        calBtn.setOnClickListener(this);

        dateEditText = (EditText)findViewById(R.id.dobEditText);
        dateEditText.addTextChangedListener(new TextWatcher() {

            int lengthOfStringBeforeChange = 0;
            int currentLengthOfString = 0;
            public void afterTextChanged(Editable s) {
                currentLengthOfString = dateEditText.getText().toString().length();
                if(currentLengthOfString > lengthOfStringBeforeChange){
                    if((currentLengthOfString == 2) || (currentLengthOfString == 5)){
                        dateEditText.append("/");
                    }
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                lengthOfStringBeforeChange = dateEditText.getText().toString().length();
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

        });
    }

    public void onClick(View view)
    {
        TextView errorTextView = (TextView)findViewById(R.id.dobErrorLabel);

        TextView nameTextView = (TextView)findViewById(R.id.nameEditText);

        String dobStr=(dateEditText.getText().toString());

        if(validateDateFormat(dobStr)){
            // convert dobStr to char[] array
            char[] chars = dobStr.toCharArray();

            //variable to store total
            int total = 0;

            // iterate over char[] array using enhanced for loop
            for (char ch : chars) {
                if(!String.valueOf(ch).contains("/")){
                    int currentInt = Integer.parseInt(String.valueOf(ch));
                    total += currentInt;
                }
            }

            while (true) {
                if (total > 9) {
                    String totalString = Integer.toString(total);

                    total = 0;
                    for (int i = 0; i < totalString.length(); i++) {
                        char c = totalString.charAt(i);
                        int currentInt = Integer.parseInt(String.valueOf(c));
                        total += currentInt;
                    }
                } else {
                    break;
                }
            }

            Intent intent = new Intent(this, LuckyActivity.class);
            intent.putExtra("name",nameTextView.getText().toString());
            intent.putExtra("lucky",String.valueOf(total));

            nameTextView.setText("");
            dateEditText.setText("");
            errorTextView.setVisibility(View.INVISIBLE);

            startActivity(intent);
        }else{
            errorTextView.setVisibility(View.VISIBLE);
        }
    }

    private boolean validateDateFormat(String dateStr){
        try {
            //validate date
            Date dateObject = formatter.parse(dateStr);
            return true;
        }catch (java.text.ParseException e){
            return false;
        }
    }
}
