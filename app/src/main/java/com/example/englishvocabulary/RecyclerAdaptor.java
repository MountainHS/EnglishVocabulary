package com.example.englishvocabulary;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import android.content.Intent;
import android.widget.Toast;

import com.example.englishvocabulary.firestore.DatabaseControl;

//일단 어뎁터로 연속으로 보여줄 클래스를 정하기 (배열에 IN)
//그리고 해당 배열에 담긴 정보들을 화면상에 출력 - ItemViewHolder



public class RecyclerAdaptor extends RecyclerView.Adapter<RecyclerAdaptor.ItemViewHolder> {
    //extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>
    ArrayList<Word> eng_kor_set = new ArrayList<>(); //영어-한글-한글-한글-위치 set
    Context context; // 어뎁터 안에서 Intent 할 때
    int whatListSelect;

    RecyclerAdaptor(){}

    //ListWord에서 어느 리스트 선택했는지 intent로 전달하기 위해 사용
    RecyclerAdaptor(int whatListSelect) {
        this.whatListSelect = whatListSelect;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //layoutInflater를 이용해 list_word_show.xml를 inflate 시킴 (반복적 보여줄 거)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_word_show, parent, false);
        context = parent.getContext();
        return new ItemViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.
        holder.onBind(eng_kor_set.get(position));
    }

    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return eng_kor_set.size();
    }

    void addItem(Word data) {
        // 외부에서 item을 추가시킬 함수입니다.
        eng_kor_set.add(data);

    }


    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView eng;
        private TextView kor1;
        private TextView kor2;
        private TextView kor3;
        private Word data;
        private ArrayList<Word> sendData;
        private LinearLayout linearLayout;


        ItemViewHolder(View itemView) {
            super(itemView);

            eng = itemView.findViewById(R.id.textview_english_word_list);
            kor1 = itemView.findViewById(R.id.textview_korean_word_list1);
            kor2 = itemView.findViewById(R.id.textview_korean_word_list2);
            kor3 = itemView.findViewById(R.id.textview_korean_word_list3);
            linearLayout = itemView.findViewById(R.id.layout_word_list_linearlayout);
        }

        void onBind(Word data) {
            this.data = data;


            eng.setText(data.getEnglish());
            kor1.setText(data.getKorean1());
            kor2.setText(data.getKorean2());
            kor3.setText(data.getKorean3());

            eng.setOnClickListener(this);
            kor1.setOnClickListener(this);
            kor2.setOnClickListener(this);
            kor3.setOnClickListener(this);
            linearLayout.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            String texting = "눌렀어" + data.getWhen();
            Intent intent = new Intent(context.getApplicationContext(), StudyWord.class);
            switch (view.getId()) {

                case R.id.layout_word_list_linearlayout:
                    intent = new Intent(context.getApplicationContext(), StudyWord.class);
                    intent.putExtra("when", data.getEnglish());
                    intent.putExtra("whatListSelect", whatListSelect);
                    context.startActivity(intent);
                    break;

                case R.id.textview_english_word_list:
                    intent = new Intent(context.getApplicationContext(), StudyWord.class);
                    intent.putExtra("when", data.getEnglish());
                    intent.putExtra("whatListSelect", whatListSelect);
                    context.startActivity(intent);
                    break;

                case R.id.textview_korean_word_list1:
                    intent = new Intent(context.getApplicationContext(), StudyWord.class);
                    intent.putExtra("when", data.getEnglish());
                    intent.putExtra("whatListSelect", whatListSelect);
                    context.startActivity(intent);
                    break;

                case R.id.textview_korean_word_list2:
                    intent = new Intent(context.getApplicationContext(), StudyWord.class);
                    intent.putExtra("when", data.getEnglish());
                    intent.putExtra("whatListSelect", whatListSelect);
                    context.startActivity(intent);
                    break;

                case R.id.textview_korean_word_list3:
                    intent = new Intent(context.getApplicationContext(), StudyWord.class);
                    intent.putExtra("when", data.getEnglish());
                    intent.putExtra("whatListSelect", whatListSelect);
                    context.startActivity(intent);
                    break;
            }
        }
    }
}