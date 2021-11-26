package com.example.englishvocabulary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.englishvocabulary.firestore.DatabaseControl;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class StudyWord extends AppCompatActivity {

    private ViewPager pager;

    ArrayList<Word> word;
    ArrayList<Word> word2;
    ArrayList<String> eng;
    ArrayList<String> kor1;
    ArrayList<String> kor2;
    ArrayList<String> kor3;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_word);

        pager = findViewById(R.id.viewpager);
        eng = new ArrayList<>();
        kor1 = new ArrayList<>();
        kor2 = new ArrayList<>();
        kor3 = new ArrayList<>();

        intent = getIntent();

        int whatListSelect = intent.getIntExtra("whatListSelect", 1);

        if(whatListSelect == 4) {
            DatabaseControl.update("OdapVoca", new DatabaseControl.OnGetDataListener() {
                @Override
                public void OnSuccess(ArrayList<Word> fetchedWordList) {
                    word = fetchedWordList;
                    startMyAdaptor();

                }
            });
        }

        else{
            DatabaseControl.update("EngVoca", new DatabaseControl.OnGetDataListener() {
                @Override
                public void OnSuccess(ArrayList<Word> fetchedWordList) {
                    word = fetchedWordList;
                    startMyAdaptor();

                }
            });
        }

    }

    public void startMyAdaptor(){
        int when_go = 0;
        String whenString = intent.getStringExtra("when");
        for(int i=0; i<word.size(); i++){
            if(word.get(i).getEnglish().equals(whenString)){
                when_go = i;
                break;
            }
        }
        pager = (ViewPager) findViewById(R.id.viewpager);
        MyAdaptor myAdaptor = new MyAdaptor(this, word, whenString);
        pager.setAdapter(myAdaptor);
        pager.setCurrentItem(when_go);
    }
}
