package edu.sabanciuniv.newsstarterexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

import edu.sabanciuniv.newsstarterexample.model.NewsItem;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    List<NewsItem> newsItems;
    Context context;
    NewsItemClickListener listener;

    public NewsAdapter(List<NewsItem> newsItems, Context context, NewsItemClickListener listener) {
        this.newsItems = newsItems;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override

    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.news_row_layout, parent, false);

        return new NewsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, final int position) {

        holder.txtDate.setText(new SimpleDateFormat("dd/MM/yyy").format(newsItems.get(position).getNewsDate()));
        holder.txtTitle.setText(newsItems.get(position).getTitle());
        holder.imgNews.setImageResource(newsItems.get(position).getImageId());
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.newItemClicked(newsItems.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsItems.size();
    }

    public interface NewsItemClickListener{
        public void newItemClicked(NewsItem selectedNewsItem);
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {

        ImageView imgNews;
        TextView txtTitle;
        TextView txtDate;
        ConstraintLayout root;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            imgNews = itemView.findViewById(R.id.imgnews);
            txtTitle = itemView.findViewById(R.id.textlisttitle);
            txtDate = itemView.findViewById(R.id.txtlistdate);
            root = itemView.findViewById(R.id.container);

        }
    }

}
