package com.example.englishvocabulary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Test extends AppCompatActivity {

    TextView text;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        intent = getIntent();
        int many = intent.getIntExtra("ManyTestWord", 0);



    }
}