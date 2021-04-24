package com.sajidur.blog.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.sajidur.blog.R;
import com.sajidur.blog.adapter.BlogAdapter;
import com.sajidur.blog.model.Blog;
import com.sajidur.blog.repository.BlogRepository;
import com.sajidur.blog.viewmodel.BlogViewModel;

import java.util.List;

public class BlogActivity extends AppCompatActivity {

    private RecyclerView recyclerViewBlog;
    private BlogViewModel blogViewModel;
    private BlogAdapter blogAdapter;
    private List<Blog> blogList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);

        recyclerViewBlog=findViewById(R.id.recyclerViewBlog);


        blogViewModel=new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(BlogViewModel.class);

        blogViewModel.getBlogLiveData().observe(BlogActivity.this, new Observer<List<Blog>>() {
            @Override
            public void onChanged(List<Blog> blogs) {
                blogList=blogs;
                setRecyclerViewBlogData();
            }
        });


    }

    private void setRecyclerViewBlogData(){
        blogAdapter=new BlogAdapter(blogList);
        recyclerViewBlog.setAdapter(blogAdapter);
        recyclerViewBlog.setLayoutManager(new LinearLayoutManager(this));
    }

}