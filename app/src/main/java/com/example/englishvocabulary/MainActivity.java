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
//    // databaseControl test start
//    boolean fileReadPermission;
//    boolean fileWritePermission;
//    DatabaseControl dbC;
//    private static final String TAG = "MyActivity";
//    // end
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
//        // databaseControl test start
//        dbC = new DatabaseControl();
//        dbC.storagePermissionCheck(this);
//        // end

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
        if(view==drawerhandle){
            DrawerLayout drawer = findViewById(R.id.activity_main);
            if(!drawer.isDrawerOpen(Gravity.LEFT)){
                drawer.openDrawer(Gravity.LEFT);
            }
        }
        else if(view==myword){
//            //databaseControl test start
//            String parentDir = Environment.getExternalStorageDirectory().getAbsolutePath();
//            File testFile = new File(parentDir + "/test.txt");
//            Log.d(TAG, "file test " + testFile.getAbsolutePath());
//            try{
//                if (!new File(parentDir).exists()){
//                    Log.d(TAG, "!!!!!!!!!!!!!!!!!!!");
//                }
//                if (!testFile.exists()){
//                    testFile.createNewFile();
//                }
//                FileWriter testWriter = new FileWriter(testFile);
//                testWriter.write("culture\t문화, 교양\n" +
//                        "experience\t경험, 경험하다");
//                Log.d(TAG, "write test");
//                testWriter.close();
//            } catch(FileNotFoundException e){
//                Log.d(TAG, "파일을 열 수 없음");
//            } catch(IOException e){
//                Log.d(TAG, "입출력 오류");
//            }
//            dbC.uploadVocabularyDataSet("testVoca", testFile.getAbsolutePath());
//            //end
            Intent intent = new Intent(getApplicationContext(), ListWord.class);

            intent.putExtra("LIST_VERSION", 1);
            startActivity(intent);
        }

        else if(view==amgi){
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