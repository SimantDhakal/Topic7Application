package com.simant.topic7application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MeaningActivity extends AppCompatActivity {

    TextView textViewMeaning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meaning);

        textViewMeaning = findViewById(R.id.txtMeaning);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String result = bundle.getString("meaning");
            textViewMeaning.setText(result);
        }

    }
}
