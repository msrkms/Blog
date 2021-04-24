package com.sajidur.blog.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sajidur.blog.model.Blog;
import com.sajidur.blog.network.CheckNetwork;
import com.sajidur.blog.repository.BlogRepository;
import com.sajidur.blog.view.BlogActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BlogViewModel extends AndroidViewModel {
    private Context context;



    private LiveData<List<Blog>> blogLiveData;
    private BlogRepository blogRepository;

    public BlogViewModel(@NonNull Application application) {
        super(application);
        context = application;
        blogRepository = new BlogRepository();
        getData();
    }

    private void getData(){
        CheckNetwork checkNetwork=new CheckNetwork(context);
        if(checkNetwork.isNetworkConnected()){
            this.blogLiveData = blogRepository.getBlogFromURL();
        }else {
            Toast.makeText(context,"No network avaiable.Showing data from local device",Toast.LENGTH_LONG).show();
        }
    }




    public LiveData<List<Blog>>getBlogLiveData(){
        return blogLiveData;
    }




}
