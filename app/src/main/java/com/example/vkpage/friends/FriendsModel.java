package com.example.vkpage.friends;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class FriendsModel {
    public String first_name;
    public String last_name;
    public int online;
    public String photo_200;
    @PrimaryKey
    public int id;
}
