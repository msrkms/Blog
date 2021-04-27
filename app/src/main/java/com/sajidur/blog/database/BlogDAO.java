package com.sajidur.blog.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.sajidur.blog.model.POJO.Blog;
import com.sajidur.blog.model.RoomDB.BlogRoomModel;

import java.util.List;

@Dao
public interface BlogDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(BlogRoomModel blog);

    @Query("DELETE  FROM Blog")
    void deleteAll();

    @Delete
    void deleteBlogs(BlogRoomModel blog);

    @Update
    void updateBlogs(BlogRoomModel blogs);

    @Query("SELECT * FROM Blog")
    List<BlogRoomModel> getAll();
}
