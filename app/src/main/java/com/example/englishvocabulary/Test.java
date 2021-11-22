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

    /*
    일단 ArrayList에 저장한 후, 현재 문제 변수 i, 정답 변수, 오답 변수, 전체 문제 변수=intent, 로 정한 다음
    한 문제 풀고 나면, settext초기화 하고, 변수 늘리고 등등등 이용해서 종료하기 버튼을 누르거나 i==intent면 종료 화면으로 이동
*/



    }
}