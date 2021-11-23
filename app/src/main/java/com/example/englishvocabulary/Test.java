package com.example.englishvocabulary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Test extends AppCompatActivity {

    Intent intent;
    TextView alltext;
    TextView nowtext;
    Button checkAnswer;
    Button jumpAnswer;
    Button endTest;


    ArrayList<Word> testWord;
    ArrayList<String> eng;
    ArrayList<String> kor1;
    ArrayList<String> kor2;
    ArrayList<String> kor3;

    int i; //한 문제 씩 증가

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        setWord(); //일단 임시로 단어 가져오기

        intent = getIntent();
        int many = intent.getIntExtra("ManyTestWord", 0); //문제 수
        //내가 설정한 문제가 many보다 많으면, ArrayList수로 문제 수를 고정하고 Toast message로 이를 알려준다.
        if(many > testWord.size()){
            Toast.makeText(getApplicationContext(), "설정한 문제 수가 단어 장 수보다 많아 최대 단어장 수로 조정합니다...", Toast.LENGTH_SHORT).show();
            many = testWord.size();
        }

        alltext = findViewById(R.id.textview_allTest);
        alltext.setText(many);
        i = 1;
        nowtext = findViewById(R.id.textview_progressTest);
        nowtext.setText(i);
        ///////////////////////////여기까지가 기본 세팅//////////////////////////
        checkAnswer = findViewById(R.id.button_checkAnswer);
        jumpAnswer = findViewById(R.id.button_jumpAnswer);
        endTest = findViewById(R.id.button_endTest);






    /*
    일단 ArrayList에 저장한 후, 현재 문제 변수 i, 정답 변수, 오답 변수, 전체 문제 변수=intent, 로 정한 다음
    한 문제 풀고 나면, settext초기화 하고, 변수 늘리고 등등등 이용해서 종료하기 버튼을 누르거나 i==intent면 종료 화면으로 이동
*/
    }

    void setWord(){

        eng.add("banana");
        kor1.add("바나나");
        kor2.add("");
        kor3.add("");
        eng.add("apple");
        kor1.add("사과");
        kor2.add("");
        kor3.add("");
        eng.add("duplicate");
        kor1.add("복제");
        kor2.add("복사하다");
        kor3.add("");
        eng.add("watermelon");
        kor1.add("수박");
        kor2.add("");
        kor3.add("");
        eng.add("example1");
        kor1.add("예제1");
        kor2.add("");
        kor3.add("");
        eng.add("example2");
        kor1.add("예제2");
        kor2.add("예제2");
        kor3.add("");
        eng.add("example3");
        kor1.add("예제3");
        kor2.add("예제3");
        kor3.add("예제3");
        eng.add("example4");
        kor1.add("예제4");
        kor2.add("");
        kor3.add("");
        eng.add("example5");
        kor1.add("예제5");
        kor2.add("예제5");
        kor3.add("");
        eng.add("example6");
        kor1.add("예제6");
        kor2.add("예제6");
        kor3.add("예제6");
        for(int i=0; i<eng.size(); i++){
            Word inWord = new Word();
            inWord.setEnglish(eng.get(i));
            inWord.setKorean1(kor1.get(i));
            inWord.setKorean2(kor2.get(i));
            inWord.setKorean3(kor3.get(i));
            inWord.setWhen(i);
            if(i%2==0) {
                inWord.setisMen(false);
                inWord.setisOdap(false);
            }
            else{
                inWord.setisMen(true);
                inWord.setisOdap(true);
            }
            testWord.add(inWord);
        }

    }
}
