package com.example.blogapp.detailPost;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.example.blogapp.R;
import com.example.blogapp.Utils;
import com.example.blogapp.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;
    private String postTitle;
    private String postMessage;
    private String postImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        getInfoFromIntent();

        binding.postTitleTv.setText(postTitle);
        binding.postMessageTv.setText(postMessage);
        Glide.with(this)
                .load(postImage)
                .into(binding.postImage);

    }

    private void getInfoFromIntent(){
        Intent intent = getIntent();
        postTitle = intent.getStringExtra(Utils.POST_TITLE);
        postMessage = intent.getStringExtra(Utils.POST_MESSAGE);
        postImage = intent.getStringExtra(Utils.POST_IMAGE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.update_item){

        }
        if (id == R.id.delete_menu){

        }

        return super.onOptionsItemSelected(item);

    }
}