package com.example.englishvocabulary;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
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
    ArrayList<Boolean> isMem;

    ImageButton drawerOnoffButton;
    private RecyclerAdaptor adapter;

    ImageButton addWordButton;
    Dialog addWordDialog;
    DatabaseControl databaseControl;


    Intent receiveIntent; //MainActivity에서 가져온 Intent
    Intent goIntent;
    int ListVersion;
    //1 = 전체 리스트, 2 = 암기, 3 = 미암기, 4 = 오답노트

    Button sortList;
    CheckBox jungOnoff;
    int sortVersion;
    //0: 기본. 1:영단어순
    boolean jungOk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_word);
        databaseControl = new DatabaseControl();

        receiveIntent = getIntent();
        ListVersion = receiveIntent.getIntExtra("ListVersion", 1);


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

        jungOk = true;

        jungOnoff = findViewById(R.id.checkbox_jungOk); //checkBox으로 변경
        jungOnoff.setOnClickListener(this);
 /*       {
            @Override
            public void onClick(View view) {
                if (((CheckBox) view).isChecked()) {
                    jungOk = true;
                } else {
                    jungOk = false;
                }
            }
        });*/

        sortVersion = 0;
        sortList = findViewById(R.id.button_sortList);
        sortList.setText("정렬기준 : 기본");
        sortList.setOnClickListener(this);


        //일단 오답노트랑 그냥 단어장 구분
        if (ListVersion == 4) {
            databaseControl.update("OdapVoca", new DatabaseControl.OnGetDataListener() {
                @Override
                public void OnSuccess(ArrayList<Word> fetchedWordList) {
                    word = fetchedWordList;
                    getData(); //데이터 IN
                }
            });
        } else if (ListVersion == 3) {
            databaseControl.update("EngVoca3", new DatabaseControl.OnGetDataListener() {
                @Override
                public void OnSuccess(ArrayList<Word> fetchedWordList) {
                    word = fetchedWordList;
                    getData(); //데이터 IN
                }
            });
        } else if (ListVersion == 2) {
            databaseControl.update("EngVoca2", new DatabaseControl.OnGetDataListener() {
                @Override
                public void OnSuccess(ArrayList<Word> fetchedWordList) {
                    word = fetchedWordList;
                    getData(); //데이터 IN
                }
            });

        } else {
            Toast.makeText(getApplicationContext(), "기본", Toast.LENGTH_SHORT).show();
            databaseControl.update("EngVoca", new DatabaseControl.OnGetDataListener() {
                @Override
                public void OnSuccess(ArrayList<Word> fetchedWordList) {
                    word = fetchedWordList;
                    getData(); //데이터 IN
                }
            });
        }

        init_recyclerView(); //리스트 초기화

    }


    private void makeDialog() {

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        Window window = addWordDialog.getWindow();
        window.setLayout((int) (size.x * 0.9), (int) (size.y * 0.9));
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
                data.setIsMem(true);
                data.setisOdap(false);

                //파이어베이스에 단어 추가
                if (ListVersion == 4) {
                    databaseControl.addWord("OdapVoca", data);
                } else if (ListVersion == 3) {
                    databaseControl.addWord("EngVoca3", data);
                } else if (ListVersion == 2) {
                    databaseControl.addWord("EngVoca2", data);
                } else {
                    databaseControl.addWord("EngVoca", data);
                }

                word.add(data); //data에 set 한거 출력할 ArrayList에 추가
                eng.add(en);
                kor1.add(ko1);
                kor2.add(ko2);
                kor3.add(ko3);
                ////////////////////////////////이 부분을 eng->word로 바꿈
                //이 밑에 추가한 단어를 study_word, list_word의 arrayList에 append


                adapter.addItem(data); //adapter에 word_data(영+한)을 추가

                adapter.notifyDataSetChanged();
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

        adapter = new RecyclerAdaptor(ListVersion, sortVersion);
        recyclerView.setAdapter(adapter);
    }

    private void getData() {

        eng = new ArrayList<>();
        kor1 = new ArrayList<>();
        kor2 = new ArrayList<>();
        kor3 = new ArrayList<>();
        isMem = new ArrayList<>();
        adapter.init_Item();

        for (Word w : word) {
            eng.add(w.getEnglish());
            kor1.add(w.getKorean1());
            kor2.add(w.getKorean2());
            kor3.add(w.getKorean3());
            isMem.add(w.getisMem());
        }

        for (int i = 0; i < eng.size(); i++) { //이 구문 때문에, kor, eng다 크기 맞추기
            Word data = new Word();
            data.setEnglish(eng.get(i));
            data.setKorean1(kor1.get(i));
            data.setKorean2(kor2.get(i));
            data.setKorean3(kor3.get(i));
            data.setIsMem(isMem.get(i));
            data.setWhen(i);


            adapter.addItem(data); //adapter에 word_data(영+한)을 추가
            adapter.notifyDataSetChanged();
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        if (view == drawerOnoffButton) {
            Intent gomainIntent = new Intent(getApplicationContext(), MainActivity.class);
            gomainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(gomainIntent);
        } else { //정렬 출력 처리
            if (view == jungOnoff) {
                if (((CheckBox) view).isChecked()) {
                    jungOk = true;
                } else {
                    jungOk = false;
                }
            }

            if (view == sortList) {
                sortVersion += 1;
            }

            //일단 3개만 되게 하자
            if (sortVersion >= 3)
                sortVersion = sortVersion % 3; //1기본, 2영단어, 3한글

            if (sortVersion == 0) {
                sortList.setText("정렬기준 : 기본");
            } else if (sortVersion == 1) {
                sortList.setText("정렬기준 : 영단어순");
            }
            else if (sortVersion == 2) {
                sortList.setText("정렬기준 : 암기/미암기");
            }

            ////////ctrl c + v

            adapter.sortVersion = sortVersion;
            adapter.jungOk = jungOk;

            //오답노트
            if (ListVersion == 4) {
                if (sortVersion == 0) {
                    Toast.makeText(getApplicationContext(), "기본", Toast.LENGTH_SHORT).show();
                    databaseControl.update("OdapVoca", new DatabaseControl.OnGetDataListener() {
                        @Override
                        public void OnSuccess(ArrayList<Word> fetchedWordList) {
                            word = fetchedWordList;
                            getData(); //데이터 IN
                        }
                    });
                } else {
                    String check = "english";
                    if (sortVersion == 1)
                        check = "english";
                    else if(sortVersion == 2)
                        check = "isMem";
                    Toast.makeText(getApplicationContext(), check, Toast.LENGTH_SHORT).show();
                    databaseControl.queryOrder("OdapVoca", check, jungOk)
                            .update(new DatabaseControl.OnGetDataListener() {
                                @Override
                                public void OnSuccess(ArrayList<Word> fetchedWordList) {
                                    word = fetchedWordList;
                                    getData(); //데이터 IN
                                }
                            });
                }
            }
            //단어장3
            else if (ListVersion == 3) {
                if (sortVersion == 0) {
                    Toast.makeText(getApplicationContext(), "기본", Toast.LENGTH_SHORT).show();
                    databaseControl.update("EngVoca3", new DatabaseControl.OnGetDataListener() {
                        @Override
                        public void OnSuccess(ArrayList<Word> fetchedWordList) {
                            word = fetchedWordList;
                            getData(); //데이터 IN
                        }
                    });
                } else {
                    String check = "english";
                    if (sortVersion == 1)
                        check = "english";
                    else if(sortVersion == 2)
                        check = "isMem";
                    Toast.makeText(getApplicationContext(), check, Toast.LENGTH_SHORT).show();
                    databaseControl.queryOrder("EngVoca3", check, jungOk)
                            .update(new DatabaseControl.OnGetDataListener() {
                                @Override
                                public void OnSuccess(ArrayList<Word> fetchedWordList) {
                                    word = fetchedWordList;
                                    getData(); //데이터 IN
                                }
                            });
                }
            }
            //단어장2
            else if (ListVersion == 2) {
                if (sortVersion == 0) {
                    Toast.makeText(getApplicationContext(), "기본", Toast.LENGTH_SHORT).show();
                    databaseControl.update("EngVoca2", new DatabaseControl.OnGetDataListener() {
                        @Override
                        public void OnSuccess(ArrayList<Word> fetchedWordList) {
                            word = fetchedWordList;
                            getData(); //데이터 IN
                        }
                    });
                } else {
                    String check = "english";
                    if (sortVersion == 1)
                        check = "english";
                    else if(sortVersion == 2)
                        check = "isMem";
                    Toast.makeText(getApplicationContext(), check, Toast.LENGTH_SHORT).show();
                    databaseControl.queryOrder("EngVoca2", check, jungOk)
                            .update(new DatabaseControl.OnGetDataListener() {
                                @Override
                                public void OnSuccess(ArrayList<Word> fetchedWordList) {
                                    word = fetchedWordList;
                                    getData(); //데이터 IN
                                }
                            });
                }
            }
            //단어장1
            else {
                if (sortVersion == 0) {
                    Toast.makeText(getApplicationContext(), "기본", Toast.LENGTH_SHORT).show();
                    databaseControl.update("EngVoca", new DatabaseControl.OnGetDataListener() {
                        @Override
                        public void OnSuccess(ArrayList<Word> fetchedWordList) {
                            word = fetchedWordList;
                            getData(); //데이터 IN
                        }
                    });
                } else {
                    String check = "english";
                    if (sortVersion == 1)
                        check = "english";
                    else if(sortVersion == 2)
                        check = "isMem";
                    Toast.makeText(getApplicationContext(), check, Toast.LENGTH_SHORT).show();
                    databaseControl.queryOrder("EngVoca", check, jungOk)
                            .update(new DatabaseControl.OnGetDataListener() {
                                @Override
                                public void OnSuccess(ArrayList<Word> fetchedWordList) {
                                    word = fetchedWordList;
                                    getData(); //데이터 IN
                                }
                            });
                }
            }
            ///////ctrl c + v
        }

    }

}
