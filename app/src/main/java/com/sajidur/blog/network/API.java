package com.sajidur.blog.network;

import com.sajidur.blog.model.POJO.BlogList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    @GET("db")
    Call<BlogList> getBlogs();
}
