package com.sajidur.blog.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.sajidur.blog.R;
import com.sajidur.blog.databinding.ActivityBlogDetailsBinding;
import com.sajidur.blog.model.POJO.Blog;
import com.sajidur.blog.model.databinding.BlogDetailsActivityBindingModel;
import com.sajidur.blog.utils.JsonConverter;
import com.sajidur.blog.viewmodel.BlogDetailsViewModel;
import com.sajidur.blog.viewmodel.BlogViewModel;

public class BlogDetailsActivity extends AppCompatActivity {
    private ActivityBlogDetailsBinding binding;
    private BlogDetailsViewModel blogDetailsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_blog_details);

        Intent intent = getIntent();

        blogDetailsViewModel=new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(BlogDetailsViewModel.class);

        binding.setData(blogDetailsViewModel.getData(intent.getStringExtra("data")));

    }


}