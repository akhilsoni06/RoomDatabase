package com.akhil.roomdatabase.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.akhil.roomdatabase.dao.UserDao;
import com.akhil.roomdatabase.model.User;

/**
 * Created by Akhil on 12-01-2018.
 */

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DB_NAME = "user_db";

    public abstract UserDao getUserDao();

}
