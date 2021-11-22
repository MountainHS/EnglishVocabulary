package com.example.englishvocabulary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TestSetting extends AppCompatActivity implements View.OnClickListener {

    Button plus10;
    Button plus20;
    Button plus50;
    Button plusMax;
    Button startTest;
    EditText inputManyWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_setting);

        plus10 = findViewById(R.id.button_plus10test);
        plus20 = findViewById(R.id.button_plus20test);
        plus50 = findViewById(R.id.button_plus50test);
        plusMax = findViewById(R.id.button_plusMAXtest);
        startTest = findViewById(R.id.button_startTest);
        inputManyWord = findViewById(R.id.edittext_numberoftest);

        plus10.setOnClickListener(this);
        plus20.setOnClickListener(this);
        plus50.setOnClickListener(this);
        plusMax.setOnClickListener(this);
        startTest.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view == plus10){
            inputManyWord.setText("");
            inputManyWord.setText("10");
        }
        else if(view == plus20){
            inputManyWord.setText("");
            inputManyWord.setText("20");
        }
        else if(view == plus50){
            inputManyWord.setText("");
            inputManyWord.setText("50");
        }
        //일단 100개로 설정
        else if(view == plusMax){
            inputManyWord.setText("");
            inputManyWord.setText("100");
        }
        else if(view == startTest){
            String many = inputManyWord.getText().toString();
            int manyTestWord = Integer.parseInt(many);
            Intent intent = new Intent(getApplicationContext(), Test.class);
            intent.putExtra("ManyTestWord", manyTestWord);
            startActivity(intent);
        }
    }
}