package com.sajidur.blog.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.sajidur.blog.model.RoomDB.AuthorRoomModel;
import com.sajidur.blog.model.RoomDB.BlogRoomModel;

import java.util.List;
@Dao
public interface AuthorDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(AuthorRoomModel authorRoomModel);

    @Query("SELECT * FROM Author")
    List<AuthorRoomModel> getAll();

    @Query("SELECT authorName FROM Author WHERE authorName = :Name LIMIT 1")
    String findByName(String Name);

    @Query("SELECT authorID FROM Author WHERE authorID = :ID LIMIT 1")
    int findByID(int ID);

    @Query("SELECT * FROM Author WHERE authorID = :ID LIMIT 1")
    AuthorRoomModel findAuthorByID(int ID);
}
