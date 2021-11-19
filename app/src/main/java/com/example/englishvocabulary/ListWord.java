package com.example.englishvocabulary;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishvocabulary.firestore.DatabaseControl;

import java.util.ArrayList;

public class ListWord extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Word> word; //파이어베이스에서 가져온 단어들 여기에 IN
    ArrayList<String> eng;
    ArrayList<String> kor1;
    ArrayList<String> kor2;
    ArrayList<String> kor3;

    Button drawerOnoffButton;
    private RecyclerAdaptor adapter;

    Button addWordButton;
    Dialog addWordDialog;

    /*
    Intent intent = getIntent(); //MainActivity에서 가져온 Intent
    int whatListSelect = intent.getIntExtra("LIST_VERSION", 1);
    //1 = 전체 리스트, 2 = 암기, 3 = 미암기, 4 = 오답노트
*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_word);



        drawerOnoffButton = findViewById(R.id.button_openDrawerWithWordList);
        drawerOnoffButton.setOnClickListener(this);

        addWordDialog = new Dialog(ListWord.this);
        addWordDialog.requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        addWordDialog.setContentView(R.layout.activity_add_word);

        addWordButton = findViewById(R.id.button_addword);
        addWordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeDialog(); //단어 추가창 생성
            }
        });

        init_recyclerView(); //리스트 초기화
        getData(); //데이터 IN

    }

    private void makeDialog() {
        addWordDialog.show();
        // *주의할 점: findViewById()를 쓸 때는 -> 앞에 반드시 다이얼로그 이름을 붙여야 한다.


        EditText input_en = addWordDialog.findViewById(R.id.edittext_input_english);
        EditText input_kor1 = addWordDialog.findViewById(R.id.edittext_input_korean1);
        EditText input_kor2 = addWordDialog.findViewById(R.id.edittext_input_korean2);
        EditText input_kor3 = addWordDialog.findViewById(R.id.edittext_input_korean3);
        Button ok = addWordDialog.findViewById(R.id.button_inputword_ok);
        Button no = addWordDialog.findViewById(R.id.button_inputword_no);

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
                Word data = new Word(); //여기에 입력받은 단어가 word 형태로 저장
                data.setEnglish(en);
                data.setKorean1(ko1);
                data.setKorean2(ko2);
                data.setKorean3(ko3);
                data.setWhen(eng.size());
                data.setisMen(true);
                data.setisOdap(false);

                //파이어베이스에 단어 추가
                DatabaseControl.addWord(data);

                word.add(data); //data에 set 한거 출력할 ArrayList에 추가
                eng.add(en);
                kor1.add(ko1);
                kor2.add(ko2);
                kor3.add(ko3);
                ////////////////////////////////이 부분을 eng->word로 바꿈
                //이 밑에 추가한 단어를 study_word, list_word의 arrayList에 append


                adapter.addItem(data); //adapter에 word_data(영+한)을 추가
                //adapter.notifyDataSetChanged();
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addWordDialog.dismiss();
            }
        });
        adapter.notifyDataSetChanged();
    }

    private void init_recyclerView() {
        RecyclerView recyclerView = findViewById(R.id.RecyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecyclerAdaptor();
        recyclerView.setAdapter(adapter);
    }

    private void getData() {
        //임시

        word = new ArrayList<>();
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
            Word data = new Word();
            data.setEnglish(eng.get(i));
            data.setKorean1(kor1.get(i));
            data.setKorean2(kor2.get(i));
            data.setKorean3(kor3.get(i));
            data.setWhen(i);
            if(i%2==0) {
                data.setisMen(false);
                data.setisOdap(false);
            }
            else{
                data.setisMen(true);
                data.setisOdap(true);
            }

            adapter.addItem(data); //adapter에 word_data(영+한)을 추가
        }

        adapter.notifyDataSetChanged();

    }

    @Override
    public void onClick(View view) {
        if (view == drawerOnoffButton) {
            DrawerLayout drawer = findViewById(R.id.activity_WordList);
            if (!drawer.isDrawerOpen(Gravity.LEFT)) {
                drawer.openDrawer(Gravity.LEFT);
            }
        }
    }
}