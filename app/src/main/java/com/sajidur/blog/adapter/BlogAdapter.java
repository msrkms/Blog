package com.sajidur.blog.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.sajidur.blog.R;
import com.sajidur.blog.databinding.BlogItemsBinding;
import com.sajidur.blog.model.databinding.BlogItemBindingModel;
import com.sajidur.blog.model.POJO.Blog;
import com.sajidur.blog.utils.JsonConverter;
import com.sajidur.blog.view.BlogActivity;
import com.sajidur.blog.view.BlogDetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.BlogViewHolder>{
    private List<Blog> blogs;
    private List<BlogItemBindingModel>  modelList;

    public BlogAdapter(List<Blog> blogs) {
        this.blogs = blogs;
        convertDataForBinding();
    }

    private void convertDataForBinding(){
        modelList=new ArrayList<>();
        for (int i = 0; i < blogs.size(); i++) {
            BlogItemBindingModel blogItemBindingModel=new BlogItemBindingModel();
            blogItemBindingModel.setTitle(blogs.get(i).getTitle());
            blogItemBindingModel.setDetails(blogs.get(i).getDescription());
            String Categories="";
            for (int j = 0; j < blogs.get(i).getCategories().size(); j++) {
                Categories=Categories+blogs.get(i).getCategories().get(j);
                if(j!=(blogs.get(i).getCategories().size()-1)){
                    Categories=Categories+",";
                }
            }
            blogItemBindingModel.setCategory(Categories);
            blogItemBindingModel.setImageURL(blogs.get(i).getCoverPhoto());
            modelList.add(blogItemBindingModel);
        }
    }
    @NonNull
    @Override
    public BlogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BlogViewHolder(
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.blog_items,parent,false)
        );

    }

    @Override
    public void onBindViewHolder(@NonNull BlogViewHolder holder, int position) {
        holder.binding.setData(modelList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return blogs!=null? blogs.size():0;
    }

    public class BlogViewHolder extends RecyclerView.ViewHolder{
        private BlogItemsBinding binding;

        public BlogViewHolder(@NonNull BlogItemsBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
            binding.imageViewCoverPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =new Intent(v.getContext(),BlogDetailsActivity.class);
                    intent.putExtra("data", JsonConverter.getGsonParser().toJson(blogs.get(getAdapterPosition())));
                    v.getContext().startActivity(intent);
                }
            });
        }


    }
}
