package com.example.together.adapter;
import android.util.Log;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.together.R;
import com.example.together.activities.BbsDetailActivity;
import com.example.together.activities.PostActivity;
import com.example.together.activities.PostListActivity;
import com.example.together.models.Post;

import android.widget.Toast;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {


    private ArrayList<Post> postList;
    private Context context;

    public PostAdapter(ArrayList<Post> postList, Context context){
        this.postList = postList;
        this.context = context;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        PostViewHolder holder = new PostViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.title.setText(postList.get(position).getTitle());
        holder.content.setText(postList.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return (postList != null ? postList.size() : 0);
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView content;

        public PostViewHolder(@NonNull View postView) {
            super(postView);
            this.title = itemView.findViewById(R.id.titleTextView);
            this.content = itemView.findViewById(R.id.contentTextView);

            postView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition() ;
                    if (pos != RecyclerView.NO_POSITION) {
                        Log.d("w","sex"+pos);
                        Intent intent = new Intent(view.getContext(), BbsDetailActivity.class);
                        intent.putExtra("title",postList.get(pos).getTitle());
                        intent.putExtra("content",postList.get(pos).getContent());
                        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                        view.getContext().startActivity(intent);
                    }
                }
            });
        }
    }
}
