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
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
    DatabaseControl databaseControl;
    ImageButton gomain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_word);
        databaseControl = new DatabaseControl();

        pager = findViewById(R.id.viewpager);
        eng = new ArrayList<>();
        kor1 = new ArrayList<>();
        kor2 = new ArrayList<>();
        kor3 = new ArrayList<>();

        intent = getIntent();

        gomain = findViewById(R.id.button_openDrawerWithWordStudy);
        gomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gomainIntent = new Intent(getApplicationContext(), MainActivity.class);
                gomainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(gomainIntent);
            }
        });

        int ListVersion = intent.getIntExtra("ListVersion", 1);
        int sortVersion = intent.getIntExtra("sortVersion", 1);
        boolean jungOk = intent.getBooleanExtra("jungOk", true);
        Log.v("test", "ListVersion : " + ListVersion);

        if(ListVersion == 4) {
            if(sortVersion == 0) {
                databaseControl.update("OdapVoca", new DatabaseControl.OnGetDataListener() {
                    @Override
                    public void OnSuccess(ArrayList<Word> fetchedWordList) {
                        word = fetchedWordList;
                        startMyAdaptor();

                    }
                });
            }
            else {
                String check = "english";
                if (sortVersion == 1)
                    check = "english";
                else if(sortVersion == 2)
                    check = "isMem";
                //                    else if(sortVersion == 2)
                //                        check = "korean";
                databaseControl.queryOrder("OdapVoca", check, jungOk)
                        .update(new DatabaseControl.OnGetDataListener() {
                            @Override
                            public void OnSuccess(ArrayList<Word> fetchedWordList) {
                                word = fetchedWordList;
                                startMyAdaptor();
                            }
                        });
            }
        }

        else if(ListVersion == 3) {
            if(sortVersion == 0) {
                databaseControl.update("EngVoca3", new DatabaseControl.OnGetDataListener() {
                    @Override
                    public void OnSuccess(ArrayList<Word> fetchedWordList) {
                        word = fetchedWordList;
                        startMyAdaptor();

                    }
                });
            }
            else {
                String check = "english";
                if (sortVersion == 1)
                    check = "english";
                else if(sortVersion == 2)
                    check = "isMem";
                //                    else if(sortVersion == 2)
                //                        check = "korean";
                databaseControl.queryOrder("EngVoca3", check, jungOk)
                        .update(new DatabaseControl.OnGetDataListener() {
                            @Override
                            public void OnSuccess(ArrayList<Word> fetchedWordList) {
                                word = fetchedWordList;
                                startMyAdaptor();
                            }
                        });
            }
        }

        else if(ListVersion == 2) {
            if(sortVersion == 0) {
                databaseControl.update("EngVoca2", new DatabaseControl.OnGetDataListener() {
                    @Override
                    public void OnSuccess(ArrayList<Word> fetchedWordList) {
                        word = fetchedWordList;
                        startMyAdaptor();

                    }
                });
            }
            else {
                String check = "english";
                if (sortVersion == 1)
                    check = "english";
                else if(sortVersion == 2)
                    check = "isMem";
                //                    else if(sortVersion == 2)
                //                        check = "korean";
                databaseControl.queryOrder("EngVOca2", check, jungOk)
                        .update(new DatabaseControl.OnGetDataListener() {
                            @Override
                            public void OnSuccess(ArrayList<Word> fetchedWordList) {
                                word = fetchedWordList;
                                startMyAdaptor();
                            }
                        });
            }
        }

        else{
            if(sortVersion == 0) {
                databaseControl.update("EngVoca", new DatabaseControl.OnGetDataListener() {
                    @Override
                    public void OnSuccess(ArrayList<Word> fetchedWordList) {
                        word = fetchedWordList;
                        startMyAdaptor();

                    }
                });
            }
            else {
                String check = "english";
                if (sortVersion == 1)
                    check = "english";
                else if(sortVersion == 2)
                    check = "isMem";
                //                    else if(sortVersion == 2)
                //                        check = "korean";
                databaseControl.queryOrder("EngVoca", check, jungOk)
                        .update(new DatabaseControl.OnGetDataListener() {
                            @Override
                            public void OnSuccess(ArrayList<Word> fetchedWordList) {
                                word = fetchedWordList;
                                startMyAdaptor();
                            }
                        });
            }
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
