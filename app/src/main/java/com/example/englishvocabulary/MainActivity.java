package com.example.englishvocabulary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

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
<<<<<<< HEAD
        drawerhandle = (Button) findViewById(R.id.button_closeDrawerWithMain);
=======
        drawerhandle = (Button) findViewById(R.id.button_openDrawerWithMain);
>>>>>>> word_list_study
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
        if(view==drawerhandle){
            DrawerLayout drawer = findViewById(R.id.activity_main);
            if(!drawer.isDrawerOpen(Gravity.LEFT)){
                drawer.openDrawer(Gravity.LEFT);
            }
        }
        else if(view==myword){
            Intent intent = new Intent(getApplicationContext(), ListWord.class);
<<<<<<< HEAD
=======
            intent.putExtra("LIST_VERSION", 1);
>>>>>>> word_list_study
            startActivity(intent);
        }

        else if(view==amgi){
<<<<<<< HEAD
            /*
            Intent intent = new Intent(getApplicationContext(), study_word.class);
            intent.putExtra("when", 1);
            startActivity(intent);*/
=======
            Intent intent = new Intent(getApplicationContext(), ListWord.class);
            intent.putExtra("LIST_VERSION", 2);
            startActivity(intent);
        }
        else if(view==miamgi){
            Intent intent = new Intent(getApplicationContext(), ListWord.class);
            intent.putExtra("LIST_VERSION", 3);
            startActivity(intent);
        }
        else if(view==odab){
            Intent intent = new Intent(getApplicationContext(), ListWord.class);
            intent.putExtra("LIST_VERSION", 4);
            startActivity(intent);
        }
        else if(view==test){
            Intent intent = new Intent(getApplicationContext(), TestSetting.class);
            startActivity(intent);
>>>>>>> word_list_study
        }

    }
}