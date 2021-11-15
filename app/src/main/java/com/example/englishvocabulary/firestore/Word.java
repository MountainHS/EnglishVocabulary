package com.example.firebasetest;

public class Word {
    private String word;
    private String mean;
    private boolean isMem;

    public Word() {
    }

    public Word(String word, String mean, boolean isMem){
        this.word = word;
        this.mean = mean;
        this.isMem = isMem;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public boolean getIsMem() {
        return isMem;
    }

    public void setResId(boolean isMem) {
        this.isMem = isMem;
    }
}
