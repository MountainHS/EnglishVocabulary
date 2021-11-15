package com.example.firebasetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class AddWordActivity extends AppCompatActivity {

    EditText et_word, et_mean;
    Button btn_add;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);


        et_word = findViewById(R.id.et_word);
        et_mean = findViewById(R.id.et_mean);
        btn_add = findViewById(R.id.btn_save);

        db = FirebaseFirestore.getInstance();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String word = et_word.getText().toString();
                String mean = et_mean.getText().toString();
                et_word.setText("");
                et_mean.setText("");

                Word data = new Word(word, mean, false);

                db.collection("word").document(word)
                        .set(data)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(), "OO", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "XX", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}