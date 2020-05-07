package edu.sabanciuniv.newsstarterexample;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;

import edu.sabanciuniv.newsstarterexample.model.NewsItem;

public class NewsDetailActivity extends AppCompatActivity {

    NewsItem selectedNews;
    TextView txtDetailTitle;
    TextView txtDetailDate;
    ImageView imgDetailNews;
    TextView txtDetailNews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        selectedNews = (NewsItem) getIntent().getSerializableExtra("selectednews");
        txtDetailTitle = findViewById(R.id.txtdetailtitle);
        txtDetailDate = findViewById(R.id.txtdetaildate);
        imgDetailNews = findViewById(R.id.imgnews);
        txtDetailNews = findViewById(R.id.txtdetailtext);

        txtDetailTitle.setText(selectedNews.getTitle());
        txtDetailDate.setText(new SimpleDateFormat("dd/MM/yyy").format(selectedNews.getNewsDate()));
        imgDetailNews.setImageResource(selectedNews.getImageId());
        txtDetailNews.setText(selectedNews.getText());

        getSupportActionBar().setTitle(R.string.news_details);

    }
}
