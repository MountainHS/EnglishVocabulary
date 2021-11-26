package com.example.englishvocabulary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.englishvocabulary.firestore.DatabaseControl;

import java.util.ArrayList;
import java.util.Collections;

public class TestSetting extends AppCompatActivity implements View.OnClickListener {

    Button plus10;
    Button plus20;
    Button plus50;
    Button plusMax;
    Button startTest;
    EditText inputManyWord;
    ArrayList<Word> word;

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

        //일단 바로 engVoca에 있는 단어 갖고오기
        DatabaseControl.update("EngVoca", new DatabaseControl.OnGetDataListener(){
            @Override
            public void OnSuccess(ArrayList<Word> fetchedWordList) {
                word = fetchedWordList;
            }
        });


    }

    @Override
    public void onClick(View view) {
        String many;
        int manyTestWord;
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
            many = inputManyWord.getText().toString();
            if(many.equals("") || many.equals(" ")){
                Toast.makeText(getApplicationContext(), "문제 수를 입력하세요", Toast.LENGTH_SHORT).show();
            }
            else {
                manyTestWord = Integer.parseInt(many);
                Collections.shuffle(word);
                Intent intent = new Intent(getApplicationContext(), Test.class);
                intent.putExtra("TestArray", word);
                intent.putExtra("ManyTestWord", manyTestWord);
                startActivity(intent);
            }
        }
        
        many = inputManyWord.getText().toString();
        if(many.equals("") || many.equals(" ")){
            //그냥 두기
        }
        else {
            manyTestWord = Integer.parseInt(many);
            //내가 설정한 문제가 many보다 많으면, ArrayList수로 문제 수를 고정하고 Toast message로 이를 알려준다.
            if (manyTestWord > word.size()) {
                Toast.makeText(getApplicationContext(), "설정한 문제 수가 단어 장 수보다 많아 최대 단어장 수로 조정합니다", Toast.LENGTH_SHORT).show();
                inputManyWord.setText(word.size() + "");
                manyTestWord = word.size();
            }
        }
    }
}