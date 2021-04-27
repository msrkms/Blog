package com.sajidur.blog.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.sajidur.blog.R;
import com.sajidur.blog.adapter.BlogAdapter;
import com.sajidur.blog.databinding.ActivityBlogBinding;
import com.sajidur.blog.model.POJO.Blog;
import com.sajidur.blog.viewmodel.BlogViewModel;

import java.util.List;

public class BlogActivity extends AppCompatActivity {
    SharedPreferences prefs=null;
    private BlogViewModel blogViewModel;
    private BlogAdapter blogAdapter;
    private List<Blog> blogList;
    private ActivityBlogBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= DataBindingUtil.setContentView(this,R.layout.activity_blog);

        prefs = getPreferences(Context.MODE_PRIVATE);


        blogViewModel=new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(BlogViewModel.class);

        if(needOnlineData()){
            blogViewModel.getBlogLiveData().observe(BlogActivity.this, new Observer<List<Blog>>() {
                @Override
                public void onChanged(List<Blog> blogs) {
                    blogViewModel.insertIntoROOMDB();
                    blogList=blogs;
                    setRecyclerViewBlogData();
                    System.out.println("Online Data");
                    prefs.edit().putBoolean("NeedOnlineData", false).commit();

                }
            });
        }else{
            blogViewModel.getAllBlogs().observe(BlogActivity.this, new Observer<List<Blog>>() {
                @Override
                public void onChanged(List<Blog> blogs) {
                    blogList=blogs;
                    setRecyclerViewBlogData();
                    System.out.println("Offline Data");
                }
            });
        }







    }

    private boolean needOnlineData(){
        if(prefs.getBoolean("NeedOnlineData",true)){
            return true;
        }else{
             return false;
        }
    }

    private void setRecyclerViewBlogData(){
        blogAdapter=new BlogAdapter(blogList);
        binding.recyclerViewBlog.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewBlog.setAdapter(blogAdapter);
    }

}