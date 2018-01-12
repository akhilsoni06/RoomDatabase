package com.akhil.roomdatabase.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.akhil.roomdatabase.model.Note;

import java.util.List;

/**
 * Created by e on 13-01-2018.
 */

@Dao
public interface NoteDao {
    @Insert
    void insetAll(Note... note);

    @Update
    void updateAll(Note... note);

    @Delete
    void deleteAll(Note... note);

    @Query("SELECT * FROM note")
    List<Note> getAllNote();

}
