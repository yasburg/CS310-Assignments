package edu.sabanciuniv.yasinaydinhomework3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

import edu.sabanciuniv.yasinaydinhomework3.model.CommentItem;
import edu.sabanciuniv.yasinaydinhomework3.model.NewsItem;

public class MainActivity extends AppCompatActivity implements NewsAdapter.NewsItemClickListener{

    RecyclerView newsRecView;
    Spinner spFilters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Boiler plate code:
        newsRecView = findViewById(R.id.newsrec);
        spFilters = findViewById(R.id.spfilter);

        String[] filters = getResources().getStringArray(R.array.filters);

        ArrayAdapter<String> adp1 =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, filters);

        spFilters.setAdapter(adp1);

        NewsAdapter adp2 = new NewsAdapter(NewsItem.getAllNews(), this, this);
        newsRecView.setLayoutManager(new LinearLayoutManager(this));
        newsRecView.setAdapter(adp2);


        List<NewsItem> items= NewsItem.getAllNews();
        Log.i("DEV","Total of " + items.size() + " news exist");


        List<CommentItem> comments = NewsItem.getCommentsByNewsId(0);
        Log.i("DEV","Total of " + comments.size() + " comments exist for newsid 0");
    }

    @Override
    public void newsItemClicked(NewsItem selectedNewsItem) {
        Intent i = new Intent(this, NewsDetailActivity.class);
        i.putExtra("selectednews", selectedNewsItem);
        startActivity(i);
    }
}
