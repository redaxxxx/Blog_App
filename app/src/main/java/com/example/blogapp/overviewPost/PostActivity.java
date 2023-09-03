package com.example.blogapp.overviewPost;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.blogapp.R;
import com.example.blogapp.Utils;
import com.example.blogapp.data.Post;
import com.example.blogapp.databinding.ActivityPostBinding;
import com.example.blogapp.detailPost.AddNewPostActivity;
import com.example.blogapp.detailPost.DetailActivity;
import com.example.blogapp.overviewPost.PostViewModel;

import java.util.List;

public class PostActivity extends AppCompatActivity implements PostItemClickListener{
    private ActivityPostBinding binding;
    private PostViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_post);

        viewModel = ViewModelProviders.of(this).get(PostViewModel.class);
        viewModel.getAllPosts().observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> posts) {
                prepareRecyclerView(posts);
            }
        });

        binding.createPostBtn.setOnClickListener(view -> {
            startActivity(new Intent(PostActivity.this, AddNewPostActivity.class));
        });
    }

    private void prepareRecyclerView(List<Post> postList){
        binding.postRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL
                , false));
        binding.postRecyclerView.setHasFixedSize(true);
        binding.postRecyclerView.setItemAnimator(new DefaultItemAnimator());

        PostAdapter postAdapter = new PostAdapter(this, postList, this);
        binding.postRecyclerView.setAdapter(postAdapter);
    }

    @Override
    public void onItemClickListener(Post post) {
        Intent intent = new Intent(PostActivity.this, DetailActivity.class);
        intent.putExtra(Utils.POST_TITLE, post.getPost_title());
        intent.putExtra(Utils.POST_MESSAGE, post.getPost_message());
        intent.putExtra(Utils.POST_IMAGE, post.getPost_image());

        startActivity(intent);
    }
}