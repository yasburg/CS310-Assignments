package edu.sabanciuniv.newsstarterexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import edu.sabanciuniv.newsstarterexample.model.CommentItem;
import edu.sabanciuniv.newsstarterexample.model.NewsItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<NewsItem> items= NewsItem.getAllNews();

        Log.i("DEV","Total of " + items.size() + " news exist");


        List<CommentItem> comments = NewsItem.getCommentsByNewsId(0);
        Log.i("DEV","Total of " + comments.size() + " comments exist for newsid 0");
    }
}
