package com.example.my_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

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

        myword = (Button) findViewById(R.id.myword_button);
        amgi = (Button) findViewById(R.id.amgi_button);
        miamgi = (Button) findViewById(R.id.miamgi_button);
        odab = (Button) findViewById(R.id.odab_button);
        test = (Button) findViewById(R.id.test_button);
        game = (Button) findViewById(R.id.game_button);
        drawerhandle = (Button) findViewById(R.id.draweronoff_button);
        close_drawer = (Button)findViewById(R.id.close_drawer_button);

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
            DrawerLayout drawer = findViewById(R.id.mainActivity_main);
            if(!drawer.isDrawerOpen(Gravity.LEFT)){
                drawer.openDrawer(Gravity.LEFT);
                }
            }
        else if(view==myword){
            Intent intent = new Intent(getApplicationContext(), list_word.class);
            startActivity(intent);
        }

        else if(view==amgi){
            /*
            Intent intent = new Intent(getApplicationContext(), study_word.class);
            intent.putExtra("when", 1);
            startActivity(intent);*/
        }

        }


    }
