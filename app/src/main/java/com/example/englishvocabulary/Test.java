package com.example.englishvocabulary;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.englishvocabulary.firestore.DatabaseControl;

import java.util.ArrayList;

public class Test extends AppCompatActivity implements View.OnClickListener {

    Intent intent;
    TextView alltext;
    TextView nowtext;
    TextView problemKoren1;
    TextView problemKoren2;
    TextView problemKoren3;
    EditText inputTestAnswer;
    Button checkAnswer;
    Button jumpAnswer;
    Button endTest;

    Dialog addWrongWordDialog;
    ArrayList<Word> testWord;

    int manyTest;
    int i; //한 문제 씩 증가
    int correct;
    int wrong;
    boolean buttonEnd;
    boolean dialogEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        intent = getIntent();
        testWord = (ArrayList<Word>) intent.getSerializableExtra("TestArray");
        manyTest = intent.getIntExtra("ManyTestWord", 0);

        alltext = findViewById(R.id.textview_allTest);
        alltext.setText(manyTest+"");
        i = 1;
        correct = 0;
        wrong = 0;
        nowtext = findViewById(R.id.textview_progressTest);
        nowtext.setText(i+"");

        problemKoren1 = findViewById(R.id.textview_problemKorean1);
        problemKoren2 = findViewById(R.id.textview_problemKorean2);
        problemKoren3 = findViewById(R.id.textview_problemKorean3);
        inputTestAnswer = findViewById(R.id.edittext_inputTestAnswer);

        //다이얼로그 세팅
        addWrongWordDialog = new Dialog(Test.this);
        addWrongWordDialog.requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        addWrongWordDialog.setContentView(R.layout.activity_test_wrong);

        ///////////////////////////여기까지가 기본 세팅//////////////////////////
        checkAnswer = findViewById(R.id.button_checkAnswer);
        jumpAnswer = findViewById(R.id.button_jumpAnswer);
        endTest = findViewById(R.id.button_endTest);
        buttonEnd = false;

        checkAnswer.setOnClickListener(this);
        jumpAnswer.setOnClickListener(this);
        endTest.setOnClickListener(this);

    /*
    일단 ArrayList에 저장한 후, 현재 문제 변수 i, 정답 변수, 오답 변수, 전체 문제 변수=intent, 로 정한 다음
    한 문제 풀고 나면, settext초기화 하고, 변수 늘리고 등등등 이용해서 종료하기 버튼을 누르거나 i==intent면 종료 화면으로 이동
*/

        problemKoren1.setText(testWord.get(i-1).getKorean1());
        problemKoren2.setText(testWord.get(i-1).getKorean2());
        problemKoren3.setText(testWord.get(i-1).getKorean3());


    }


    @Override
    public void onClick(View view) {
        if(view == checkAnswer){ //답 비교
            String check = inputTestAnswer.getText().toString();
            if(check.equals(testWord.get(i-1).getEnglish())){
                correct++;
            }
            else{
                makeDialog(); //wrong++를 여기서 하면 마지막 문제 틀렸을 때 바로 넘김
                wrong++;
            }
        }

        else if(view == jumpAnswer) { //답 건너뛰기
            makeDialog();
            wrong++;
        }

        else if(view == endTest){ //끝내기
            buttonEnd = true;
            i-=1; //이미 올라간 문제X
        }

        if(i<manyTest && buttonEnd == false){
            i+=1;
            nowtext.setText(i+"");
            inputTestAnswer.setText("");
            problemKoren1.setText(testWord.get(i-1).getKorean1());
            problemKoren2.setText(testWord.get(i-1).getKorean2());
            problemKoren3.setText(testWord.get(i-1).getKorean3());
        }
        else{
            Intent resultIntent = new Intent(getApplicationContext(), TestResult.class);
            resultIntent.putExtra("solveTest", i); //푼 문제
            resultIntent.putExtra("correctTest", correct); //맞은 수
            resultIntent.putExtra("wrongTest", wrong); //틀린 수
            startActivity(resultIntent);

        }
    }

    private void makeDialog() {
        addWrongWordDialog.show();
        // *주의할 점: findViewById()를 쓸 때는 -> 앞에 반드시 다이얼로그 이름을 붙여야 한다.

        TextView answerText = addWrongWordDialog.findViewById(R.id.textview_thisIsAnswer);
        Button addOdapYes = addWrongWordDialog.findViewById(R.id.button_addOdapYes);;
        Button addOdapNo = addWrongWordDialog.findViewById(R.id.button_addOdapNo);;

        answerText.setText(testWord.get(i-1).getEnglish());
        addOdapYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //다이얼로그를 띄우고 난 뒤 바로 다음으로 넘어가서 문제....
                DatabaseControl.addWord("OdapVoca", testWord.get(i-2));
                Toast.makeText(getApplicationContext(), testWord.get(i-2).getEnglish() + "추가 완료", Toast.LENGTH_SHORT).show();

                addWrongWordDialog.dismiss();
            }
        });
        addOdapNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addWrongWordDialog.dismiss();
            }
        });



    }
}
