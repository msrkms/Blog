package com.sajidur.blog.model.databinding;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.sajidur.blog.model.POJO.Blog;
import com.squareup.picasso.Picasso;

public class BlogDetailsActivityBindingModel {
    private Blog blog;
    private String Categories;

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public String getCategories() {
        return Categories;
    }

    public void setCategories(String categories) {
        Categories = categories;
    }

    @BindingAdapter("android:imageUrl")
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.get().load(imageUrl).into(view);
    }
}
