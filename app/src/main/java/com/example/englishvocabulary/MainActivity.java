package com.example.englishvocabulary;

import static android.os.Environment.DIRECTORY_DOWNLOADS;
import static com.example.englishvocabulary.firestore.DatabaseControl.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.englishvocabulary.firestore.DatabaseControl;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//    Button myword;
//    Button amgi;
//    Button miamgi;
    // databaseControl test start
    DatabaseControl dbC;
    // end
    LinearLayout myword;
    LinearLayout myword2;
    LinearLayout myword3;

    Button odab;
    Button test;
    Button game;
    ImageButton drawerhandle;
    Button close_drawer;
    Button goNaver;
    DatabaseControl databaseControl;

    TextView mywordManyword;
    TextView myword2Manyword;
    TextView myword3Manyword;

    //단어 개수 출력용 list
    ArrayList<Word> list1;
    ArrayList<Word> list2;
    ArrayList<Word> list3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseControl = new DatabaseControl();
//        // databaseControl test start
//        File testFile = Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS);
//        Log.d("!!!!!",testFile.getAbsolutePath());
//        for (String s:testFile.list()){
//            Log.d("!!!!!", s);
//        }
//
//        try {
//            testFile = new File(testFile.getAbsolutePath() + "/test.txt");
//            Log.d("!!!!!",testFile.getAbsolutePath());
//            FileWriter testWriteFile = new FileWriter(testFile);
//            testWriteFile.write("1. culture \t문화, 교양\n" +
//                    "2. experience \t경험, 경험하다\n" +
//                    "3. education \t교육\n" +
//                    "4. symbol \t상징\n" +
//                    "5. effect \t결과, 영향, 효과\n" +
//                    "6. liberty \t자유\n" +
//                    "7. affair \t사건, 일\n" +
//                    "8. comfort \t안락, 위안\n" +
//                    "9. tradition \t전통\n" +
//                    "10. subject \t학과, 주제, 주어\n" +
//                    "11. object \t물건, 목적, 반대하다\n" +
//                    "12. source \t출처, 근원\n" +
//                    "13. revolution \t혁명\n" +
//                    "14. pollution \t오염\n" +
//                    "15. system \t체계, 조직, 제도\n" +
//                    "16. triumph \t승리\n" +
//                    "17. respect \t존경, 존경하다\n" +
//                    "18. communication \t의사소통, 전달\n" +
//                    "19. foundation \t기초, 근거, 설립\n" +
//                    "20. glory \t영광");
//            testWriteFile.close();
//        }
//        catch (IOException e){
//            Log.d("!!!!!","test.txt IOE");
//        }
//        dbC.uploadVocabularyDataSet("testVoca", testFile.getAbsolutePath());
//        // end

//        myword = (Button) findViewById(R.id.button_myword);
//        amgi = (Button) findViewById(R.id.button_amgi);
//        miamgi = (Button) findViewById(R.id.button_miamgi);
        myword = findViewById(R.id.myword);
        myword2 = findViewById(R.id.myword2);
        myword3 = findViewById(R.id.myword3);

        odab = (Button) findViewById(R.id.button_odap);
        test = (Button) findViewById(R.id.button_test);
        game = (Button) findViewById(R.id.button_game);
        drawerhandle = (ImageButton) findViewById(R.id.button_openDrawerWithMain);
        close_drawer = (Button)findViewById(R.id.button_close_drawer);
        goNaver = (Button)findViewById(R.id.button_goNaver);

        mywordManyword = findViewById(R.id.textview_mywordManyWord);
        myword2Manyword = findViewById(R.id.textview_myword2ManyWord);
        myword3Manyword = findViewById(R.id.textview_myword3ManyWord);

        myword.setOnClickListener(this);
        myword2.setOnClickListener(this);
        myword3.setOnClickListener(this);
        odab.setOnClickListener(this);
        test.setOnClickListener(this);
        game.setOnClickListener(this);
        goNaver.setOnClickListener(this);
        drawerhandle.setOnClickListener(this);

        //단어 개수 출력용


        databaseControl.update("EngVoca", new DatabaseControl.OnGetDataListener() {
            @Override
            public void OnSuccess(ArrayList<Word> fetchedWordList) {
                list1 = fetchedWordList;
                mywordManyword.setText(list1.size()+"");
            }
        });
        databaseControl.update("EngVoca2", new DatabaseControl.OnGetDataListener() {
            @Override
            public void OnSuccess(ArrayList<Word> fetchedWordList) {
                list2 = fetchedWordList;
                myword2Manyword.setText(list2.size()+"");
            }
        });
        databaseControl.update("EngVoca3", new DatabaseControl.OnGetDataListener() {
            @Override
            public void OnSuccess(ArrayList<Word> fetchedWordList) {
                list3 = fetchedWordList;
                myword3Manyword.setText(list3.size()+"");
            }
        });


    }

    @Override
    public void onClick(View view) {
        Intent intent;
        /*순서
        1. MainActivity에서 intent로 ListWord에 ListVersion을 보낸다. -> ListWord에서 List맞게 DB에 갖고온다.
        2. ListWord에서 RecyclerAdaptor로 생성자를 통해, ListVersion을 보낸다.
        3. RecyclerAdaptor에서 ListVersion을 StudyWord로 intent로 보낸다. -> StudyWord에서 intent로 받은 것을 통해 DB에서 갖고온다.
        */
        if(view==drawerhandle){
            /*
            DrawerLayout drawer = findViewById(R.id.activity_main);
            if(!drawer.isDrawerOpen(Gravity.LEFT)){
                drawer.openDrawer(Gravity.LEFT);
            }*/
            Intent gomainIntent = new Intent(getApplicationContext(), MainActivity.class);
            gomainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(gomainIntent);
        }
        else if(view==myword){
            intent = new Intent(getApplicationContext(), ListWord.class);
            intent.putExtra("ListVersion", 1);
            startActivity(intent);
        }

        else if(view==myword2){
            intent = new Intent(getApplicationContext(), ListWord.class);
            intent.putExtra("ListVersion", 2);
            startActivity(intent);
        }
        else if(view==myword3){
            intent = new Intent(getApplicationContext(), ListWord.class);
            intent.putExtra("ListVersion", 3);
            startActivity(intent);
        }
        else if(view==odab){
            intent = new Intent(getApplicationContext(), ListWord.class);
            intent.putExtra("ListVersion", 4);
            startActivity(intent);
        }
        else if(view==goNaver){
            intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://endic.naver.com/"));
            startActivity(intent);
        }
        else if(view==test){
            intent = new Intent(getApplicationContext(), TestSetting.class);
            startActivity(intent);
        }

        else if(view==game){
            moveTaskToBack(true);
            finishAndRemoveTask();
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == 200 && grantResults.length > 0) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
//                fileReadPermission = true;
//            if (grantResults[1] == PackageManager.PERMISSION_GRANTED)
//                fileWritePermission = true;
//        }
//    }
}