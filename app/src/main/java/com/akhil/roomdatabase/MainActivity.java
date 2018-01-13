package com.akhil.roomdatabase;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.akhil.roomdatabase.activity.AddNoteActivity;
import com.akhil.roomdatabase.db.AppDatabase;
import com.akhil.roomdatabase.model.Note;

import java.util.List;

/**
 * Created by Akhil on 12-01-2018.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView mRecycleListView;
    private AppDatabase mDataBase;
    private NoteAdapter mNoteAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDataBase = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME).build();
        initView();
    }

    private void initView() {
        mRecycleListView = (RecyclerView) findViewById(R.id.recycler_view);
        findViewById(R.id.add).setOnClickListener(this);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(MainActivity.this);
        mRecycleListView.setLayoutManager(manager);

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNotes();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.add) {
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
                if (notes != null && notes.size() > 0)
                    mNoteAdapter = new NoteAdapter(notes);
                mRecycleListView.setAdapter(mNoteAdapter);
            }
        }.execute();
    }
}
