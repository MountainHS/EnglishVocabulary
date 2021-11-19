package com.example.englishvocabulary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

public class Drawer extends AppCompatActivity implements View.OnClickListener{

    Button close_drawer;
    Button gomain;
    Button gowordlist;
    Button gogame;
    Button gotest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        close_drawer = (Button)findViewById(R.id.button_close_drawer);
        gomain = (Button)findViewById(R.id.button_gomain);
        gowordlist = (Button)findViewById(R.id.button_gowordlist);
        gogame = (Button)findViewById(R.id.button_gogame);
        gotest = (Button)findViewById(R.id.button_gotest);

        close_drawer.setOnClickListener(this);
        gomain.setOnClickListener(this);
        gowordlist.setOnClickListener(this);
        gogame.setOnClickListener(this);
        gotest.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view==close_drawer){
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.activity_main);
            if(drawer.isDrawerOpen(Gravity.LEFT)){
                drawer.closeDrawer(Gravity.LEFT);
            }
        }
        else if(view==gomain){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }
}