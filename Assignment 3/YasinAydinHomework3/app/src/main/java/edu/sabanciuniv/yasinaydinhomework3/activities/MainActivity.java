package edu.sabanciuniv.yasinaydinhomework3.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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
import java.util.Date;
import java.util.List;

import edu.sabanciuniv.yasinaydinhomework3.adapters.NewsAdapter;
import edu.sabanciuniv.yasinaydinhomework3.R;
import edu.sabanciuniv.yasinaydinhomework3.model.NewsItem;

public class MainActivity extends AppCompatActivity implements NewsAdapter.NewsItemClickListener {

    ProgressDialog prgDialog;
    RecyclerView newsRecView;
    List<NewsItem> data;
    Spinner spFilters;
    NewsAdapter adp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = new ArrayList<>();
        //Boiler plate code:
        newsRecView = findViewById(R.id.newsrec);
        spFilters = findViewById(R.id.spfilter);
        //

        adp2 = new NewsAdapter(data, this, this);
        newsRecView.setLayoutManager(new LinearLayoutManager(this));
        newsRecView.setAdapter(adp2);

        NewsTask tsk = new NewsTask();
        tsk.execute("http://94.138.207.51:8080/NewsApp/service/news/getall");


        String[] filters = getResources().getStringArray(R.array.filters);
        ArrayAdapter<String> adp1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, filters);
        spFilters.setAdapter(adp1);

        spFilters.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selectedOperation = spFilters.getSelectedItem().toString();
                NewsTask tsk = new NewsTask();
                int categoryID;
                if (selectedOperation.equals("All")) {
                    tsk.execute("http://94.138.207.51:8080/NewsApp/service/news/getall");
                } else if (selectedOperation.equals("Economics")) {
                    categoryID = 4;
                    tsk.execute("http://94.138.207.51:8080/NewsApp/service/news/getbycategoryid/"+String.valueOf(categoryID));
                } else if (selectedOperation.equals("Politics")) {
                    categoryID = 6;
                    tsk.execute("http://94.138.207.51:8080/NewsApp/service/news/getbycategoryid/"+String.valueOf(categoryID));
                } else if (selectedOperation.equals("Sports")) {
                    categoryID = 5;
                    tsk.execute("http://94.138.207.51:8080/NewsApp/service/news/getbycategoryid/"+String.valueOf(categoryID));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                NewsTask tsk = new NewsTask();
                tsk.execute("http://94.138.207.51:8080/NewsApp/service/news/getall");
            }

        });

    }

    class NewsTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            prgDialog = new ProgressDialog(MainActivity.this);
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

                        long date = current.getLong("date");
                        Date objDate = new Date(date);

                        NewsItem item = new NewsItem(current.getInt("id"),
                                current.getString("title"),
                                current.getString("text"),
                                current.getString("image"),
                                objDate);

                        data.add(item);
                    }

                } else {
                    System.out.println("serviceMessageCode == 0 !!!!");
                }

                adp2.notifyDataSetChanged();
                prgDialog.dismiss();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void newsItemClicked(NewsItem selectedNewsItem) {
        Intent i = new Intent(this, NewsDetailActivity.class);
        i.putExtra("selectednews", selectedNewsItem);
        startActivity(i);
    }
}
