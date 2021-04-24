package com.sajidur.blog.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sajidur.blog.R;
import com.sajidur.blog.model.Blog;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.BlogViewHolder>{
    private List<Blog> blogs;

    public BlogAdapter(List<Blog> blogs) {
        this.blogs = blogs;
    }

    @NonNull
    @Override
    public BlogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BlogAdapter.BlogViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_items,parent,false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull BlogViewHolder holder, int position) {
        holder.setData(blogs.get(position));
    }

    @Override
    public int getItemCount() {
        return blogs!=null? blogs.size():0;
    }

    public class BlogViewHolder extends RecyclerView.ViewHolder{
        TextView textViewTitle,textViewDescription,textViewCategories;
        ImageView imageViewBlog;
        public BlogViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle=(TextView) itemView.findViewById(R.id.textViewTitle);
            textViewDescription=(TextView) itemView.findViewById(R.id.textViewDescription);
            textViewCategories=(TextView) itemView.findViewById(R.id.textViewCategories);
            imageViewBlog=(ImageView) itemView.findViewById(R.id.imageViewCoverPhoto);
        }

        public void setData(Blog blog){
            textViewTitle.setText(blog.getTitle());
            textViewDescription.setText(blog.getDescription());
            String Categories="";
            for (int i = 0; i < blog.getCategories().size(); i++) {
                if(i>0){
                    Categories=Categories+",";
                }
                Categories=Categories+ blog.getCategories().get(i);
            }
            textViewCategories.setText(Categories);
            Picasso.get().load(blog.getCoverPhoto()).into(imageViewBlog);
        }
    }
}
