package com.sajidur.blog.repository;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.sajidur.blog.database.RoomDB;
import com.sajidur.blog.model.POJO.Author;
import com.sajidur.blog.model.POJO.Blog;
import com.sajidur.blog.model.POJO.BlogList;
import com.sajidur.blog.model.RoomDB.AuthorRoomModel;
import com.sajidur.blog.model.RoomDB.BlogCategoryCrossRef;
import com.sajidur.blog.model.RoomDB.BlogRoomModel;
import com.sajidur.blog.model.RoomDB.CategoriesRoomModel;
import com.sajidur.blog.network.API;
import com.sajidur.blog.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class BlogRepository {
    private RoomDB roomDB;
    private MutableLiveData< List<Blog>> blogs;
    private MutableLiveData<List<Blog>> allBlogs;

    public BlogRepository(Context context) {
        roomDB=RoomDB.getInstance(context);
    }

    public MutableLiveData<List<Blog>> getBlogList(){
        if (blogs==null){
            blogs= new MutableLiveData<>();
        }
        return blogs;
    }

    public MutableLiveData<List<Blog>> getAllBlogs(){
      List<BlogRoomModel>  blogs= roomDB.blogDAO().getAll();
      MutableLiveData<List<Blog>> listMutableLiveData=new MutableLiveData<>();
      List<Blog> blogList=new ArrayList<>();
        for (int i = 0; i < blogs.size(); i++) {
           Blog blog=new Blog();
           blog.setId(blogs.get(i).getBlogID());
           blog.setTitle(blogs.get(i).getBlogTitle());
           blog.setDescription(blogs.get(i).getBlogDetails());
           blog.setCoverPhoto(blogs.get(i).getBlogImage());
           //author
            AuthorRoomModel authorRoomModel=  roomDB.authorDAO().findAuthorByID(blogs.get(i).getAuthorCreatedID());
            Author author  =new Author();
            author.setId(authorRoomModel.getAuthorID());
            author.setName(authorRoomModel.getAuthorName());
            author.setProfession(authorRoomModel.getAuthorProfession());
            author.setAvatar(authorRoomModel.getAuthorAvatar());
            blog.setAuthor(author);

            String[] categories=roomDB.blogCategoryCrossRefDAO().getAllCategoryNames(blogs.get(i).getBlogID());
            List<String> tempct=new ArrayList<>();
            for (int j = 0; j < categories.length; j++) {
                tempct.add(categories[j]);
            }
            blog.setCategories(tempct);
            blogList.add(blog);
        }
        listMutableLiveData.setValue(blogList);

      return listMutableLiveData;
    }


    public void insertBlogINTORoomDB(BlogRoomModel blogRoomModel){
        roomDB.blogDAO().insert(blogRoomModel);
    }

    public  int insertCategoryINTORoomDB(CategoriesRoomModel categoriesRoomModel){
        int id=0;
        try {
          String Name=  roomDB.categoriesDAO().findOne(categoriesRoomModel.getCategoryName());
            if(Name==null || !Name.equals(categoriesRoomModel.getCategoryName())){
                Long aLong;
                aLong= roomDB.categoriesDAO().insert(categoriesRoomModel);
                id=aLong.intValue();
            }else {
                if(Name.equals(categoriesRoomModel.getCategoryName())){
                    id=roomDB.categoriesDAO().findID(categoriesRoomModel.getCategoryName());
                }
            }
        }catch (Exception e){
            System.out.println("Category Insert Error"+e.toString());
        }
        return id;
    }



    public void insertAuthorINTORoomDB(AuthorRoomModel authorRoomModel){
        roomDB.authorDAO().insert(authorRoomModel);
    }


    public void insertBlogCategoryCrossRefINTORoomDB(BlogCategoryCrossRef blogCategoryCrossRef){

        roomDB.blogCategoryCrossRefDAO().insert(blogCategoryCrossRef);
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
