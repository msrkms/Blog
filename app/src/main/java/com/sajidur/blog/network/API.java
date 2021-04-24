package com.sajidur.blog.network;

import com.sajidur.blog.model.Blog;
import com.sajidur.blog.model.BlogList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    @GET("db")
    Call<BlogList> getBlogs();
}
