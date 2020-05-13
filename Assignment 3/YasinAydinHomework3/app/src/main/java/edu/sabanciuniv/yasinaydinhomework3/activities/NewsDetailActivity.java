package edu.sabanciuniv.yasinaydinhomework3.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;

import edu.sabanciuniv.yasinaydinhomework3.ImageDownloadTask;
import edu.sabanciuniv.yasinaydinhomework3.R;
import edu.sabanciuniv.yasinaydinhomework3.model.NewsItem;

public class NewsDetailActivity extends AppCompatActivity {

    NewsItem selectedNews;
    TextView txtDetailTitle;
    TextView txtDetailDate;
    ImageView imgDetailNews;
    TextView txtDetailNews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail_layout);

        selectedNews = (NewsItem) getIntent().getSerializableExtra("selectednews");
        txtDetailTitle = findViewById(R.id.txtdetailtitle);
        txtDetailDate = findViewById(R.id.txtdetaildate);
        imgDetailNews = findViewById(R.id.imgnews);
        txtDetailNews = findViewById(R.id.txtdetailtext);

        txtDetailTitle.setText(selectedNews.getTitle());
        txtDetailDate.setText(new SimpleDateFormat("dd/MM/yyy").format(selectedNews.getNewsDate()));

        if (selectedNews.getBitmap() == null) {
            new ImageDownloadTask(imgDetailNews).execute(selectedNews);

        } else {
            imgDetailNews.setImageBitmap(selectedNews.getBitmap());
        }

        txtDetailNews.setText(selectedNews.getText());



        getSupportActionBar().setTitle(R.string.news_details);

        ActionBar currentBar = getSupportActionBar();
        currentBar.setHomeButtonEnabled(true);
        currentBar.setDisplayHomeAsUpEnabled(true);
        currentBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.news_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        else if (item.getItemId() == R.id.newscomment) {
            Intent i = new Intent(this, CommentsActivity.class);
            i.putExtra("selectednews", selectedNews);
            startActivity(i);
        }
        return true;
    }
}
