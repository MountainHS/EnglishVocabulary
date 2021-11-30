package com.example.englishvocabulary;

import static com.example.englishvocabulary.firestore.DatabaseControl.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
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
    LinearLayout myword;
    LinearLayout myword2;
    LinearLayout myword3;

    Button odab;
    Button test;
    Button game;
    Button drawerhandle;
    Button close_drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        // databaseControl test start
//        dbC = new DatabaseControl();
//        dbC.storagePermissionCheck(this);
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
        drawerhandle = (Button) findViewById(R.id.button_openDrawerWithMain);
        close_drawer = (Button)findViewById(R.id.button_close_drawer);

        myword.setOnClickListener(this);
        myword2.setOnClickListener(this);
        myword3.setOnClickListener(this);
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