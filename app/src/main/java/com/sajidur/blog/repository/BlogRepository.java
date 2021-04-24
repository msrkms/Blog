package com.sajidur.blog.repository;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.MutableLiveData;

import com.sajidur.blog.model.Blog;
import com.sajidur.blog.model.BlogList;
import com.sajidur.blog.network.API;
import com.sajidur.blog.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class BlogRepository {


    private MutableLiveData< List<Blog>> blogs;

    public BlogRepository() {

    }

    public MutableLiveData<List<Blog>> getBlogList(){
        if (blogs==null){
            blogs= new MutableLiveData<>();
        }
        return blogs;
    }


    public MutableLiveData<List<Blog>>  getBlogFromURL() {
        if(blogs==null){
            blogs=new MutableLiveData<>();
        }
        RetrofitClient retrofitClient = new RetrofitClient();
        Retrofit retrofit = retrofitClient.getClient();
        API api = retrofit.create(API.class);
        Call<BlogList> listCall = api.getBlogs();
        listCall.enqueue(new Callback<BlogList>() {
            @Override
            public void onResponse(Call<BlogList> call, Response<BlogList> response) {
                if(!response.isSuccessful()){
                }else {
                    BlogList blogList=response.body();
                    List<Blog> temp=new ArrayList<>();
                    for (int i = 0; i < blogList.getBlogs().size(); i++) {
                        temp.add(blogList.getBlogs().get(i));
                    }
                    blogs.postValue(temp);

                }
            }

            @Override
            public void onFailure(Call<BlogList> call, Throwable t) {
                blogs.setValue(null);
            }
        });
        return blogs;
    }


}
