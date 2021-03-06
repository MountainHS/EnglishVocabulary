package com.example.englishvocabulary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.englishvocabulary.firestore.DatabaseControl;

import java.util.ArrayList;
import java.util.Collections;

public class TestSetting extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    DatabaseControl databaseControl;
    Button plus10;
    Button plus20;
    Button plus50;
    Button plusMax;
    Button startTest;
    EditText inputManyWord;
    ArrayList<Word> word;

    ImageButton drawerhandle;

    RadioGroup setRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        databaseControl = new DatabaseControl();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_setting);

        plus10 = findViewById(R.id.button_plus10test);
        plus20 = findViewById(R.id.button_plus20test);
        plus50 = findViewById(R.id.button_plus50test);
        plusMax = findViewById(R.id.button_plusMAXtest);
        startTest = findViewById(R.id.button_startTest);
        inputManyWord = findViewById(R.id.edittext_numberoftest);
        drawerhandle = findViewById(R.id.button_openDrawerWithTestSetting);

        plus10.setOnClickListener(this);
        plus20.setOnClickListener(this);
        plus50.setOnClickListener(this);
        plusMax.setOnClickListener(this);
        startTest.setOnClickListener(this);

        setRadio = findViewById(R.id.radiogroup_set);
        setRadio.setOnCheckedChangeListener(this);
        drawerhandle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gomainIntent = new Intent(getApplicationContext(), MainActivity.class);
                gomainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(gomainIntent);
            }
        });
        //?????? ?????? engVoca??? ?????? ?????? ????????????



    }

    @Override
    public void onClick(View view) {
        String many;
        int manyTestWord = 0;

        if(setRadio.getCheckedRadioButtonId() == -1){   //???????????? ???????????? ?????? ?????? -1 return
            Toast.makeText(getApplicationContext(), "???????????? ????????? ?????????", Toast.LENGTH_SHORT).show();
            return;
        }

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
        //arraylist size??? ??????
        else if(view == plusMax){
            inputManyWord.setText("");
            inputManyWord.setText(word.size() + "");
        }
        else if(view == startTest){
            Log.v("testing","startTest ??????");

            many = inputManyWord.getText().toString();
            if(many.equals("") || many.equals(" ")){
                Toast.makeText(getApplicationContext(), "?????? ?????? ???????????????", Toast.LENGTH_SHORT).show();
            }
            else {
                manyTestWord = Integer.parseInt(many);
                if(manyTestWord <= word.size()) {
                    manyTestWord = Integer.parseInt(many);
                    Collections.shuffle(word);
                    Intent intent = new Intent(getApplicationContext(), Test.class);
                    intent.putExtra("TestArray", word);
                    intent.putExtra("ManyTestWord", manyTestWord);
                    Log.v("testing","?????? ?????? ???  " + manyTestWord +"?????? ?????? ??????...");
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "????????? ?????? ?????? ?????? ??? ????????? ?????? ?????? ????????? ?????? ???????????????", Toast.LENGTH_SHORT).show();
                    inputManyWord.setText(word.size() + "");
                    manyTestWord = word.size();
                }
            }
        }
        
        many = inputManyWord.getText().toString();
        if(many.equals("") || many.equals(" ")){
            //?????? ??????
        }
        else {
            manyTestWord = Integer.parseInt(many);
            //?????? ????????? ????????? many?????? ?????????, ArrayList?????? ?????? ?????? ???????????? Toast message??? ?????? ????????????.
            if (manyTestWord > word.size()) {
                Toast.makeText(getApplicationContext(), "????????? ?????? ?????? ?????? ??? ????????? ?????? ?????? ????????? ?????? ???????????????", Toast.LENGTH_SHORT).show();
                inputManyWord.setText(word.size() + "");
                manyTestWord = word.size();
            }
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int check) {
        if(check == R.id.radiobutton_myword){
            databaseControl.update("EngVoca", new DatabaseControl.OnGetDataListener(){
                @Override
                public void OnSuccess(ArrayList<Word> fetchedWordList) {
                    word = fetchedWordList;
                }
            });
        }
        else if(check == R.id.radiobutton_myword2){
            databaseControl.update("EngVoca2", new DatabaseControl.OnGetDataListener(){
                @Override
                public void OnSuccess(ArrayList<Word> fetchedWordList) {
                    word = fetchedWordList;
                }
            });
        }
        else if(check == R.id.radiobutton_myword3){
            databaseControl.update("EngVoca3", new DatabaseControl.OnGetDataListener(){
                @Override
                public void OnSuccess(ArrayList<Word> fetchedWordList) {
                    word = fetchedWordList;
                }
            });
        }
    }
}