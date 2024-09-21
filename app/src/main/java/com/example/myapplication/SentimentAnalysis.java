package com.example.myapplication;

import androidx.annotation.NonNull;

public class SentimentAnalysis {

    public String analyzeSentiment (String tweetText){
        return tweetText.toLowerCase().contains("happy") ? "Positive" : "Negative";
    }
}
