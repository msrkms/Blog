package com.sajidur.blog.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.sajidur.blog.model.POJO.Blog;
import com.sajidur.blog.model.RoomDB.AuthorRoomModel;
import com.sajidur.blog.model.RoomDB.BlogCategoryCrossRef;
import com.sajidur.blog.model.RoomDB.BlogRoomModel;
import com.sajidur.blog.model.RoomDB.CategoriesRoomModel;
import com.sajidur.blog.network.CheckNetwork;
import com.sajidur.blog.repository.BlogRepository;

import java.util.ArrayList;
import java.util.List;

public class BlogViewModel extends AndroidViewModel {
    private Context context;
    private LiveData<List<Blog>> blogLiveData;
    private BlogRepository blogRepository;
    private LiveData<List<Blog>> allBlogs;

    public BlogViewModel(@NonNull Application application) {
        super(application);
        context = application;
        blogRepository = new BlogRepository(application);
        this.blogLiveData=blogRepository.getBlogFromURL();
    }

    public LiveData<List<Blog>> getAllBlogs(){
        allBlogs=blogRepository.getAllBlogs();
        return allBlogs;
    }





    public void insertIntoROOMDB(){
        try{
            for (int i = 0; i < blogLiveData.getValue().size(); i++) {
                Blog blog=blogLiveData.getValue().get(i);
                int[] id=new int[blog.getCategories().size()];

                for (int j = 0; j <blog.getCategories().size() ; j++) {
                    CategoriesRoomModel categoriesRoomModel=new CategoriesRoomModel();
                    categoriesRoomModel.setCategoryName(blog.getCategories().get(j));
                    id[j]=blogRepository.insertCategoryINTORoomDB(categoriesRoomModel);
                }
                AuthorRoomModel authorRoomModel= new AuthorRoomModel();

                authorRoomModel.setAuthorID(blog.getAuthor().getId());
                authorRoomModel.setAuthorName(blog.getAuthor().getName());
                authorRoomModel.setAuthorAvatar(blog.getAuthor().getAvatar());
                authorRoomModel.setAuthorProfession(blog.getAuthor().getProfession());
                blogRepository.insertAuthorINTORoomDB(authorRoomModel);

                BlogRoomModel blogRoomModel=new BlogRoomModel();
                blogRoomModel.setAuthorCreatedID(authorRoomModel.getAuthorID());
                blogRoomModel.setBlogID(blog.getId());
                blogRoomModel.setBlogTitle(blog.getTitle());
                blogRoomModel.setBlogDetails(blog.getDescription());
                blogRoomModel.setBlogImage(blog.getCoverPhoto());

                blogRepository.insertBlogINTORoomDB(blogRoomModel);

                for (int k = 0; k < id.length; k++) {
                    BlogCategoryCrossRef blogCategoryCrossRef=new BlogCategoryCrossRef();
                    blogCategoryCrossRef.setBlogID(blogRoomModel.getBlogID());
                    blogCategoryCrossRef.setCategoryID(id[k]);

                    blogRepository.insertBlogCategoryCrossRefINTORoomDB(blogCategoryCrossRef);
                }

            }

        }catch (Exception e){

        }

    }





    public LiveData<List<Blog>>getBlogLiveData(){
        return blogLiveData;
    }




}
