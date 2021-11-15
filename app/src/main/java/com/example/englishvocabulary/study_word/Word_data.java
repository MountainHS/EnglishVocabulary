package com.example.my_project;

import java.util.ArrayList;

public class Word_data {

    private String eng; //영단어
    private String kor1;
    private String kor2;
    private String kor3;

    private ArrayList<String> kor; //해석 최대 3개
    //kor.get(0) kor.get(1) kor.get(2)

    int when; //단어 위치
    private boolean isMem; //암기 여부



    public String getEnglish() {
        return eng;
    }

    public void setEnglish(String eng) {
        this.eng = eng;
    }

    public String getKorean1() {
        return kor1;
    }

    public void setKorean1(String kor1) {
        this.kor1 = kor1;
    }

    public String getKorean2() {
        return kor2;
    }

    public void setKorean2(String kor2) {
        this.kor2 = kor2;
    }

    public String getKorean3() {
        return kor3;
    }

    public void setKorean3(String kor3) {
        this.kor3 = kor3;
    }

    public int getWhen() {
        return when;
    }

    public void setWhen(int when) {
        this.when = when;
    }

}
