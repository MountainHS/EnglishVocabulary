package com.example.englishvocabulary.firestore;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.englishvocabulary.Word;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DatabaseControl extends AppCompatActivity{
    public static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static Query queryResult;
    boolean fileReadPermission;
    boolean fileWritePermission;

    public interface OnGetDataListener{
        public void OnSuccess(ArrayList<Word> fetchedWordList);
    }

    public void addWord(String collectionName, Word word){ //파이어베이스에 단어 추가
        Map<String, Object> newWord = new HashMap<>();
        newWord.put("english", word.getEnglish());
        newWord.put("korean", word.getKoreanAll());
        newWord.put("isMem", word.getisMem());
        newWord.put("isOdap", word.getisOdap());

        CollectionReference targetVoca = db.collection(collectionName);
        targetVoca.document(word.getEnglish())
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

    public void queryOrder(String collectionName, String standard, boolean isAsc){
        CollectionReference targetVoca = db.collection(collectionName);

        if (isAsc == true){
            queryResult = targetVoca.orderBy(standard, Query.Direction.ASCENDING);
        }
        else {
            queryResult = targetVoca.orderBy(standard, Query.Direction.DESCENDING);
        }
//        return queryResult;
    }

    public void update(String collectionName, OnGetDataListener listener){
        CollectionReference targetVoca = db.collection(collectionName);
        ArrayList<Word> wordList = new ArrayList<>();
        targetVoca.orderBy("korean").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                wordList.clear();
                for(QueryDocumentSnapshot doc : task.getResult()){
                    Log.d("key:", doc.getId() + " value:" + doc.getData());
                    Word word = doc.toObject(Word.class);
                    wordList.add(word);
                    wordList.get(wordList.size()-1).setKorenAll((ArrayList<String>) doc.getData().get("korean"));
                }
                listener.OnSuccess(wordList);
            }
        });
    }

    public void update(OnGetDataListener listener){
        ArrayList<Word> wordList = new ArrayList<>();
        queryResult.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                wordList.clear();
                for(QueryDocumentSnapshot doc : task.getResult()){
                    Log.d("key:", doc.getId() + " value:" + doc.getData());
                    Word word = doc.toObject(Word.class);
                    wordList.add(word);
                    wordList.get(wordList.size()-1).setKorenAll((ArrayList<String>) doc.getData().get("korean"));
                }
                listener.OnSuccess(wordList);
            }
        });
    }

    public void storagePermissionCheck(Context activity){
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            fileReadPermission = true;
        }
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            fileWritePermission = true;
        }

        if (!fileReadPermission || !fileWritePermission) {
            ActivityCompat.requestPermissions((Activity) activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 200 && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                fileReadPermission = true;
            if (grantResults[1] == PackageManager.PERMISSION_GRANTED)
                fileWritePermission = true;
        }
    }

    public void uploadVocabularyDataSet(String collectionName, String filePath){
        try{
            FileReader vocaDataSet = new FileReader(filePath);
            StringBuffer row = new StringBuffer();
//            String [] keyList = {"english", "korean"};
            String english;
            ArrayList<String> korean;
            int c;
            while (true) {
                while ((c = vocaDataSet.read()) != '\n' && c != -1) {
                    row.append(c);
                }

                if (c == -1){
                    break;
                }

                String[] parsedData = new String(row).split("\t");
                String [] koreanList = parsedData[1].split(",");
                english = parsedData[0];
                for (String kor : koreanList){
                    kor = kor.trim();
                }
                korean = new ArrayList<>(Arrays.asList(koreanList));
                Word newWord = new Word();
                newWord.setKorenAll(korean);
                newWord.setEnglish(english);
                newWord.setisMen(true);
                newWord.setisOdap(false);

                addWord(collectionName, newWord);
            }
        } catch(FileNotFoundException e){
            Log.d(TAG, "파일을 열 수 없음");
        } catch(IOException e){
            Log.d(TAG, "입출력 오류");
        }
    }
}
