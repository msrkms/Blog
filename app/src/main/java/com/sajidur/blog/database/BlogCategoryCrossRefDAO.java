package com.sajidur.blog.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.sajidur.blog.model.RoomDB.AuthorRoomModel;
import com.sajidur.blog.model.RoomDB.BlogCategoryCrossRef;

import java.util.List;

@Dao
public interface BlogCategoryCrossRefDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(BlogCategoryCrossRef blogCategoryCrossRef);

    @Query("SELECT categoryName FROM Categories JOIN BlogCategory ON Categories.categoryID=BlogCategory.categoryID WHERE blogID=:blogID")
    String[] getAllCategoryNames(int blogID);

}
