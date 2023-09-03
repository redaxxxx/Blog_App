package com.example.blogapp.detailPost;

import com.example.blogapp.data.FileUtils;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.blogapp.R;
import com.example.blogapp.Utils;
import com.example.blogapp.data.ServerResponse;
import com.example.blogapp.databinding.ActivityAddNewPostBinding;
import com.example.blogapp.network.ServiceHelper;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewPostActivity extends AppCompatActivity {

    private ActivityAddNewPostBinding binding;
    private Uri postImgUrl;

    private int id = 27;
    private final ActivityResultLauncher<String> mContent =
            registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri url) {
                    if (url != null){
                        postImgUrl = url;
                        binding.addPostImage.setImageURI(postImgUrl);
                    }else {
                        Log.d(Utils.TAG, "Url is empty");
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_new_post);

        binding.addPostImage.setOnClickListener(view -> {
            mContent.launch("image/");
            binding.addPostImage.setClickable(false);
        });

        binding.addPostBtn.setOnClickListener(view -> {
            uploadFile();
        });
    }

    private void uploadFile(){

        MultipartBody.Part part = prepareFilePart("image", postImgUrl);
        RequestBody postTitle = createPartFromTitleString(binding.postTitleEt.getText().toString());
        RequestBody postMessage = createPartFromMessageString(binding.postMessageEt.getText().toString());
        ServiceHelper.getInstance().createPost(postTitle, postMessage, part)
                .enqueue(new Callback<ServerResponse>() {
                    @Override
                    public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                        if (response.body() != null){
                            if (response.body().getSuccess()){
                                Toast.makeText(AddNewPostActivity.this, response.body().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                                Toast.makeText(AddNewPostActivity.this, "Done" + response.code(),
                                        Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(AddNewPostActivity.this, response.body().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                                Toast.makeText(AddNewPostActivity.this, "Failed" + response.code(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Log.d(Utils.TAG, response.body().getMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<ServerResponse> call, Throwable t) {
                        Log.d(Utils.TAG, t.getLocalizedMessage());
                    }
                });
    }

    @NonNull
    private RequestBody createPartFromTitleString(String postTitle) {
        return RequestBody.create(MediaType.parse(FileUtils.MIME_TYPE_TEXT), postTitle);
    }
    @NonNull
    private RequestBody createPartFromMessageString(String postMessage) {
        return RequestBody.create(MediaType.parse(FileUtils.MIME_TYPE_TEXT), postMessage);
    }

    @NonNull
    private MultipartBody.Part prepareFilePart(String partName, Uri fileUri) {
        // use the FileUtils to get the actual file by uri
        File file = FileUtils.getFile(this, fileUri);

        // create RequestBody instance from file
        RequestBody requestFile = RequestBody.create (MediaType.parse(FileUtils.MIME_TYPE_IMAGE), file);

        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }
}