package edu.sabanciuniv.yasinaydinhomework3;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import edu.sabanciuniv.yasinaydinhomework3.model.NewsItem;

public class CommentsActivity extends AppCompatActivity {

    RecyclerView commentsRecView;
    NewsItem selectedNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        selectedNews = (NewsItem) getIntent().getSerializableExtra("selectednews");
        commentsRecView = findViewById(R.id.commentsrec);

        CommentsAdapter adp = new CommentsAdapter(NewsItem.getCommentsByNewsId(selectedNews.getId()), this);
        commentsRecView.setLayoutManager(new LinearLayoutManager(this));
        commentsRecView.setAdapter(adp);

        getSupportActionBar().setTitle(R.string.post_comment);

        ActionBar currentBar = getSupportActionBar();
        currentBar.setHomeButtonEnabled(true);
        currentBar.setDisplayHomeAsUpEnabled(true);
        currentBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.comments_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        else if (item.getItemId() == R.id.sendcomment) {
            Intent i = new Intent(this, PostCommentActivity.class);
            i.putExtra("selectednews", selectedNews);
            startActivity(i);
        }
        return true;
    }
}
