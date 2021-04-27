package com.sajidur.blog.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.sajidur.blog.model.RoomDB.CategoriesRoomModel;

import java.util.List;

@Dao
public interface CategoriesDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(CategoriesRoomModel categoriesRoomModel);

    @Query("DELETE  FROM Categories")
    void deleteAll();

    @Delete
    void deleteBlogs(CategoriesRoomModel categoriesRoomModel);

    @Update
    void updateBlogs(CategoriesRoomModel categoriesRoomModel);

    @Query("SELECT * FROM Categories")
    List<CategoriesRoomModel> getAll();

    @Query("SELECT categoryName FROM Categories WHERE categoryName = :Name LIMIT 1")
    String findOne(String Name);

    @Query("SELECT categoryID FROM Categories WHERE categoryName = :Name LIMIT 1")
    int findID(String Name);
}
