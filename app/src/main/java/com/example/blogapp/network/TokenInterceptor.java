package com.example.blogapp.network;

import androidx.annotation.NonNull;

import com.example.blogapp.Utils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder requestBuilder = request.newBuilder();
        requestBuilder.addHeader("Content_Type", Utils.MULTIPART);
        requestBuilder.addHeader("ACCEPT", Utils.APPLICATION_JSON);
        request = requestBuilder.build();
        return chain.proceed(request);
    }
}
