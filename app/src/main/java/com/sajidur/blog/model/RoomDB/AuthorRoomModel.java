package com.sajidur.blog.model.RoomDB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Entity(tableName = "Author")
public class AuthorRoomModel {

    @PrimaryKey(autoGenerate = false)
    private int authorID;
    private String authorName;
    private String authorAvatar;
    private String authorProfession;



    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorAvatar() {
        return authorAvatar;
    }

    public void setAuthorAvatar(String authorAvatar) {
        this.authorAvatar = authorAvatar;
    }

    public String getAuthorProfession() {
        return authorProfession;
    }

    public void setAuthorProfession(String authorProfession) {
        this.authorProfession = authorProfession;
    }



}
