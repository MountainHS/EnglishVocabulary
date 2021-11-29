package com.example.englishvocabulary;

import java.util.ArrayList;

public class Word {

    private String eng; //영단어
    private ArrayList<String> kor = new ArrayList<>(); //해석 최대 3개
    //kor.get(0) kor.get(1) kor.get(2)

    public Word(){
        kor.add("");
        kor.add("");
        kor.add("");
    }

    int when; //단어 위치
    private boolean isMem; //암기 여부
    private boolean isOdap; //오답 여부



    public String getEnglish() {
        return eng;
    }

    public void setEnglish(String eng) {
        this.eng = eng;
    }

    public ArrayList<String> getKoreanAll() {return kor;}

    public void setKorenAll(ArrayList<String> kor) {this.kor = kor;}

    public String getKorean1() {
        return kor.get(0);
    }

    public void setKorean1(String kor1) {
        this.kor.set(0,kor1);
    }

    public String getKorean2() {
        return kor.get(1);
    }

    public void setKorean2(String kor2) {
        this.kor.set(1,kor2);
    }

    public String getKorean3() {
        return kor.get(2);
    }

    public void setKorean3(String kor3) {
        this.kor.set(2,kor3);
    }

    public int getWhen() {
        return when;
    }

    public void setWhen(int when) {
        this.when = when;
    }

    public boolean getisMem() {
        return isMem;
    }

    public void setisMen(boolean isMem) {
        this.isMem = isMem;
    }

    public boolean getisOdap() {
        return isOdap;
    }

    public void setisOdap(boolean isOdap) {
        this.isOdap = isOdap;
    }
}