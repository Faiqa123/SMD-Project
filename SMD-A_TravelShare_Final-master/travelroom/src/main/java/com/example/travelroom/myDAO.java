package com.example.travelroom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;

@Dao
public interface myDAO {

    @Insert
    void InsertLocation(Location location);

    @Insert
    void InsertUser(User user);

    @Delete
    void DeleteLocation(Location location);

    @Delete
    void DeleteUser(User user);
}
