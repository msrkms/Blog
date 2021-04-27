package com.sajidur.blog.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.sajidur.blog.model.RoomDB.AuthorRoomModel;
import com.sajidur.blog.model.RoomDB.BlogCategoryCrossRef;
import com.sajidur.blog.model.RoomDB.BlogRoomModel;
import com.sajidur.blog.model.RoomDB.CategoriesRoomModel;

@Database(entities = {CategoriesRoomModel.class, AuthorRoomModel.class,BlogRoomModel.class, BlogCategoryCrossRef.class},version = 1,exportSchema = false)
public abstract class RoomDB extends RoomDatabase {

    public static RoomDB roomDB;
    private static String DBName="database";

    public synchronized static RoomDB getInstance(Context context){
        if(roomDB==null){
            roomDB= Room.databaseBuilder(context.getApplicationContext(),RoomDB.class,DBName).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return roomDB;
    }


    public abstract BlogCategoryCrossRefDAO blogCategoryCrossRefDAO();
    public abstract AuthorDAO authorDAO();
    public abstract CategoriesDAO categoriesDAO();
    public abstract BlogDAO blogDAO();
    public abstract AuthorBlogDAO authorBlogDAO();
}
