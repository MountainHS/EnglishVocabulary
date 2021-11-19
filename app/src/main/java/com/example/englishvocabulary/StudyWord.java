package com.example.englishvocabulary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class StudyWord extends AppCompatActivity {

    private ViewPager pager;

    ArrayList<Word> word;
    ArrayList<String> eng;
    ArrayList<String> kor1;
    ArrayList<String> kor2;
    ArrayList<String> kor3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_word);

        pager = findViewById(R.id.viewpager);
        word = new ArrayList<>();
        eng = new ArrayList<>();
        kor1 = new ArrayList<>();
        kor2 = new ArrayList<>();
        kor3 = new ArrayList<>();

        Intent intent = getIntent();

        //여기 부분을 change
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
            word.add(inWord);
        }

        //Intent에서 가져온 String
        String whenString = intent.getStringExtra("when");

        pager = (ViewPager) findViewById(R.id.viewpager);
        MyAdaptor myAdaptor = new MyAdaptor(this, word, whenString);
        pager.setAdapter(myAdaptor);

    }
}