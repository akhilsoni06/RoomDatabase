package com.akhil.roomdatabase;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.akhil.roomdatabase.activity.AddNoteActivity;
import com.akhil.roomdatabase.db.AppDatabase;
import com.akhil.roomdatabase.model.Note;

import java.util.List;

/**
 * Created by e on 12-01-2018.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView mListView;
    private AppDatabase mDataBase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDataBase = Room.databaseBuilder(this,AppDatabase.class,AppDatabase.DB_NAME).build();
        initView();
    }

    private void initView()
    {
        mListView =(RecyclerView)findViewById(R.id.recycler_view);
        findViewById(R.id.add).setOnClickListener(this);

}

    @Override
    protected void onResume() {
        super.onResume();
        loadNotes();
    }

    @Override
    public void onClick(View view) {
        if(view.getId()== R.id.add)
        {
            startActivity(new Intent(this, AddNoteActivity.class));
        }
    }

    private void loadNotes() {
        new AsyncTask<Void, Void, List<Note>>() {
            @Override
            protected List<Note> doInBackground(Void... params) {
                return mDataBase.getNoteDao().getAllNote();
            }

            @Override
            protected void onPostExecute(List<Note> notes) {
                //adapter.setNotes(notes);
                if(notes.size()>0)
                    Log.d("note size is",notes.size()+" size"+notes.get(0).getTitle());
            }

        }.execute();
    }
    }
