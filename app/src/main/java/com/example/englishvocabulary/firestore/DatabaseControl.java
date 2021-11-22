package com.example.englishvocabulary.firestore;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.englishvocabulary.Word;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DatabaseControl extends AppCompatActivity {
    public static FirebaseFirestore db = FirebaseFirestore.getInstance();;
    public static CollectionReference engVoca = db.collection("EngVoca");

    // 이미 있는 단어가 있을때 구현해야함
    //// 뜻이 다를 경우 기존 문서에 뜻 추가
    //// 뜻까지 다 같은 경우 종료
    public static void addWord(Word word){ //파이어베이스에 단어 추가
        Map<String, Object> newWord = new HashMap<>();
        newWord.put("english", word.getEnglish());
        newWord.put("korean", word.getKoreanAll());
        newWord.put("memorize need", word.getisMem());
        newWord.put("recent test result", word.getisOdap());

        engVoca.document("test")
                .set(newWord)
            .addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void documentReference) {
                    Log.d(TAG, "set method test");
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.w(TAG, "Error writing document", e);
                }
            });
    }

    /*
    public static ArrayList<Word> update(){
        CollectionReference colRef = db.collection("word");
        ArrayList<Word> word_list = new ArrayList<>();

//        word_list.clear();
        colRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(DocumentSnapshot doc : queryDocumentSnapshots){
                    Log.d("word_list", doc.toObject(Word.class).getEnglish() + " " + doc.toObject(Word.class).getMean());
                    word_list.add(doc.toObject(Word.class));
                }

            }
        });
    }
*/
//    public
}
