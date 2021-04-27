package com.sajidur.blog.model.RoomDB;

import androidx.room.Entity;

@Entity(primaryKeys = {"blogID", "categoryID"},tableName = "BlogCategory")
public class BlogCategoryCrossRef {
    private int blogID;
    private int categoryID;

    public int getBlogID() {
        return blogID;
    }

    public void setBlogID(int blogID) {
        this.blogID = blogID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }
}
