package com.example.englishvocabulary;

import static com.example.englishvocabulary.firestore.DatabaseControl.update;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button myword;
    Button amgi;
    Button miamgi;
    Button odab;
    Button test;
    Button game;
    Button drawerhandle;
    Button close_drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myword = (Button) findViewById(R.id.button_myword);
        amgi = (Button) findViewById(R.id.button_amgi);
        miamgi = (Button) findViewById(R.id.button_miamgi);
        odab = (Button) findViewById(R.id.button_odap);
        test = (Button) findViewById(R.id.button_test);
        game = (Button) findViewById(R.id.button_game);
        drawerhandle = (Button) findViewById(R.id.button_openDrawerWithMain);
        close_drawer = (Button)findViewById(R.id.button_close_drawer);

        myword.setOnClickListener(this);
        amgi.setOnClickListener(this);
        miamgi.setOnClickListener(this);
        odab.setOnClickListener(this);
        test.setOnClickListener(this);
        game.setOnClickListener(this);
        drawerhandle.setOnClickListener(this);

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
            DrawerLayout drawer = findViewById(R.id.activity_main);
            if(!drawer.isDrawerOpen(Gravity.LEFT)){
                drawer.openDrawer(Gravity.LEFT);
            }
        }
        else if(view==myword){
            intent = new Intent(getApplicationContext(), ListWord.class);
            intent.putExtra("ListVersion", 1);
            startActivity(intent);
        }

        else if(view==amgi){
            intent = new Intent(getApplicationContext(), ListWord.class);
            intent.putExtra("ListVersion", 2);
            startActivity(intent);
        }
        else if(view==miamgi){
            intent = new Intent(getApplicationContext(), ListWord.class);
            intent.putExtra("ListVersion", 3);
            startActivity(intent);
        }
        else if(view==odab){
            intent = new Intent(getApplicationContext(), ListWord.class);
            intent.putExtra("ListVersion", 4);
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
}