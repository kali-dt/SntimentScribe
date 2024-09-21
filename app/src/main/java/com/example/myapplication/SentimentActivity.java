package com.example.myapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class SentimentActivity extends AppCompatActivity {

    private SentimentAnalysis sentimentAnalysis;
    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentiment);

        ListView listView = findViewById(R.id.tweetsList);
        pieChart = findViewById(R.id.pieChart);

        ArrayList<String> tweets = new ArrayList<>();
        tweets.add("i Love This");
        tweets.add("This is so sad");
        tweets.add("I am feeling so angry about this");

        setupPieChart(70, 20, 10); // Example: 70% positive, 20% negative, 10% neutral


        sentimentAnalysis = new SentimentAnalysis();
        ArrayList<String> tweetTexts = getIntent().getStringArrayListExtra("tweets");

        if (tweetTexts != null){
            List<String> analyedTweets = new ArrayList<>();
            for (String tweet : tweetTexts) {
                String sentiment = sentimentAnalysis.analyzeSentiment(tweet);
                analyedTweets.add(tweet + "- Sentiment: " + sentiment);
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, analyedTweets);
            listView.setAdapter(adapter);

        }
    }

    private void setupPieChart(int positive, int negative, int neutral) {
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(positive, "Positive"));
        entries.add(new PieEntry(negative, "Negative"));
        entries.add(new PieEntry(neutral, "Neutral"));

        PieDataSet dataSet = new PieDataSet(entries, "Sentimental analysis");
        dataSet.setColors(new int[]{R.color.colorPositive, R.color.colorNegative, R.color.colorNeutral});
        PieData data = new PieData(dataSet);

        pieChart.setData(data);
        pieChart.invalidate();
    }
}