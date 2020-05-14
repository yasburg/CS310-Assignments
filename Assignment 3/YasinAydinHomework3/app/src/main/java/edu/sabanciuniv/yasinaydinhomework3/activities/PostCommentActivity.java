package edu.sabanciuniv.yasinaydinhomework3.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import edu.sabanciuniv.yasinaydinhomework3.R;
import edu.sabanciuniv.yasinaydinhomework3.model.NewsItem;

public class PostCommentActivity extends AppCompatActivity {

    NewsItem selectedNews;
    EditText edittxtfullname;
    EditText edittxtmessage;
    Button btnpost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_comment_layout);
        getSupportActionBar().setTitle(R.string.post_comment);

        selectedNews = (NewsItem) getIntent().getSerializableExtra("selectednews");
        edittxtfullname = findViewById(R.id.edittxtfullname);
        edittxtmessage = findViewById(R.id.edittxtmessage);
        btnpost = findViewById(R.id.btnpost);

        ActionBar currentBar = getSupportActionBar();
        currentBar.setHomeButtonEnabled(true);
        currentBar.setDisplayHomeAsUpEnabled(true);
        currentBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }

    public void toPostClicked(View v) {

        PostCommentActivity.PostCommentTask tsk = new PostCommentActivity.PostCommentTask();
        tsk.execute("http://94.138.207.51:8080/NewsApp/service/news/savecomment");


    }

    class PostCommentTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String urlStr = strings[0];
            StringBuilder buffer = new StringBuilder();
            JSONObject obj = new JSONObject();

            try {
                String username = edittxtfullname.getText().toString();
                String message = edittxtmessage.getText().toString();

                obj.put("name",username);
                obj.put("text",message);
                obj.put("news_id",selectedNews.getId());

                URL url = new URL(urlStr);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setDoOutput(true);
                conn.setDoInput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type","application/json");
                conn.connect();

                DataOutputStream writer = new DataOutputStream(conn.getOutputStream());
                writer.writeBytes(obj.toString());

                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    System.out.println("No problem posting a comment.");
                    finish();
                } else {
                    System.out.println("PROBLEMM");
                    showPostAlertDialog();
                }

            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }

            return buffer.toString();
        }

        private void showPostAlertDialog() {

            FragmentManager fm = getSupportFragmentManager();
            PostAlertDialog dialog = new PostAlertDialog();
            dialog.show(fm,"");
        }

    }

}
