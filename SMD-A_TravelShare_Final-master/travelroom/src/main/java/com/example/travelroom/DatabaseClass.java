package com.example.travelroom;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {Location.class, User.class}, version = 1, exportSchema = false)


public abstract class DatabaseClass extends RoomDatabase {

    public abstract myDAO daoAccess();
}