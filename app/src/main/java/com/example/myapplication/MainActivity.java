package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import twitter4j.HttpClient;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> titles = new ArrayList<>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = findViewById(R.id.webView);
//        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient());
//        webView.loadUrl("https://twitter.com/search?q=%40AbdullahJanKha5");

        getTweets tweets = new getTweets();
        tweets.execute();
    }
    private class getTweets extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            System.setProperty("java.net.useSystemProxies", "true");
            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
                    .setOAuthConsumerKey("EunDT4tc3fIwnj8bcKPPN9peA")
                    .setOAuthConsumerSecret("Ns3qNzI6JHGfUFANKe61wU99OenPkhbyLOUf1ablmwsqdcSUPr ")
                    .setOAuthAccessToken("753587374173614081-94CS79O9lKy0PPtiprGdtF3JsSs9t3N")
                    .setOAuthAccessTokenSecret("aBAbe385Uz4byrqIY8C53nHs3eIG5M7iCl6CQGoScZEuI");
            TwitterFactory tf = new TwitterFactory(cb.build());
            twitter4j.Twitter twitter = tf.getInstance();
//            int i=1;
            try {
                List<twitter4j.Status> statusList = twitter.getHomeTimeline();
//                Log.i("Iteration", Integer.toString(i++));
                for (twitter4j.Status s : statusList) {
                    Log.i("User Name : ", s.getUser().getScreenName());
                    Log.i("Text : ", s.getText());
                }
            }
            catch (TwitterException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}




//    public class download extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected String doInBackground(String... strings) {
//
//            ConfigurationBuilder cb = new ConfigurationBuilder();
//            cb.setDebugEnabled(true)
//                    .setOAuthConsumerKey("SnV1ZqjRSbr6PR7iwlX8KoSMz")
//                    .setOAuthConsumerSecret("EC4I3zJzDooQ5pgKsCLeNLToSgqJUzhIR2rexIhniYPSXJXwvG ")
//                    .setOAuthAccessToken("753587374173614081-aWN6u4ggwGon7jADjJ7evxlbpmq5hZJ")
//                    .setOAuthAccessTokenSecret("jIm0u9tte2XzPv5QlzpKHNdqYnq38owdTQzDQbFcreYWz ");
//            TwitterFactory tf = new TwitterFactory(cb.build());
//            Twitter twitter = tf.getInstance();
//            Query query = new Query("Pakistan");
//            try {
//                QueryResult queryResult = twitter.search(query);
//                List<twitter4j.Status> status = queryResult.getTweets();
//
//                twitter4j.Status str = status.get(1);
//                Log.i("Tweet", str.getUser().getStatus().toString());
//            }
//            catch (TwitterException e) {
//                e.printStackTrace();
//            }

//            String result = "";
//            URL url;
//            HttpsURLConnection urlConnection = null;
//
//            try {
//                url = new URL(strings[0]);
//
//                urlConnection = (HttpsURLConnection) url.openConnection();
//                InputStream in = urlConnection.getInputStream();
//                InputStreamReader reader = new InputStreamReader(in);
//                int data = reader.read();
//
//                while(data!=-1){
//                    char current = (char) data;
//                    result+=current;
//                    data = reader.read();
//                }
//
//                Log.i("URLConnection", result);
//
//            }
//            catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//}


