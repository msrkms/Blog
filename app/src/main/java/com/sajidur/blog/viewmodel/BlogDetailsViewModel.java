package com.sajidur.blog.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.sajidur.blog.model.POJO.Blog;
import com.sajidur.blog.model.databinding.BlogDetailsActivityBindingModel;
import com.sajidur.blog.utils.JsonConverter;

public class BlogDetailsViewModel  extends AndroidViewModel {
    public BlogDetailsViewModel(@NonNull Application application) {
        super(application);
    }

    public BlogDetailsActivityBindingModel getData(String JSON){
        Blog blog= JsonConverter.getGsonParser().fromJson(JSON,Blog.class);
        BlogDetailsActivityBindingModel blogDetailsActivityBindingModel=new BlogDetailsActivityBindingModel();
        blogDetailsActivityBindingModel.setBlog(blog);
        String Categories="";
        for (int j = 0; j <blog.getCategories().size(); j++) {
            Categories=Categories+blog.getCategories().get(j);
            if(j!=(blog.getCategories().size()-1)){
                Categories=Categories+",";
            }
        }
        blogDetailsActivityBindingModel.setCategories(Categories);

        return blogDetailsActivityBindingModel;
    }
}
