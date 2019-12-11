package com.simant.topic7application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DashboardActivity extends AppCompatActivity {

    private ListView lstDictionary;
    private Map<String, String> dictionary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        lstDictionary = findViewById(R.id.lstDictionary);
        dictionary = new HashMap<>();

        // fetch all the data
        readFromFile();
        ArrayAdapter adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                new ArrayList<String>(dictionary.keySet())
        );
        lstDictionary.setAdapter(adapter);

        lstDictionary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String key = adapterView.getItemAtPosition(i).toString();
                String meaning = dictionary.get(key);
                Intent intent = new Intent(getApplicationContext(), MeaningActivity.class);
                intent.putExtra("meaning", meaning);
                startActivity(intent);
            }
        });

    }

    private void readFromFile() {
        try {
            FileInputStream fos = openFileInput("word.txt");
            InputStreamReader isr = new InputStreamReader(fos);
            BufferedReader br = new BufferedReader(isr);
            String line = "";
            while ((line=br.readLine()) != null ) {
                String[] parts = line.split("->");
                dictionary.put(parts[0], parts[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
