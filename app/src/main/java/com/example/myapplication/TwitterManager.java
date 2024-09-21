package com.example.myapplication;

import android.content.res.Configuration;

import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterManager {

    private Twitter twitter;

    public TwitterManager() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerSecret("obVLhuASMgStiH1OMCAHFII0UHCQpdrbNOMxZBCDdcuQnXb5Ya")
                .setOAuthConsumerKey("obVLhuASMgStiH1OMCAHFII0UHCQpdrbNOMxZBCDdcuQnXb5Ya")
                .setOAuthAccessToken("1442420962125701128-DJ3LC8wKlRIDY9kEWCWhLCeq5EKK11")
                .setOAuthAccessTokenSecret("2ieNZZimuF6dvyWPMEEdoI1wpvocXBJLJrwFWdFX9jdQo");

        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
    }

    public List<Status> searchTweets(String query) {
        try {
            Query searchQuery = new Query();
            QueryResult result = twitter.search(searchQuery);
            return result.getTweets();
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        return null;
    }
}

