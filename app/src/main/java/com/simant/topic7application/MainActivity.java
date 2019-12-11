package com.simant.topic7application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintStream;

public class MainActivity extends AppCompatActivity {

    EditText editTextWord, editTextMeaning;
    Button buttonSave, buttonCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextWord = findViewById(R.id.etWord);
        editTextMeaning = findViewById(R.id.etMeaning);
        buttonSave = findViewById(R.id.btnSave);
        buttonCheck = findViewById(R.id.btnVisit);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });

        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                startActivity(intent);
            }
        });

    }

    private void save() {
        try {
            PrintStream printStream = new PrintStream(openFileOutput("word.txt", MODE_PRIVATE | MODE_APPEND));
            printStream.println(editTextWord.getText().toString() + "->" + editTextMeaning.getText().toString());
            Toast.makeText(getApplicationContext(), "Saved to " + getFilesDir(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Log.d("Dictionary app ", "Error: " + e.toString());
            e.printStackTrace();
        }
    }
}
