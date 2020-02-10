package com.example.mad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;


public class Main3Activity extends AppCompatActivity {
    int noteId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        EditText editText = (EditText)findViewById(R.id.editText);

        Intent intent = getIntent();
        noteId = intent.getIntExtra("noteId", -1);
        if (noteId != -1) {

            editText.setText(Main2Activity.notes.get(noteId));
        }else {
            Main2Activity.notes.add("");
            Main2Activity.adapter.notifyDataSetChanged();
            noteId = Main2Activity.notes.size() - 1;
        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Main2Activity.notes.set(noteId, String.valueOf(s));
                Main2Activity.adapter.notifyDataSetChanged();
                SharedPreferences sharedPreferences = getApplicationContext()
                        .getSharedPreferences("com.example.ekene.mynotes", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet<String>(Main2Activity.notes);
                sharedPreferences.edit().putStringSet("notes", set).apply();
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}