package com.example.blogapp.network;

import com.example.blogapp.data.Post;
import com.example.blogapp.data.ServerResponse;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

interface ApiService {
    @GET("getposts")
    Call<List<Post>> getAllPosts();

    @Multipart
    @POST("create")
    Call<ServerResponse> createPost(@Part RequestBody title,
                                  @Part RequestBody message,
                                  @Part MultipartBody.Part image);

    @POST("updatepost")
    Call<Post> updatePost(@Body Post post);
}
