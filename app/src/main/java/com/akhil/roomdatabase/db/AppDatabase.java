package com.akhil.roomdatabase.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.akhil.roomdatabase.dao.NoteDao;
import com.akhil.roomdatabase.model.Note;

/**
 * Created by Akhil on 12-01-2018.
 */

@Database(entities = {Note.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DB_NAME = "sample_db";

    public abstract NoteDao getNoteDao();

}
