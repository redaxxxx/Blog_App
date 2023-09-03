package com.example.blogapp.overviewPost;

import com.example.blogapp.data.Post;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.blogapp.databinding.PostItemsBinding;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHandler> {

    private List<Post> postList;
    private Context context;
    private PostItemClickListener itemClickListener;

    public PostAdapter(Context context, List<Post> postList, PostItemClickListener itemClickListener) {
        this.context = context;
        this.postList = postList;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public PostHandler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostHandler(PostItemsBinding.inflate(LayoutInflater.from(parent.getContext()), parent,
                false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostHandler holder, int position) {
        Post post = postList.get(position);

        holder.binding.postTitle.setText(post.getPost_title());
        Glide.with(holder.itemView.getContext())
                .load(post.getPost_image())
                .into(holder.binding.postImage);

        holder.itemView.setOnClickListener(view -> {
            itemClickListener.onItemClickListener(post);
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    class PostHandler extends RecyclerView.ViewHolder{
        private PostItemsBinding binding;
        public PostHandler(@NonNull PostItemsBinding postItemBinding) {
            super(postItemBinding.getRoot());
            binding = postItemBinding;
        }
    }

}
