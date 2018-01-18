package com.akhil.roomdatabase.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.akhil.roomdatabase.model.User;

import java.util.List;

/**
 * DAOs are responsible for defining the methods that access the database.
 * Created by Akhil on 13-01-2018.
 */

@Dao
public interface UserDao {
    @Insert
    void insetAll(User... user);

    @Update
    void updateAll(User... user);

    @Delete
    void deleteAll(User... user);

    @Query("SELECT * FROM User")
    List<User> getAllUser();

}
