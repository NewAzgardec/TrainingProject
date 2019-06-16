package com.example.vkpage.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.vkpage.friends.FriendsModel;

@Database(entities = {FriendsModel.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract PersonDao getPersonDao();
}
