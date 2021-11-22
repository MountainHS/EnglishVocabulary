package com.example.englishvocabulary.old.studyWord;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class add_word extends AppCompatActivity {

    EditText input_en;
    EditText input_kor1;
    EditText input_kor2;
    EditText input_kor3;

    Button ok;
    Button no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);
/*
        input_en = findViewById(R.id.input_english);
        input_kor1 = findViewById(R.id.input_korean1);
        input_kor2 = findViewById(R.id.input_korean2);
        input_kor3 = findViewById(R.id.input_korean3);
        ok = findViewById(R.id.inputword_ok_button);
        no = findViewById(R.id.inputword_no_button);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String en = input_en.getText().toString();
                String kor1 = input_kor1.getText().toString();
                String kor2 = input_kor2.getText().toString();
                String kor3 = input_kor3.getText().toString();
                //edittext초기화
                input_en.setText("");
                input_kor1.setText("");
                input_kor2.setText("");
                input_kor3.setText("");

                //이 밑에 추가한 단어를 study_word, list_word의 arrayList에 append

            }
        });
*/
    }
}