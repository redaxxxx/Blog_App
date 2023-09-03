package com.example.blogapp.overviewPost;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.blogapp.Repository;
import com.example.blogapp.data.Post;

import java.util.List;

public class PostViewModel extends ViewModel {
    private Repository repository;
    public PostViewModel(){
        repository = new Repository();
    }
    public MutableLiveData<List<Post>> getAllPosts(){
        return repository.getAllPost();
    }
}
