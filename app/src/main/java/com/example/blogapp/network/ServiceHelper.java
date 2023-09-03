package com.example.blogapp.network;

import com.example.blogapp.data.Post;
import com.example.blogapp.data.ServerResponse;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceHelper {
    private static final String BASE_URL = "https://task.astra-tech.net/fronendtask/public/api/";
    private static ServiceHelper mInstance = new ServiceHelper();
    private ApiService service;
    private ServiceHelper(){
        Retrofit retrofit = createRetrofit().build();
        service = retrofit.create(ApiService.class);
    }
    public static ServiceHelper getInstance(){
        return mInstance;
    }

    private Retrofit.Builder createRetrofit(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .addNetworkInterceptor(new TokenInterceptor()).build();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create());
    }

    public Call<List<Post>> getAllPosts(){
        return service.getAllPosts();
    }
    public Call<ServerResponse> createPost(RequestBody title,
                                         RequestBody message,
                                         MultipartBody.Part image){
        return service.createPost(title, message, image);
    }
}
