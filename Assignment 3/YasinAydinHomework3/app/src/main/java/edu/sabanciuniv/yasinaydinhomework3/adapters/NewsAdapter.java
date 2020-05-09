package edu.sabanciuniv.yasinaydinhomework3.adapters;

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

import edu.sabanciuniv.yasinaydinhomework3.ImageDownloadTask;
import edu.sabanciuniv.yasinaydinhomework3.R;
import edu.sabanciuniv.yasinaydinhomework3.model.NewsItem;

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
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.newsItemClicked(newsItems.get(position));
            }
        });

        if (newsItems.get(position).getBitmap() == null) {
            new ImageDownloadTask(holder.imgNews).execute(newsItems.get(position));

        } else {
            holder.imgNews.setImageBitmap(newsItems.get(position).getBitmap());
        }

    }

    @Override
    public int getItemCount() {
        return newsItems.size();
    }

    public interface NewsItemClickListener{
        public void newsItemClicked(NewsItem selectedNewsItem);
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
