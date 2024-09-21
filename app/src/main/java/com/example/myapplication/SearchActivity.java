package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Status;

public class SearchActivity extends AppCompatActivity {

    private TwitterManager twitterManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        twitterManager = new TwitterManager();

        EditText searchQuery = findViewById(R.id.searchQuery);
        Button button = findViewById(R.id.searchButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = searchQuery.getText().toString();

                if (query.isEmpty()){
                    Toast.makeText(SearchActivity.this, "Search Box is empty", Toast.LENGTH_SHORT).show();
                } else {
                    searchTweet(query);
                }
            }
        });
    }

    private void searchTweet(String query) {

        new Thread(() -> {
            List<Status> tweet = twitterManager.searchTweets(query);
            if(tweet != null && !tweet.isEmpty()){
                ArrayList<String> tweetText = new ArrayList<>();
                for (Status tweets : tweet){
                    tweetText.add(tweets.getText());
                }
                Intent intent = new Intent(SearchActivity.this, SentimentActivity.class);
                intent.putStringArrayListExtra("tweets", tweetText);
                runOnUiThread(() -> startActivity(intent));
            }else {
                runOnUiThread(() -> Toast.makeText(SearchActivity.this, "No tweets found", Toast.LENGTH_SHORT).show());
            }
        }).start();

    }
}