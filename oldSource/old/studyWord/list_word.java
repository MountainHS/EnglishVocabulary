package com.example.englishvocabulary.old.studyWord;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class list_word extends AppCompatActivity implements View.OnClickListener {

    ArrayList<String> eng;
    ArrayList<String> kor1;
    ArrayList<String> kor2;
    ArrayList<String> kor3;

    Button onoff_button;
    private RecyclerAdapter adapter;

    Button addword_button;
    Dialog addwordDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_word);


        onoff_button = findViewById(R.id.draweronoff_button2);
        onoff_button.setOnClickListener(this);

        addwordDialog = new Dialog(list_word.this);//초기화
        addwordDialog.requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        addwordDialog.setContentView(R.layout.activity_add_word);


        addword_button = findViewById(R.id.addword_button);
        addword_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeDialog(); //단어 추가창 생성
            }
        });

        init_recyclerView(); //리스트 초기화
        getData(); //데이터 IN


    }

    private void makeDialog() {
        addwordDialog.show();
        // *주의할 점: findViewById()를 쓸 때는 -> 앞에 반드시 다이얼로그 이름을 붙여야 한다.


        EditText input_en = addwordDialog.findViewById(R.id.input_english);
        EditText input_kor1 = addwordDialog.findViewById(R.id.input_korean1);
        EditText input_kor2 = addwordDialog.findViewById(R.id.input_korean2);
        EditText input_kor3 = addwordDialog.findViewById(R.id.input_korean3);
        Button ok = addwordDialog.findViewById(R.id.inputword_ok_button);
        Button no = addwordDialog.findViewById(R.id.inputword_no_button);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String en = input_en.getText().toString();
                String ko1 = input_kor1.getText().toString();
                String ko2 = input_kor2.getText().toString();
                String ko3 = input_kor3.getText().toString();
                //edittext초기화
                input_en.setText("");
                input_kor1.setText("");
                input_kor2.setText("");
                input_kor3.setText("");

                //Word_data에도 추가
                Word_data data = new Word_data();
                data.setEnglish(en);
                data.setKorean1(ko1);
                data.setKorean2(ko2);
                data.setKorean3(ko3);
                data.setWhen(eng.size());

                //이 밑에 추가한 단어를 study_word, list_word의 arrayList에 append
                eng.add(en);
                kor1.add(ko1);
                kor2.add(ko2);
                kor3.add(ko3);

                adapter.addItem(data); //adapter에 word_data(영+한)을 추가
                //adapter.notifyDataSetChanged();
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addwordDialog.dismiss();
            }
        });
        adapter.notifyDataSetChanged();
    }

    private void init_recyclerView() {
        RecyclerView recyclerView = findViewById(R.id.RecyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void getData() {
        eng = new ArrayList<>();
        kor1 = new ArrayList<>();
        kor2 = new ArrayList<>();
        kor3 = new ArrayList<>();


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

        for (int i = 0; i < eng.size(); i++) { //이 구문 때문에, kor, eng다 크기 맞추기
            Word_data data = new Word_data();
            data.setEnglish(eng.get(i));
            data.setKorean1(kor1.get(i));
            data.setKorean2(kor2.get(i));
            data.setKorean3(kor3.get(i));
            data.setWhen(i);


            adapter.addItem(data); //adapter에 word_data(영+한)을 추가
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        if (view == onoff_button) {
            DrawerLayout drawer = findViewById(R.id.word_list_activity);
            if (!drawer.isDrawerOpen(Gravity.LEFT)) {
                drawer.openDrawer(Gravity.LEFT);
            }
        }

    }


}