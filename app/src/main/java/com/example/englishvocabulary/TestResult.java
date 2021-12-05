package com.example.englishvocabulary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TestResult extends AppCompatActivity {

    TextView score;
    TextView solveTest;
    TextView correctTest;
    TextView wrongTest;
    Button gomain;

    int solve;
    int correct;
    int wrong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result);

        Intent intent = getIntent();
        solve = intent.getIntExtra("solveTest", 0);
        correct = intent.getIntExtra("correctTest", 0);
        wrong = intent.getIntExtra("wrongTest", 0);

        score = findViewById(R.id.textview_testScore);
        solveTest = findViewById(R.id.textview_solveAllProblem);
        correctTest = findViewById(R.id.textview_solveCorrectProblem);
        wrongTest = findViewById(R.id.textview_solveWrongProblem);
        gomain = findViewById(R.id.button_testOverGoMain);

        solveTest.setText(solve+"");
        correctTest.setText(correct+"");
        wrongTest.setText(wrong+"");
        double testScore = ((double)correct / solve)*100;
        String testScore_fin = String.format("%.0f",testScore);
        score.setText(testScore_fin);

        gomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gomainIntent = new Intent(getApplicationContext(), MainActivity.class);
                gomainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(gomainIntent);
            }
        });

    }
}