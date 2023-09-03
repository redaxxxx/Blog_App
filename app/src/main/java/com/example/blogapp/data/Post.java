package com.example.blogapp.data;

public class Post {
    private String post_title;
    private String post_image;
    private String post_message;
    private long id;

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String value) {
        this.post_title = value;
    }

    public String getPost_image() {
        return post_image;
    }

    public void setPost_image(String value) {
        this.post_image = value;
    }

    public String getPost_message() {
        return post_message;
    }

    public void setPost_message(String value) {
        this.post_message = value;
    }

    public long getid() {
        return id;
    }

    public void setid(long value) {
        this.id = value;
    }

}
