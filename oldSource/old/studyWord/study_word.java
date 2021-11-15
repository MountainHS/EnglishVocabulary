package com.example.englishvocabulary.old.studyWord;

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

import java.lang.reflect.Array;
import java.util.ArrayList;

public class study_word extends AppCompatActivity  {

    private ViewPager pager;

    ArrayList<String> eng;
    ArrayList<String> kor1;
    ArrayList<String> kor2;
    ArrayList<String> kor3;


    /*
    String[] eng1;
    String[] kor11;
    String[] kor12;
    String[] kor13;
    */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_word);

        pager = findViewById(R.id.viewpager);
        eng = new ArrayList<>();
        kor1 = new ArrayList<>();
        kor2 = new ArrayList<>();
        kor3 = new ArrayList<>();

        Intent inent = getIntent(); //이 부분이 이제 앞으로 위치 옮길 때 사용하는 변수
        //getIntent().getIntExtra("When",0)


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

        /*
        eng1 = getResources().getStringArray(R.array.English);
        kor11 = getResources().getStringArray(R.array.Korean1);
        kor12 = getResources().getStringArray(R.array.Korean3);
        kor13 = getResources().getStringArray(R.array.Korean3);
*/


        pager = (ViewPager) findViewById(R.id.viewpager);
        MyAdaptor myAdaptor = new MyAdaptor(this, eng, kor1, kor2, kor3, getIntent().getIntExtra("when",0));
       // MyAdaptor myAdaptor = new MyAdaptor(this, eng, kor1, kor2, kor3, eng1, kor11, kor12, kor13);
        pager.setAdapter(myAdaptor);




    }




}