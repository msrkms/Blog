package com.sajidur.blog.model.RoomDB;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = AuthorRoomModel.class,
        parentColumns = "authorID",
        childColumns = "authorCreatedID",
        onDelete = CASCADE,
        onUpdate = CASCADE),tableName = "Blog")

public class BlogRoomModel {
    @PrimaryKey(autoGenerate = false)
    private int blogID;
    private String blogTitle;
    private String blogDetails;
    private String blogImage;
    private int authorCreatedID;





    public int getBlogID() {
        return blogID;
    }

    public void setBlogID(int blogID) {
        this.blogID = blogID;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogDetails() {
        return blogDetails;
    }

    public void setBlogDetails(String blogDetails) {
        this.blogDetails = blogDetails;
    }

    public String getBlogImage() {
        return blogImage;
    }

    public void setBlogImage(String blogImage) {
        this.blogImage = blogImage;
    }

    public int getAuthorCreatedID() { return authorCreatedID; }

    public void setAuthorCreatedID(int authorCreatedID) { this.authorCreatedID = authorCreatedID; }
}