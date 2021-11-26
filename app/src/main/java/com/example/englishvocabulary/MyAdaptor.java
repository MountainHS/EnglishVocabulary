package com.example.englishvocabulary;

import androidx.viewpager.widget.PagerAdapter;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyAdaptor extends PagerAdapter {
    Context context;

    ArrayList<Word> word = new ArrayList<>(); //Word 단어 저장

    ArrayList<String> eng = new ArrayList<>();
    ArrayList<String> kor1 = new ArrayList<>();
    ArrayList<String> kor2 = new ArrayList<>();
    ArrayList<String> kor3 = new ArrayList<>();

    String whenString; //when 인자 대신 새로운 방법
    int when;


    MyAdaptor(Context context, ArrayList<Word> word, String whenString) {
        this.context = context;
        this.word = word;
        this.whenString = whenString;
    }

    private String engdColor()
    {
        if(TextStatus.engStatus==false)
            return "#FF000000";
        else
            return "#d8edf3";
    }
    private String korColor()
    {
        if(TextStatus.korStatus == false)
            return "#FF000000";
        else
            return "#ffffff";
    }

    @Override
    public int getCount() {
        return word.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater inflater = LayoutInflater.from(context);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.activity_study_word_show, container, false);
        TextView english_word = (TextView)layout.findViewById(R.id.textview_english_word);
        TextView korean_word1 = (TextView)layout.findViewById(R.id.textview_korean_word1);
        TextView korean_word2 = (TextView)layout.findViewById(R.id.textview_korean_word2);
        TextView korean_word3 = (TextView)layout.findViewById(R.id.textview_korean_word3);


        for(int i=0; i<word.size(); i++){
            if(word.get(i).getEnglish().equals(whenString)){
                when = i;
                break;
            }
        }
        String text = "클릭한 문장 " + whenString + "when값 " + when;
       // Toast.makeText(context.getApplicationContext(), text, Toast.LENGTH_SHORT).show();


        //원래는 eng의 position의 get
        //position+=when;
        english_word.setText(word.get(position).getEnglish());
        korean_word1.setText(word.get(position).getKorean1());
        korean_word2.setText(word.get(position).getKorean2());
        korean_word3.setText(word.get(position).getKorean3());

        //여기서 +1 or -1로 어떻게 이동하냐 이말이다...


        //LinearLayout korean_word_view = (LinearLayout)layout.findViewById(R.id.korean_word_view);
        Button english_show_button = (Button)layout.findViewById(R.id.button_english_show);
        Button korean_show_button = (Button)layout.findViewById(R.id.button_korean_show);

        english_show_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextStatus.engStatus = !TextStatus.engStatus;
                english_word.setTextColor(Color.parseColor(engdColor()));
            }
        });


        korean_show_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextStatus.korStatus = !TextStatus.korStatus;
                korean_word1.setTextColor(Color.parseColor(korColor()));
                korean_word2.setTextColor(Color.parseColor(korColor()));
                korean_word3.setTextColor(Color.parseColor(korColor()));
            }
        });



        container.addView(layout, 0);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (View)object;
    }

    @Override
    public void notifyDataSetChanged(){
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object){
        return POSITION_NONE;
    }

}
