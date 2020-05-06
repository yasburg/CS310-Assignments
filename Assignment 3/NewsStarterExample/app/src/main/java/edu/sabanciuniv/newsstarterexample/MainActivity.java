package edu.sabanciuniv.newsstarterexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import edu.sabanciuniv.newsstarterexample.model.CommentItem;
import edu.sabanciuniv.newsstarterexample.model.NewsItem;

public class MainActivity extends AppCompatActivity {

    RecyclerView newsRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsRecView = findViewById(R.id.newsrec);
        NewsAdapter adp = new NewsAdapter(NewsItem.getAllNews(), this, new NewsAdapter.NewsItemClickListener() {
            @Override
            public void newItemClicked(NewsItem selectedNewsItem) {
                Toast.makeText(MainActivity.this, selectedNewsItem.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        newsRecView.setLayoutManager(new LinearLayoutManager(this));
        newsRecView.setAdapter(adp);

        List<NewsItem> items= NewsItem.getAllNews();
        Log.i("DEV","Total of " + items.size() + " news exist");


        List<CommentItem> comments = NewsItem.getCommentsByNewsId(0);
        Log.i("DEV","Total of " + comments.size() + " comments exist for newsid 0");
    }
}
