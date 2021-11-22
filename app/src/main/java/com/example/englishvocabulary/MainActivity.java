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
    ArrayList<Word> array;
    TextView updateTest;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        array = new ArrayList<>();
        update(array);
        updateTest = findViewById(R.id.textview_getTest);
        updateTest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        TextView textView = (TextView) v;
        update(array);

        if (i <= array.size()-1){
            textView.setText(array.get(i).getKorean1());
            i++;
        }
    }
}