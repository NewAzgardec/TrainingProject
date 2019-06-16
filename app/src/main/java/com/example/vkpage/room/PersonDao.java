package com.example.vkpage.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.vkpage.friends.FriendsModel;

import java.util.List;

@Dao
public interface PersonDao {

    @Update
    void update(FriendsModel friendsModel);

    @Query("SELECT * FROM friendsmodel")
    List<FriendsModel> getAll();

}
