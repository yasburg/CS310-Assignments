package edu.sabanciuniv.yasinaydinhomework3.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.sabanciuniv.yasinaydinhomework3.R;
import edu.sabanciuniv.yasinaydinhomework3.model.CommentItem;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder> {

    List<CommentItem> commentItems;
    Context context;

    public CommentsAdapter(List<CommentItem> commentItems, Context context) {
        this.commentItems = commentItems;
        this.context = context;
    }

    @NonNull
    @Override
    public CommentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.comment_row_layout, parent, false);
        return new CommentsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsViewHolder holder, final int position) {

        holder.txtusername.setText(commentItems.get(position).getName());
        holder.txtcomment.setText(commentItems.get(position).getMessage());

    }

    @Override
    public int getItemCount() {
        return commentItems.size();
    }

    class CommentsViewHolder extends RecyclerView.ViewHolder{
        TextView txtusername;
        TextView txtcomment;
        ConstraintLayout root;

        public CommentsViewHolder(@NonNull View itemView) {
            super(itemView);
            txtusername = itemView.findViewById(R.id.txtusername);
            txtcomment = itemView.findViewById(R.id.txtcomment);
            root = itemView.findViewById(R.id.container);

        }

    }

}
