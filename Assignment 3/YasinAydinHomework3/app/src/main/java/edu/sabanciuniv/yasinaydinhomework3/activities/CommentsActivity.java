package edu.sabanciuniv.yasinaydinhomework3.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import edu.sabanciuniv.yasinaydinhomework3.R;
import edu.sabanciuniv.yasinaydinhomework3.adapters.CommentsAdapter;
import edu.sabanciuniv.yasinaydinhomework3.model.CommentItem;
import edu.sabanciuniv.yasinaydinhomework3.model.NewsItem;

public class CommentsActivity extends AppCompatActivity {

    ProgressDialog prgDialog;
    RecyclerView commentsRecView;
    NewsItem selectedNews;
    List<CommentItem> data;
    CommentsAdapter adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments_layout);
        getSupportActionBar().setTitle(R.string.comments);

        data = new ArrayList<>();

        selectedNews = (NewsItem) getIntent().getSerializableExtra("selectednews");
        commentsRecView = findViewById(R.id.commentsrec);


        adp = new CommentsAdapter(data, this);
        commentsRecView.setLayoutManager(new LinearLayoutManager(this));
        commentsRecView.setAdapter(adp);

        CommentsActivity.CommentsTask tsk = new CommentsActivity.CommentsTask();
        tsk.execute("http://94.138.207.51:8080/NewsApp/service/news/getcommentsbynewsid/" + selectedNews.getId());


        ActionBar currentBar = getSupportActionBar();
        currentBar.setHomeButtonEnabled(true);
        currentBar.setDisplayHomeAsUpEnabled(true);
        currentBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        CommentsTask tsk = new CommentsTask();
        tsk.execute("http://94.138.207.51:8080/NewsApp/service/news/getcommentsbynewsid/" + selectedNews.getId());

    }

    public class CommentsTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            prgDialog = new ProgressDialog(CommentsActivity.this);
            prgDialog.setTitle("Loading");
            prgDialog.setMessage("Please wait...");
            prgDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            prgDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            String urlStr = strings[0];
            StringBuilder buffer = new StringBuilder();

            try {
                URL url = new URL(urlStr);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return buffer.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            data.clear();

            try {
                JSONObject obj = new JSONObject(s);

                if (obj.getInt("serviceMessageCode") == 1) {

                    JSONArray arr = obj.getJSONArray("items");

                    for (int i = 0; i < arr.length(); i++) {

                        JSONObject current = (JSONObject)arr.get(i);

                        CommentItem item = new CommentItem(current.getInt("id"),
                                current.getString("name"),
                                current.getString("text"));

                        data.add(item);
                    }

                } else {
                    System.out.println("serviceMessageCode == 0 !!!!");
                }

                adp.notifyDataSetChanged();
                prgDialog.dismiss();

            } catch (JSONException e) {
                Log.e("DEV", String.valueOf(e));
                e.printStackTrace();
            }
        }
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
